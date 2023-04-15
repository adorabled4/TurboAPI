package com.dhx.apisdk.model.exception;

import lombok.Data;

/**
 * @author adorabled4
 * @className BusinessException
 * @date : 2023/04/10/ 14:23
 **/
@Data
public class BusinessException extends RuntimeException{

    private static final long serialVersionUID = 165474231423634L;
    /**
     * 错误码
     */
    private int code=500;


    private String message;
    /**
     * 错误描述
     */
    private String description;

    public BusinessException(int code,String description,String message){
        super(message);// 错误信息
        this.code=code;
        this.description=description;
    }

}