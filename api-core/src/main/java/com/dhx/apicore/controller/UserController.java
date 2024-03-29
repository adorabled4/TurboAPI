package com.dhx.apicore.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.common.annotation.AuthCheck;
import com.dhx.apicore.common.annotation.SysLog;
import com.dhx.apicore.common.constant.UserConstant;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.query.UserUpdateQuery;
import com.dhx.apicore.model.vo.UserVo;
import com.dhx.apicore.service.UserService;
import com.dhx.apicommon.util.ThrowUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author adorabled4
 * @className UserController
 * @date : 2023/04/10/ 14:19
 **/
@RestController
@RequestMapping("/user")
@Tag(name = "用户相关接口")
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/{id}/info")
    @Operation(summary = "通过用户id获取当前信息")
    public BaseResponse<UserVo> getUserById(@PathVariable("id") Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/list/info")
    @Operation(summary = "获取用户列表")
    public BaseResponse<List<UserVo>> getUserList(PageQuery pageQuery) {
        return userService.getUserList(pageQuery);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "通过ID删除用户")
    @Profile("dev")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> deleteUserById(@PathVariable("id") Long userId) {
        return ResultUtil.success(userService.deleteUserById(userId));
    }

    @DeleteMapping("/delete/account")
    @Profile("dev")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    @Operation(summary = "通过账户删除用户")
    public BaseResponse<Boolean> deleteUserByAccount(@RequestParam("account") String userAccount) {
        ThrowUtil.throwIf(StringUtils.isBlank(userAccount), ErrorCode.PARAMS_ERROR);
        return ResultUtil.success(userService.deleteUserByAccount(userAccount));
    }

    @PostMapping("/add")
    @Operation(summary = "添加用户")
    public BaseResponse<Long> addUser(@RequestBody @Validated UserVo userVo) {
        return userService.addUser(userVo);
    }

    @GetMapping("/current")
    @Operation(summary = "获取当前用户信息")
    public BaseResponse<UserVo> currentUser() {
        return ResultUtil.success(userService.getCurrentUser());
    }


    @PostMapping("/update")
    @Operation(summary = "更新用户信息")
    @SysLog("更新用户")
    public BaseResponse<String> updateUserInfo(@RequestPart(value = "file", required = false) MultipartFile multipartFile,
                                       UserUpdateQuery param) {
        userService.updateUserInfO(multipartFile,param);
        return ResultUtil.success("更新成功!");
    }
}
