package com.dhx.apiinterface.service.impl;

import com.dhx.apicommon.model.query.AddInterfaceInfoQuery;
import com.dhx.apicommon.service.api.ApiInterfaceService;
import com.dhx.apiinterface.gen.InterfaceMetadata;
import com.dhx.apiinterface.service.InterfaceMetadataService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className InnerApiInterfaceServiceImpl
 * @date : 2023/12/27/ 14:35
 **/
@DubboService
public class InnerApiInterfaceServiceImpl implements ApiInterfaceService {
    @Resource
    InterfaceMetadataService interfaceMetadataService;
    @Override
    public boolean addInterfaceData(AddInterfaceInfoQuery query) {
        InterfaceMetadata metadata = InterfaceMetadata.builder()
                .interfaceName(query.getInterfaceName())
                .interfaceId(query.getInterfaceId())
                .isAigc(query.getIsAigc())
                .callPath(query.getCallPath())
                .build();
        return interfaceMetadataService.saveOrUpdate(metadata);
    }
}
