package com.dhx.apicore.service;

import com.dhx.apicore.model.DO.CallResult;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apicore.model.enums.CallApiStatusEnum;

/**
* @author dhx
* @description 针对表【call_result】的数据库操作Service
* @createDate 2024-01-01 14:18:57
*/
public interface CallResultService extends IService<CallResult> {

    void updateCallStatus(String traceId, CallApiStatusEnum statusEnum);
}
