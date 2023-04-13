package com.dhx.apicore.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.model.param.LoginParam;
import com.dhx.apicore.model.param.RegisterParam;
import com.dhx.apicore.model.vo.UserVo;
import com.dhx.apicore.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    public BaseResponse login(@Valid @RequestBody LoginParam param){
        if(param==null){
            return ResultUtil.error(ErrorCode.PARAMS_ERROR);
        }
        String userName = param.getUserName();
        String password = param.getPassword();
        if(password==null ||userName==null){
            return ResultUtil.error(ErrorCode.NULL_ERROR);
        }
        return userService.login(userName,password);
    }
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public BaseResponse register(@Valid @RequestBody RegisterParam param){
        if(param==null){
            return ResultUtil.error(ErrorCode.PARAMS_ERROR);
        }
        String userName = param.getUserName();
        String password = param.getPassword();;
        String checkPassword = param.getCheckPassword();;
        return userService.register(userName,password,checkPassword);
    }

    @GetMapping("/{id}")
    @ApiOperation("通过用户id获取当前信息")
    public BaseResponse<UserVo> getUserById(@PathVariable("id") Long userId){
        if(userId==null || userId<0){
            return ResultUtil.error(ErrorCode.PARAMS_ERROR);
        }
        return userService.getUserById(userId);
    }
    @GetMapping("/list")
    @ApiOperation("获取用户列表")
    public BaseResponse<List<UserVo>> getUserList(
            @RequestParam(value = "pageSize",defaultValue = "5")int pageSize,
            @RequestParam(value = "current",defaultValue ="1")int current){
        return userService.getUserList(pageSize,current);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过ID删除用户")
    public BaseResponse<Boolean> deleteUserById(@PathVariable("id") Long userId){
        if(userId==null || userId<0){
            return ResultUtil.error(ErrorCode.PARAMS_ERROR);
        }
        return userService.deleteUserById(userId);
    }

    @PostMapping("/add")
    @ApiOperation("添加用户")
    public BaseResponse addUser(@RequestBody UserVo userVo){
        if(userVo==null){
            return ResultUtil.error(ErrorCode.PARAMS_ERROR);
        }
        return userService.addUser(userVo);
    }
}
