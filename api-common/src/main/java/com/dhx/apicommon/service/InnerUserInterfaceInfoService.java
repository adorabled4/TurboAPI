package com.dhx.apicommon.service;

/**
 * @author adorabled4
 * @className InnerUserInterfaceInfoService
 * @date : 2023/04/19/ 14:44
 **/
public interface InnerUserInterfaceInfoService {

    boolean invokeCount(Long userId, Long interfaceId);

    int getUserLeftNum(Long userId, Long interfaceId);
}
