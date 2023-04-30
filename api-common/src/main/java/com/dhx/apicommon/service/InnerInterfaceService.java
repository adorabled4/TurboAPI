package com.dhx.apicommon.service;

import com.dhx.apicommon.model.to.InterfaceTo;

/**
 * @author adorabled4
 * @className InnerInterfaceService
 * @date : 2023/04/19/ 14:34
 **/
public interface InnerInterfaceService {

    /**
     * 获取接口信息
     * @param method
     * @param url
     * @return
     */
    InterfaceTo getInterfaceInfo(String  url , String method);


    /**
     * 接口调用次数+1
     * @param interfaceId
     */
    void interfaceCallCount(long interfaceId);
}
