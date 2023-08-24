package com.dhx.apicore.service.impl.inner;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.constant.MQConstant;
import com.dhx.apicommon.service.InnerUserInterfaceInfoService;
import com.dhx.apicore.constants.RedisConstant;
import com.dhx.apicore.model.DO.UserInterfaceInfoEntity;
import com.dhx.apicore.model.DTO.CallResultDTO;
import com.dhx.apicore.service.UserInterfaceInfoEntityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

import static com.dhx.apicore.constants.InterfaceConstant.DEFAULT_LEFT_NUM;

/**
 * @author adorabled4
 * @className InnerUserInterfaceInfoServiceImpl
 * @date : 2023/04/19/ 14:49
 **/
@DubboService
@Slf4j
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    UserInterfaceInfoEntityService userInterfaceInfoEntityService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    RedissonClient redissonClient;


    @Resource
    RabbitTemplate rabbitTemplate;

    @Override
    public boolean invokeCount(Long userId, Long interfaceId, BaseResponse baseResponse) {
        CallResultDTO callResultDTO = new CallResultDTO(userId, interfaceId, baseResponse);
        // 统计调用情况
        Message message = new Message(JSONUtil.toJsonStr(callResultDTO).getBytes());
        rabbitTemplate.send(MQConstant.CALL_RESULT_EXCHANGE,MQConstant.CALL_RESULT_QUEUE,message);
        // 统计用户可用次数
        String key = RedisConstant.USER_CALL_LEFTNUM_KEY + userId;
        String cachedLeftNum = stringRedisTemplate.opsForValue().get(key);
        long leftNum;
        boolean shouldUpdateCache = false; // 是否需要更新缓存
        if (StringUtils.isEmpty(cachedLeftNum)) {
            // 需要从数据库中查询
            QueryWrapper<UserInterfaceInfoEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId);
            UserInterfaceInfoEntity one = userInterfaceInfoEntityService.getOne(wrapper);
            if (one == null) {
                // 插入新的数据
                UserInterfaceInfoEntity infoEntity = new UserInterfaceInfoEntity();
                infoEntity.setUserId(userId);
                infoEntity.setTotalNum(1);
                infoEntity.setLeftNum(100 - 1);
                leftNum = DEFAULT_LEFT_NUM - 1;
                userInterfaceInfoEntityService.save(infoEntity);
            } else {
                one.setLeftNum(one.getLeftNum() - 1);
                one.setTotalNum(one.getTotalNum() + 1);
                userInterfaceInfoEntityService.updateById(one);
                leftNum = one.getLeftNum() - 1;
            }
            shouldUpdateCache = true;
        } else {
            // redis中已经缓存了
            leftNum = Long.parseLong(cachedLeftNum);
            if (leftNum <= 0) {
                UserInterfaceInfoEntity infoEntity = new UserInterfaceInfoEntity();
                infoEntity.setUserId(userId);
                infoEntity.setLeftNum(0);
                userInterfaceInfoEntityService.updateById(infoEntity);
                shouldUpdateCache = false;
            } else {
                leftNum -= 1;
                shouldUpdateCache = true;
            }
        }
        String lockKey = RedisConstant.LEFTNUM_LOCK_KEY + userId;
        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean tryLock = lock.tryLock(10, TimeUnit.SECONDS);        // 缓存到redis
            log.info("leftNum: {}", leftNum);
            if (tryLock && shouldUpdateCache) { // 只有获取到锁并且需要更新缓存才进行缓存操作
                stringRedisTemplate.opsForValue().set(key, String.valueOf(leftNum), RedisConstant.LEFT_NUM_TTL, TimeUnit.HOURS);
            }
        } catch (InterruptedException e) {
            log.error("获取锁失败{}", e.getMessage());
        } finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return true;
    }


    @Override
    public int getUserLeftNum(Long userId) {
        String key = RedisConstant.USER_CALL_LEFTNUM_KEY + userId;
        String cachedLeftNum = stringRedisTemplate.opsForValue().get(key);
        int leftNum = 100;
        if (StringUtils.isEmpty(cachedLeftNum)) {
            // 需要从数据库中查询
//            RLock lock = redissonClient.getLock("getUserLeftNumRedis:" + interfaceId+":" +userId);
            RLock lock = redissonClient.getLock(RedisConstant.LEFTNUM_LOCK_KEY + userId);
            try {
                lock.lock();
                cachedLeftNum = stringRedisTemplate.opsForValue().get(key); // 再次尝试获取缓存值
                if (StringUtils.isEmpty(cachedLeftNum)) {
                    QueryWrapper<UserInterfaceInfoEntity> wrapper = new QueryWrapper<>();
                    wrapper.eq("user_id", userId);
//                    wrapper.eq("user_id", userId).eq("interface_id", interfaceId);
                    UserInterfaceInfoEntity one = userInterfaceInfoEntityService.getOne(wrapper);
                    if (one == null) {
                        // 直接返回默认的数量
                        return DEFAULT_LEFT_NUM.intValue();
                    }
                    leftNum = one.getLeftNum();
                } else {
                    leftNum = Integer.parseInt(cachedLeftNum);
                }
            } finally {
                lock.unlock();
            }
        } else {
            leftNum = Integer.parseInt(cachedLeftNum);
        }
        return leftNum;
    }

//    @Override
//    public int getUserLeftNum(Long userId, Long interfaceId) {
//        String lockKey = "getUserLeftNum:" + interfaceId + ":" + userId;
//        RLock lock = redissonClient.getLock(lockKey);
//        try {
//            boolean tryLock = lock.tryLock(10, TimeUnit.SECONDS);
//            if (tryLock) {
//                try {
//                    UserInterfaceInfoEntity one = userInterfaceInfoEntityService.query().eq("user_id", userId).eq("interface_id", interfaceId).one();
//                    if (one.getLeftNum() == null || one.getLeftNum() <= 0) {
//                        return 0;
//                    }
//                    return one.getLeftNum();
//                } finally {
//                    lock.unlock();
//                }
//            } else {
//                // 没有获取到锁, 自旋获取锁
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                return getUserLeftNum(userId, interfaceId);
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
