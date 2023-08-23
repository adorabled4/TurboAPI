package com.dhx.apicommon.model.bo;

import lombok.Data;

/**
 * @author adorabled4
 * @className UserInterfaceInfo
 * @date : 2023/08/23/ 22:33
 **/
@Data
public class UserInterfaceInfo {
    private long interfaceId;
    private long userId;

    public UserInterfaceInfo(Long id, Long userId) {
        this.interfaceId=id;
        this.userId=userId;
    }
}
