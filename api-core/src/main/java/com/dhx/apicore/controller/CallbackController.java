package com.dhx.apicore.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.model.DO.CallBack;
import com.dhx.apicore.model.query.AddCalLBackQuery;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.service.CallBackService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className CallbackController
 * @date : 2024/01/02/ 00:07
 **/
@RestController
@Tag(name = "回调配置")
public class CallbackController {

    @Resource
    CallBackService callBackService;

    @PostMapping("/config/add")
    @Operation(summary = "添加接口回调地址配置")
    public BaseResponse<String> addCallBackConfig(AddCalLBackQuery query){
        callBackService.addCallBackConfig(query);
        return ResultUtil.success("");
    }


    @GetMapping("/config/delete")
    @Operation(summary = "删除接口回调地址配置")
    public BaseResponse<String> delCallBackConfig(@RequestParam("id")Long id){
        callBackService.removeById(id);
        return ResultUtil.success("删除成功!");
    }

    @GetMapping("/config/list")
    @Operation(summary = "查询接口回调地址配置")
    public BaseResponse<Page<CallBack>> listCallBackConfig(PageQuery query){
        return ResultUtil.success(callBackService.listConfigs(query));
    }

}
