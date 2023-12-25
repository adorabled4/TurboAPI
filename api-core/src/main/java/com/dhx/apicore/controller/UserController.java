package com.dhx.apicore.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.common.annotation.AuthCheck;
import com.dhx.apicore.common.constant.UserConstant;
import com.dhx.apicore.model.query.LoginQuery;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.query.RegisterQuery;
import com.dhx.apicore.model.vo.UserVo;
import com.dhx.apicore.service.UserService;
import com.dhx.apicore.util.ThrowUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author adorabled4
 * @className UserController
 * @date : 2023/04/10/ 14:19
 **/
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关接口")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public BaseResponse<String> login(@Valid @RequestBody LoginQuery param) {
        return userService.login(param);
    }

    @PostMapping("/register")
    @ApiOperation("用户注册")
    public BaseResponse<Long> register(@Valid @RequestBody RegisterQuery param) {
        return userService.register(param);
    }

    @GetMapping("/{id}/info")
    @ApiOperation("通过用户id获取当前信息")
    public BaseResponse<UserVo> getUserById(@PathVariable("id") Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/list/info")
    @ApiOperation("获取用户列表")
    public BaseResponse<List<UserVo>> getUserList(PageQuery pageQuery) {
        return userService.getUserList(pageQuery);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过ID删除用户")
    @Profile("dev")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUserById(@PathVariable("id") Long userId) {
        return ResultUtil.success(userService.deleteUserById(userId));
    }

    @DeleteMapping("/delete/account")
    @Profile("dev")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUserByAccount(@RequestParam("account") String userAccount) {
        ThrowUtil.throwIf(StringUtils.isBlank(userAccount), ErrorCode.PARAMS_ERROR);
        return ResultUtil.success(userService.deleteUserByAccount(userAccount));
    }

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public BaseResponse<Long> addUser(@RequestBody @Validated UserVo userVo) {
        return userService.addUser(userVo);
    }

    @GetMapping("/current")
    @ApiOperation("获取当前用户信息")
    public BaseResponse<UserVo> currentUser() {
        return ResultUtil.success(userService.getCurrentUser());
    }
}
