package com.dhx.apisdk.model.exception;

/**
 * @author adorabled4
 * @className ErrorCode
 * @date : 2023/04/10/ 14:21
 **/
public enum ErrorCode {
    SUCCESS(200,"ok",""),
    //HTTP状态码本身就是500，为什么500，因为你的业务里面抛异常 , 但是不应该让前端出现500，因为我们刚刚自己定义了一个业务异常，它应该返回40000
    PARAMS_ERROR(40000,"error","请求参数错误"),
    NULL_ERROR(40001,"error","请求数据为空"),
    NOT_LOGIN(40100,"error","未登录"),
    NO_AUTH(40101,"error","无权限"),
    SYSTEM_ERROR(500000,"error","服务器内部异常"),
    NOT_FOUND(404000,"error","访问路径错误")
    ;
    final int code;
    final String message;
    final String description;
    ErrorCode(int code, String message , String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}

