package com.dhx.apiinterface.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apiinterface.gen.InterfaceMetadata;
import com.dhx.apiinterface.mapper.InterfaceMetadataMapper;
import com.dhx.apiinterface.service.InterfaceMetadataService;
import org.springframework.stereotype.Service;

/**
 * @author dhx
 * @description 针对表【interface_metadata】的数据库操作Service实现
 * @createDate 2023-12-27 11:21:55
 */
@Service
public class InterfaceMetadataServiceImpl extends ServiceImpl<InterfaceMetadataMapper, InterfaceMetadata>
        implements InterfaceMetadataService {

    @Override
    public boolean increDataCount(Long interfaceId) {
        return update().setSql("data_count = data_count+1").eq("interface_id", interfaceId).update();
    }

    @Override
    public InterfaceMetadata findInterfaceByPath(String requestURI) {
        return query().eq("call_path", requestURI).one();
    }
}




