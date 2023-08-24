package com.dhx.apicore.model.enums;

import com.dhx.apicore.model.DTO.CallResultDTO;

/**
 * @author adorabled4
 * @className CallResultEnum 调用结果
 * @date : 2023/08/24/ 11:09
 **/
public enum CallResultEnum {

    /**
     * 成功
     */
    SUCCEED(1),

    /**
     * 失败
     */
    FAILED(0);

    public int getIsSucceed() {
        return isSucceed;
    }

    int isSucceed;


    CallResultEnum(int isSucceed) {
        this.isSucceed = isSucceed;
    }
}
