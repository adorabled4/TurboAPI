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

    /**
     * 调用结果
     */
    private BaseResponse baseResponse;

    public CallResultDTO(Long userId, Long interfaceId, BaseResponse baseResponse) {
        this.userId=userId;
        this.interfaceId=interfaceId;
        this.baseResponse=baseResponse;
    }
}
