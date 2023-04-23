package com.dhx.apicore.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dhx.apicommon.service.InnerUserInterfaceInfoService;
import com.dhx.apicore.model.DO.UserInterfaceInfoEntity;
import com.dhx.apicore.service.UserInterfaceInfoEntityService;
import org.apache.dubbo.config.annotation.DubboService;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author adorabled4
 * @className InnerUserInterfaceInfoServiceImpl
 * @date : 2023/04/19/ 14:49
 **/
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    UserInterfaceInfoEntityService userInterfaceInfoEntityService;

    @Resource
    RedissonClient redissonClient;

    @Override
    public boolean invokeCount(Long userId, Long interfaceId) {
        String lockKey = "invokeCount:" + interfaceId + ":" + userId;
        RLock lock = redissonClient.getLock(lockKey);
//        synchronized (Object.class){ // 使用 synchronized 导致锁的力度过大
//             tryLock = lock.tryLock();
//        }
        try {
            boolean tryLock= lock.tryLock(10, TimeUnit.SECONDS);
            if (tryLock) {
                try{
                    Long count = userInterfaceInfoEntityService.query().eq("user_id", userId).eq("interface_id", interfaceId).count();
                    if (count > 0) {
                        lock.lock(10, TimeUnit.SECONDS);
                        UpdateWrapper<UserInterfaceInfoEntity> wrapper = new UpdateWrapper<>();
                        wrapper.eq("user_id", userId).eq("interface_id", interfaceId);
                        wrapper.setSql("left_num=left_num-1 , total_num=total_num+1");
                        return userInterfaceInfoEntityService.update(wrapper);
                    } else {
                        // 插入新的数据
                        UserInterfaceInfoEntity infoEntity = new UserInterfaceInfoEntity();
                        infoEntity.setUserId(userId);
                        infoEntity.setInterfaceId(interfaceId);
                        infoEntity.setTotalNum(1);
                        infoEntity.setLeftNum(100 - 1);
                        infoEntity.setStatus(1);
                        return userInterfaceInfoEntityService.save(infoEntity);
                    }
                }finally {
                    lock.unlock();
                }
            } else {
                // 没有获取到锁, 自旋获取锁
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return invokeCount(userId, interfaceId);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int getUserLeftNum(Long userId, Long interfaceId) {
        String lockKey = "getUserLeftNum:" + interfaceId + ":" + userId;
        RLock lock = redissonClient.getLock(lockKey);
        try {
            boolean tryLock = lock.tryLock(10, TimeUnit.SECONDS);
            if (tryLock) {
                try {
                    UserInterfaceInfoEntity one = userInterfaceInfoEntityService.query().eq("user_id", userId).eq("interface_id", interfaceId).one();
                    if (one.getLeftNum() == null || one.getLeftNum() <= 0) {
                        return 0;
                    }
                    return one.getLeftNum();
                } finally {
                    lock.unlock();
                }
            } else {
                // 没有获取到锁, 自旋获取锁
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return getUserLeftNum(userId, interfaceId);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
