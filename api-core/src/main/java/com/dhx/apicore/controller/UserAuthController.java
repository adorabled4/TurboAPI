package com.dhx.apicore.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.model.query.EmailVerifyCodeRequest;
import com.dhx.apicore.model.query.LoginQuery;
import com.dhx.apicore.model.query.RegisterQuery;
import com.dhx.apicore.service.LoginService;
import com.dhx.apicore.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "用户权限")
public class UserAuthController {

    @Resource
    LoginService loginService;

    @Resource
    UserService userService;

    @PostMapping("/login")
    @Operation(summary = "用户登录")
    public BaseResponse<String> login(@Valid @RequestBody LoginQuery param) {
        return loginService.login(param);
    }

    @PostMapping("/register")
    @Operation(summary = "用户注册")
    public BaseResponse<Long> register(@Valid @RequestBody RegisterQuery param) {
        return loginService.register(param);
    }

    @PostMapping("/bind/email")
    @Operation(summary = "绑定邮箱")
    public BaseResponse<String> bindEmail(@Valid @RequestBody EmailVerifyCodeRequest param) {
        loginService.bindEmail(param);
        return ResultUtil.success("绑定成功!!");
    }

    @PostMapping("/login/email/quick")
    @Operation(summary = "邮箱验证码快速登录")
    public BaseResponse<String> quickLogin(@Valid @RequestBody EmailVerifyCodeRequest param) {
        return loginService.quickLoginByEmail(param);
    }

    @GetMapping("/refresh/key")
    @Operation(summary = "更新key")
    public BaseResponse<String> refreshUserKey() {
        loginService.refreshKey();
        return ResultUtil.success("获取成功!");
    }

    @GetMapping("/send/email/code")
    @Operation(summary = "发送验证码")
    public BaseResponse<String> sendVerifyCode(@RequestParam("email") String email, HttpServletRequest request) {
        loginService.sendVerifyCode(email, request);
        return ResultUtil.success("发送成功!");
    }

    @PostMapping("/update/pwd")
    @Operation(summary = "更新用户密码")
    public BaseResponse<String> updateUserPassword(@RequestParam("password")String password){
        userService.updateUserPwd(password);
        return ResultUtil.success("更新成功!");
    }

}
