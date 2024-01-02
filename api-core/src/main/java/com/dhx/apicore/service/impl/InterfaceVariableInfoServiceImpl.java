package com.dhx.apicore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicore.mapper.InterfaceVariableInfoMapper;
import com.dhx.apicore.model.DO.InterfaceVariableInfoEntity;
import com.dhx.apicore.service.InterfaceVariableInfoService;
import com.dhx.apicommon.util.ThrowUtil;
import org.springframework.stereotype.Service;

/**
 * @author dhx
 * @description 针对表【interface_variable_info】的数据库操作Service实现
 * @createDate 2023-04-12 09:38:35
 */
@Service
public class InterfaceVariableInfoServiceImpl extends ServiceImpl<InterfaceVariableInfoMapper, InterfaceVariableInfoEntity>
        implements InterfaceVariableInfoService {

    @Override
    public InterfaceVariableInfoEntity findById(Long id) {
        InterfaceVariableInfoEntity variableInfo = getById(id);
        ThrowUtil.throwIf(variableInfo == null, ErrorCode.NOT_FOUND_ERROR);
        return variableInfo;
    }
}




