package com.dhx.apicore.service;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicore.model.query.LoginQuery;
import com.dhx.apicore.model.query.QuickLoginEmailRequest;
import com.dhx.apicore.model.query.RegisterQuery;

import javax.servlet.http.HttpServletRequest;

/**
 * @author adorabled4
 * @interface LoginService
 * @date : 2023/12/27/ 19:38
 **/
public interface LoginService {
    /**
     * 邮箱快速登录
     *
     * @param param 参数
     * @return {@link BaseResponse} 返回jwt token
     */
    BaseResponse<String> quickLoginByEmail(QuickLoginEmailRequest param);

    /**
     * 发送验证码
     *
     * @param email   电子邮件
     * @param request 请求
     */
    void sendVerifyCode(String email, HttpServletRequest request);


    /**
     * 用户注册
     *
     * @param param 参数
     * @return {@link BaseResponse}
     */
    BaseResponse<Long> register(RegisterQuery param);

    /**
     * 用户登录
     *
     * @param param 参数
     * @return 返回token
     */
    BaseResponse<String> login(LoginQuery param);
}
