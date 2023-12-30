package com.dhx.apicore.model.DTO;

import com.dhx.apicommon.common.BaseResponse;
import lombok.Data;

import java.io.Serializable;

/**
 * @author adorabled4
 * @className CallResult
 * @date : 2023/08/24/ 10:54
 **/
@Data
public class CallResultDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 接口id
     */
    private Long interfaceId;


    public CallResultDTO(Long userId, Long interfaceId) {
        this.userId=userId;
        this.interfaceId=interfaceId;
    }
}
