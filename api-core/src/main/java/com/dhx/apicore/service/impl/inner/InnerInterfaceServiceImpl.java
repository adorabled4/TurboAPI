package com.dhx.apicore.service.impl.inner;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.spring.util.BeanFactoryUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.model.to.InterfaceTo;
import com.dhx.apicommon.service.InnerInterfaceService;
import com.dhx.apicore.model.DO.InterfaceEntity;
import com.dhx.apicore.service.InterfaceEntityService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className InnerInterfaceServiceImpl
 * @date : 2023/04/19/ 14:36
 **/
@DubboService
public class InnerInterfaceServiceImpl implements InnerInterfaceService {

    @Resource
    InterfaceEntityService interfaceEntityService;

    @Override
    public InterfaceTo getInterfaceInfo(String url , String method) {
        if(StringUtils.isAnyBlank(method,url)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceEntity> wrapper = new QueryWrapper<>();
        wrapper.like("url", url);
        wrapper.eq("method", method);
        InterfaceEntity one = interfaceEntityService.getOne(wrapper);
        InterfaceTo interfaceTo = BeanUtil.copyProperties(one, InterfaceTo.class);
        return interfaceTo;
    }
}
