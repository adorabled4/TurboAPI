package com.dhx.apicommon.common;

import com.dhx.apicommon.common.exception.ErrorCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://blog.dhx.icu/"> adorabled4 </a>
 * @className BaseResponse
 * @date : 2023/01/07/ 14:33
 **/
@Data
public class BaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    private int code;

    private T data;//  controller 中的不同的方法返回值的类型不同

    private String message;


    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMsg());
    }


    public BaseResponse(ErrorCode errorCode, String message) {
        this(errorCode.getCode(), null, message);
    }

    /**
     * 正常返回
     * @param data
     * @param <T>
     * @return
     */
    public <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(200, data, "ok");
    }
}
