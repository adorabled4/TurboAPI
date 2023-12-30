package com.dhx.apicore.service.inner;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.model.to.InterfaceTo;
import com.dhx.apicommon.service.InnerInterfaceService;
import com.dhx.apicore.model.DO.InterfaceInfoEntity;
import com.dhx.apicore.model.DO.InterfaceVariableInfoEntity;
import com.dhx.apicore.service.InterfaceInfoService;
import com.dhx.apicore.service.InterfaceVariableInfoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className InnerInterfaceServiceImpl
 * @date : 2023/04/19/ 14:36
 **/
@DubboService
@Slf4j
public class InnerInterfaceServiceImpl implements InnerInterfaceService {

    @Resource
    InterfaceInfoService interfaceInfoService;

    @Resource
    InterfaceVariableInfoService interfaceVariableInfoService;

    @Override
    public InterfaceTo getInterfaceInfo(String url , String method) {
        if(StringUtils.isAnyBlank(method,url)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfoEntity> wrapper = new QueryWrapper<>();
        wrapper.like("call_path", url);
        wrapper.eq("request_method", method);
        try{
            InterfaceInfoEntity interfaceInfo = interfaceInfoService.getOne(wrapper);
            InterfaceVariableInfoEntity variableInfo = interfaceVariableInfoService.findById(interfaceInfo.getId());
            InterfaceTo interfaceTo = BeanUtil.copyProperties(interfaceInfo, InterfaceTo.class);
            BeanUtil.copyProperties(variableInfo,interfaceTo);
            return interfaceTo;
        }catch (RuntimeException e){
            log.info("获取接口信息失败: {}",e.getMessage());
        }
        return null;
    }

}
