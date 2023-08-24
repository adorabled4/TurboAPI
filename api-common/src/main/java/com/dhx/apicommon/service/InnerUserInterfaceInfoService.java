package com.dhx.apicommon.service;

import com.dhx.apicommon.common.BaseResponse;

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
    boolean invokeCount(Long userId, Long interfaceId, BaseResponse baseResponse);

    /**
     * 获取用户剩余调用次数
     *
     * @param userId      用户id
     * @return int
     */
    int getUserLeftNum(Long userId);
}
