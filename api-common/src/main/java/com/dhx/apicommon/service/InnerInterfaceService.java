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
     * @param callPath
     * @return
     */
    InterfaceTo getInterfaceInfo(String  callPath , String method);

    /**
     * 校验回调接口配置
     *
     * @param userId 用户id
     * @param interfaceId     interfaceId
     * @return boolean
     */
    boolean checkCallBackConfig(Long userId, Long interfaceId);
}
