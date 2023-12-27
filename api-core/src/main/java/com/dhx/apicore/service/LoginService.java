package com.dhx.apicore.service;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicore.model.query.QuickLoginEmailRequest;

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

    void sendVerifyCode(String email, HttpServletRequest request);
}
