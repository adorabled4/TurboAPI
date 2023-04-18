package com.dhx.apisdk.util;


import com.dhx.apisdk.model.BaseResponse;
import com.dhx.apisdk.model.exception.ErrorCode;

/**
 * @author adorabled4
 * @className ResultUtil
 * @date : 2023/04/10/ 14:24
 **/
public class ResultUtil {
    private static final long serialVersionUID = 164567353L;
    /**
     * 正常返回
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data){
        return new BaseResponse<T>(200,data,"ok","");
    }


    /**
     * 出现错误
     * @param errorCode
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode){
        return new BaseResponse<T>(errorCode);
    }

    /**
     * 失败
     *
     * @param code
     * @param message
     * @param description
     * @return
     */
    public static BaseResponse error(int code, String message, String description) {
        return new BaseResponse(code, null, message, description);
    }

    /**
     *
     * @param errorCode
     * @param message 错误信息
     * @param description 描述
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse(errorCode.getCode(), null, message, description);
    }

    /**
     *
     * @param errorCode 错误码
     * @param description 描述
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> error(ErrorCode errorCode,String description){
        return new BaseResponse<T>(errorCode,description);
    }
    /**
     *
     * @return
     */
    public static <T> BaseResponse<T> error(int code , String description){
        return new BaseResponse<T>(code,null,"error",description);
    }

    /**
     *
     * @return
     */
    public static <T> BaseResponse<T> error(){
        return new BaseResponse<T>(ErrorCode.SYSTEM_ERROR);
    }

    public static BaseResponse success() {
        return new BaseResponse(200,null,"success");
    }
}

