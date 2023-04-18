package com.dhx.apisdk.model;

import com.dhx.apisdk.model.exception.ErrorCode;
import lombok.Data;

/**
 * @author adorabled4
 * @className BaseResponse
 * @date : 2023/04/10/ 14:21
 **/
@Data
public class BaseResponse<T> {


    private int code;

    private T data;//  controller 中的不同的方法返回值的类型不同

    private String message;

    private String description;


    public BaseResponse(int code, T data, String message, String description) {
        this.code = code;
        this.data = data;
        this.message = message;
        this.description = description;
    }

    public BaseResponse(int code, T data, String description) {
        this(code, data, "", description);
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage(), errorCode.getDescription());
    }

    public BaseResponse(ErrorCode errorCode, String description) {
        this(errorCode.getCode(), null, errorCode.getMessage(), description);
    }


    public BaseResponse(ErrorCode errorCode, String message, String description) {
        this(errorCode.getCode(), null, message, description);
    }


    /**
     * 正常返回
     *
     * @param data
     * @param <T>
     * @return
     */
    public <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(200, data, "ok", "");
    }
}

