package com.dhx.apiinterface.service;

import com.dhx.apicommon.common.BaseResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author adorabled4
 * @interface InvokeInterfaceServiceV2
 * @date : 2023/12/27/ 15:22
 **/
public interface InvokeInterfaceServiceV2 {
    BaseResponse genTakeOutComment(String recipe, HttpServletRequest request);

}
