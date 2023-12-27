package com.dhx.apiinterface.service.impl;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.gen.InterfaceMetadata;
import com.dhx.apiinterface.service.AigcContentService;
import com.dhx.apiinterface.service.InterfaceMetadataService;
import com.dhx.apiinterface.service.InvokeInterfaceServiceV2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author adorabled4
 * @className InvokeInterfaceServiceV2Impl
 * @date : 2023/12/27/ 15:23
 **/
@Service
public class InvokeInterfaceServiceV2Impl implements InvokeInterfaceServiceV2 {
    @Resource
    AigcContentService aigcContentService;

    @Resource
    InterfaceMetadataService interfaceMetadataService;

    @Override
    public BaseResponse genTakeOutComment(String recipe, HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        InterfaceMetadata interfaceMetadata = interfaceMetadataService.findInterfaceByPath(requestURI);
        String result = aigcContentService.genTakeOutComment(interfaceMetadata.getInterfaceId(), recipe);
        return ResultUtil.success(result);
    }
}
