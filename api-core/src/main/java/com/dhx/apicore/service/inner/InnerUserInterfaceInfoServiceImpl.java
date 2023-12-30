package com.dhx.apicore.service.inner;

import com.dhx.apicommon.service.InnerUserInterfaceInfoService;
import com.dhx.apicore.service.InterfaceInfoService;
import com.dhx.apicore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className InnerUserInterfaceInfoServiceImpl
 * @date : 2023/04/19/ 14:49
 **/
@Slf4j
@DubboService
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {
    @Resource
    UserService userService;

    @Resource
    InterfaceInfoService interfaceInfoService;

    @Override
    @Transactional
    public boolean invokeCount(Long userId, Long interfaceId, Integer cost) {
        try {
            userService.reduceCoin(userId, cost);
            interfaceInfoService.increaseCount(interfaceId);
        } catch (RuntimeException e) {
            log.info("更新用户调用统计异常: {}", e.getMessage());
            return false;
        }
        return true;
    }


}
