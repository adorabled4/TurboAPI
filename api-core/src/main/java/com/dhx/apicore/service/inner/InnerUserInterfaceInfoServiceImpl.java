package com.dhx.apicore.service.inner;

import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.model.to.InterfaceTo;
import com.dhx.apicommon.service.InnerUserInterfaceInfoService;
import com.dhx.apicore.model.DO.CallResult;
import com.dhx.apicore.model.enums.CallApiStatusEnum;
import com.dhx.apicore.service.CallResultService;
import com.dhx.apicore.service.InterfaceInfoService;
import com.dhx.apicore.service.UserService;
import com.dhx.apicore.util.ThrowUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.slf4j.MDC;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;

import static com.dhx.apicommon.common.BaseResponse.TRACE_ID;

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

    @Resource
    CallResultService callResultService;

    @Resource
    TransactionTemplate transactionTemplate;

    @Override
    @Transactional
    public boolean invokeCount(Long userId, Long interfaceId, Integer cost, boolean isSuccess) {
        try {
            transactionTemplate.execute(status -> {
                userService.reduceCoin(userId, cost);
                interfaceInfoService.increaseCount(interfaceId);
                CallApiStatusEnum statusEnum;
                if (isSuccess) {
                    statusEnum = CallApiStatusEnum.SUCCEED;
                } else {
                    statusEnum = CallApiStatusEnum.FAILED;
                }
                callResultService.updateCallStatus(MDC.get(TRACE_ID), statusEnum);
                return true;
            });
        } catch (RuntimeException e) {
            log.info("更新用户调用统计异常: {}", e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public void createCallResult(InterfaceTo interfaceTo, Long userId) {
        CallResult callResult = new CallResult();
        callResult.setUserId(userId);
        callResult.setInterfaceId(interfaceTo.getId());
        callResult.setStatus(CallApiStatusEnum.RUNNING);
        callResult.setTraceId(MDC.get(TRACE_ID));
        if (interfaceTo.isAsync()) {
            callResult.setIsAsync(true);
        }
        ThrowUtil.throwIf(!callResultService.save(callResult), ErrorCode.OPERATION_ERROR, "保存callResult失败");
    }
}
