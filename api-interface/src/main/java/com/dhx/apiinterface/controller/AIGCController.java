package com.dhx.apiinterface.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apiinterface.service.InvokeInterfaceServiceV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author adorabled4
 * @className AIGCController
 * @date : 2023/12/27/ 15:18
 **/
@RestController
@RequestMapping("/v2")
public class AIGCController {
    @Resource
    InvokeInterfaceServiceV2 invokeInterfaceServiceV2;

    @GetMapping("/takeout/comment")
    public BaseResponse genTakeOutComment(@RequestParam("recipe")String recipe, HttpServletRequest request){
        return invokeInterfaceServiceV2.genTakeOutComment( recipe,  request);
    }
}