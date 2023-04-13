package com.dhx.apicore.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.model.DTO.UserDTO;
import com.dhx.apicore.util.UserHolder;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author adorabled4
 * @className TestController
 * @date : 2023/04/11/ 11:34
 **/
@RestController
//@Api(tags = "测试接口,请忽视",hidden = true)
public class TestController {

    @RequestMapping("/test")
    @ApiOperation(hidden = true,value = "测试用户登录")
    public BaseResponse<UserDTO> loginTest(){
        UserDTO user = UserHolder.getUser();
        return ResultUtil.success(user);
    }
}
