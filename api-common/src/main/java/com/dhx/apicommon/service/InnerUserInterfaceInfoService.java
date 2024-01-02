package com.dhx.apicommon.service;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.model.to.InterfaceTo;

/**
 * @author adorabled4
 * @className InnerUserInterfaceInfoService
 * @date : 2023/04/19/ 14:44
 **/
public interface InnerUserInterfaceInfoService {

    /**
     * 接口调用统计
     *
     * @param userId      用户id
     * @param interfaceId 接口id
     * @return boolean
     */
    boolean invokeCount(Long userId, Long interfaceId, Integer cost,boolean isSuccess);

    /**
     * 预先创建调用上下文信息
     *
     * @param interfaceTo 接口
     * @param userId      用户id
     */
    void createCallResult(InterfaceTo interfaceTo,Long userId);
}
