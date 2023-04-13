package com.dhx.apicore.constants;

/**
 * @author adorabled4
 * @className UserConstant
 * @date : 2023/04/10/ 18:09
 **/
public class UserConstant {

    /**
     * 用户名正则
     */
    public static final String USER_NAME_REGEX = "^[\\u4e00-\\u9fa5a-zA-Z0-9]{6,12}$";

    /**
     * 密码正则
     */
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[\\s\\S]{8,16}$";
}
