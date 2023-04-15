package com.dhx.apicore.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.model.DTO.UserDTO;
import com.dhx.apicore.util.UserHolder;
import com.dhx.apisdk.client.HxApiClient;
import io.lettuce.core.api.reactive.BaseRedisReactiveCommands;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className TestController
 * @date : 2023/04/11/ 11:34
 **/
@RestController
//@Api(tags = "测试接口,请忽视",hidden = true)
public class TestController {

    @Resource
    HxApiClient hxApiClient;

    @RequestMapping("/test")
    @ApiOperation(hidden = true,value = "测试用户登录")
    public BaseResponse<UserDTO> loginTest(){
        UserDTO user = UserHolder.getUser();
        return ResultUtil.success(user);
    }

    /**
     * 测试 ip SDK
     * @param ip
     * @return
     */
    @RequestMapping("/ip")
    public BaseResponse<String> ipTest(String ip){
        String s = hxApiClient.analyseIP(ip);
        System.out.println(s);
        return ResultUtil.success(s);
    }

}
