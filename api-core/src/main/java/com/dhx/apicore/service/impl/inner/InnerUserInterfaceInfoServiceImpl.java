package com.dhx.apicore.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhx.apicommon.service.InnerUserInterfaceInfoService;
import com.dhx.apicore.constants.RedisConstant;
import com.dhx.apicore.model.DO.UserInterfaceInfoEntity;
import com.dhx.apicore.service.UserInterfaceInfoEntityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;
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
    ThreadPoolExecutor threadPoolExecutor;

//    @Override
//    public boolean invokeCount(Long userId, Long interfaceId) {
//        String lockKey = "invokeCount:" + interfaceId + ":" + userId;
//        RLock lock = redissonClient.getLock(lockKey);
////        synchronized (Object.class){ // 使用 synchronized 导致锁的力度过大
////             tryLock = lock.tryLock();
////        }
//        try {
//            boolean tryLock= lock.tryLock(10, TimeUnit.SECONDS);
////            lock.lock(); // tryLock 已经上锁了
//            if (tryLock) {
//                try{
//                    Long count = userInterfaceInfoEntityService.query().eq("user_id", userId).eq("interface_id", interfaceId).count();
//                    if (count > 0) {
//                        lock.lock(10, TimeUnit.SECONDS);
//                        UpdateWrapper<UserInterfaceInfoEntity> wrapper = new UpdateWrapper<>();
//                        wrapper.eq("user_id", userId).eq("interface_id", interfaceId);
//                        wrapper.setSql("left_num=left_num-1 , total_num=total_num+1");
//                        return userInterfaceInfoEntityService.update(wrapper);
//                    } else {
//                        // 插入新的数据
//                        UserInterfaceInfoEntity infoEntity = new UserInterfaceInfoEntity();
//                        infoEntity.setUserId(userId);
//                        infoEntity.setInterfaceId(interfaceId);
//                        infoEntity.setTotalNum(1);
//                        infoEntity.setLeftNum(100 - 1);
//                        infoEntity.setStatus(1);
//                        return userInterfaceInfoEntityService.save(infoEntity);
//                    }
//                }finally {
//                    lock.unlock();
//                }
//            } else {
//                // 没有获取到锁, 自旋获取锁
//                return invokeCount(userId, interfaceId);
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }


    @Override
    public boolean invokeCount(Long userId, Long interfaceId) {
        String key = RedisConstant.USER_INTERFACE_INFO_PREFIX + interfaceId+":" +userId;
        String cachedLeftNum  = stringRedisTemplate.opsForValue().get(key);
        long leftNum=100;
        boolean shouldUpdateCache = false; // 是否需要更新缓存
        if(StringUtils.isEmpty(cachedLeftNum)){
            // 需要从数据库中查询
            QueryWrapper<UserInterfaceInfoEntity> wrapper = new QueryWrapper<>();
            wrapper.eq("user_id", userId).eq("interface_id", interfaceId);
            UserInterfaceInfoEntity one = userInterfaceInfoEntityService.getOne(wrapper);
            if(one==null){
                // 插入新的数据
                UserInterfaceInfoEntity infoEntity = new UserInterfaceInfoEntity();
                infoEntity.setUserId(userId);
                infoEntity.setInterfaceId(interfaceId);
                infoEntity.setTotalNum(1);
                infoEntity.setLeftNum(100 - 1);
                infoEntity.setStatus(1);
                leftNum=DEFAULT_LEFT_NUM-1;
            }else{
                one.setLeftNum(one.getLeftNum()-1);
                one.setTotalNum(one.getTotalNum()+1);
                userInterfaceInfoEntityService.updateById(one);
                leftNum=one.getLeftNum()-1;
            }
        }else{
            // redis中已经缓存了
            leftNum = Long.parseLong(cachedLeftNum );
            if (leftNum <= 0) {
                shouldUpdateCache = false;
            } else {
                shouldUpdateCache = true;
            }
        }
        String lockKey = "invokeCount:" + interfaceId + ":" + userId;
        RLock lock = redissonClient.getLock(lockKey);
        try{
            boolean tryLock= lock.tryLock(10, TimeUnit.SECONDS);        // 缓存到redis
            if (tryLock && shouldUpdateCache) { // 只有获取到锁并且需要更新缓存才进行缓存操作
                stringRedisTemplate.opsForValue().set(key, String.valueOf(leftNum), RedisConstant.LEFT_NUM_TTL, TimeUnit.HOURS);
            }
        }catch (InterruptedException e) {
            log.error("获取锁失败{}",e.getMessage());
        }finally {
            if (lock.isHeldByCurrentThread()) {
                lock.unlock();
            }
        }
        return true;
    }


    @Override
    public int getUserLeftNum(Long userId, Long interfaceId) {
        String key = RedisConstant.USER_INTERFACE_INFO_PREFIX + interfaceId+":" +userId;
        String cachedLeftNum  = stringRedisTemplate.opsForValue().get(key);
        int leftNum=100;
        if(StringUtils.isEmpty(cachedLeftNum)){
            // 需要从数据库中查询
            RLock lock = redissonClient.getLock("getUserLeftNumRedis:" + interfaceId+":" +userId);
            try {
                lock.lock();
                cachedLeftNum  = stringRedisTemplate.opsForValue().get(key); // 再次尝试获取缓存值
                if(StringUtils.isEmpty(cachedLeftNum)){
                    QueryWrapper<UserInterfaceInfoEntity> wrapper = new QueryWrapper<>();
                    wrapper.eq("user_id", userId).eq("interface_id", interfaceId);
                    UserInterfaceInfoEntity one = userInterfaceInfoEntityService.getOne(wrapper);
                    if(one==null){
                        // 直接返回默认的数量
                        return DEFAULT_LEFT_NUM.intValue();
                    }
                    leftNum = one.getLeftNum();
                } else {
                    leftNum = Integer.parseInt(cachedLeftNum );
                }
            } finally {
                lock.unlock();
            }
        } else {
            leftNum = Integer.parseInt(cachedLeftNum );
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
