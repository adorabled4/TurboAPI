package com.dhx.apicommon.common.exception;

/**
 * @author <a href="https://blog.dhx.icu/"> adorabled4 </a>
 * @className ErrorCode <a href="https://developer.mozilla.org/zh-CN/docs/Web/HTTP"> 关于HTTP状态码 </a>
 * @date : 2023/01/07/ 14:34
 **/
public enum ErrorCode {
    SUCCESS(200,"ok"),
    //HTTP状态码本身就是500，为什么500，因为你的业务里面抛异常 , 但是不应该让前端出现500，因为我们刚刚自己定义了一个业务异常，它应该返回40000
    PARAMS_ERROR(400,"请求参数错误"),
    NULL_ERROR(400,"请求数据为空"),
    NOT_LOGIN(401,"未登录"),
    NO_AUTH_ERROR(403,"无权限"),

    FORBIDDEN_ERROR(403,"禁止访问"),

    NOT_FOUND(404,"访问路径错误"),
    NOT_FOUND_ERROR(404, "请求数据不存在"),
    UPLOAD_ERROR(406,"上传失败"),
    TOO_MANY_REQUEST(429,"请求过于频繁"),
    SYSTEM_ERROR(500,"服务器内部异常"),

    OPERATION_ERROR(50001, "操作失败"),
    POOR_LEFT_NUM(50002,"剩余调用次数不足!")

    ;
    final int code;
    final String msg;
    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
