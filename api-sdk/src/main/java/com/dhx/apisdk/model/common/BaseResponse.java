package com.dhx.apisdk.model.common;

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

}


