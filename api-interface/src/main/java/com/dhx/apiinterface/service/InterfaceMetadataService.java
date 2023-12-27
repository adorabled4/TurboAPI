package com.dhx.apiinterface.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apiinterface.gen.InterfaceMetadata;

/**
* @author dhx
* @description 针对表【interface_metadata】的数据库操作Service
* @createDate 2023-12-27 11:21:55
*/
public interface InterfaceMetadataService extends IService<InterfaceMetadata> {

    boolean increDataCount(Long interfaceId);

}
