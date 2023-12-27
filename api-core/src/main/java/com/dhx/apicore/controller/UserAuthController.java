package com.dhx.apicore.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.model.query.QuickLoginEmailRequest;
import com.dhx.apicore.service.LoginService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author adorabled4
 * @className UserAuthController
 * @date : 2023/12/27/ 17:48
 **/
@RestController
public class UserAuthController {

    @Resource
    LoginService loginService;

    @PostMapping("/login/email/quick")
    @ApiOperation("邮箱验证码快速登录")
    public BaseResponse<String> quickLogin(@Valid @RequestBody QuickLoginEmailRequest param) {
        return loginService.quickLoginByEmail(param);
    }

    /**
     * 发送验证码
     *
     * @param email 电子邮件
     * @return {@link BaseResponse}<{@link String}>
     */
    @GetMapping("/send/email/code")
    @ApiOperation("发送验证码")
    public BaseResponse<String> sendVerifyCode(@RequestParam("email") String email, HttpServletRequest request) {
       loginService.sendVerifyCode(email,request);
        return ResultUtil.success("发送成功!");
    }

}
