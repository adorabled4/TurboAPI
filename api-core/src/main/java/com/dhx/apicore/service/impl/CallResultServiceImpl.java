package com.dhx.apicore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicore.model.DO.CallResult;
import com.dhx.apicore.model.enums.CallApiStatusEnum;
import com.dhx.apicore.service.CallResultService;
import com.dhx.apicore.mapper.CallResultMapper;
import com.dhx.apicommon.util.ThrowUtil;
import org.springframework.stereotype.Service;

/**
* @author dhx
* @description 针对表【call_result】的数据库操作Service实现
* @createDate 2024-01-01 14:18:57
*/
@Service
public class CallResultServiceImpl extends ServiceImpl<CallResultMapper, CallResult>
    implements CallResultService{

    @Override
    public void updateCallStatus(String traceId, CallApiStatusEnum statusEnum) {
        boolean update = update().eq("trace_id", traceId).set("status", statusEnum.ordinal()).update();
        ThrowUtil.throwIf(!update, ErrorCode.OPERATION_ERROR,"更新callResult状态失败,statusEnum:%s".formatted(statusEnum));

    }
}




