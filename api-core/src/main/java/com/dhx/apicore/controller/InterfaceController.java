package com.dhx.apicore.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.model.DO.InterfaceInfoEntity;
import com.dhx.apicore.model.DO.InterfaceVariableInfoEntity;
import com.dhx.apicore.model.enums.InterfaceCategoryEnum;
import com.dhx.apicore.model.query.InterfaceCategoryQuery;
import com.dhx.apicore.model.query.InterfaceIdsQuery;
import com.dhx.apicore.model.query.InterfaceUpdateQuery;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVO;
import com.dhx.apicore.model.vo.InterfaceDetailVO;
import com.dhx.apicore.model.vo.InterfaceRankInfoVo;
import com.dhx.apicore.service.InterfaceInfoService;
import com.dhx.apicore.service.InterfaceVariableInfoService;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author adorabled4
 * @className InterfaceController
 * @date : 2023/04/13/ 10:28
 **/
@Tag(name = "interface控制层")
@RestController
@RequestMapping("/interface")
public class InterfaceController {

    @Resource
    InterfaceInfoService interfaceInfoService;
    @GetMapping("/test/gen/doc")
    public void test(){
        List<InterfaceInfoEntity> list = interfaceInfoService.list();
        List<Long> ids = list.stream().map(InterfaceInfoEntity::getId).collect(Collectors.toList());
        interfaceInfoService.genInterfaceDocMD(new InterfaceIdsQuery(ids));
    }

    @GetMapping("/test/gen/sdk")
    public void genTurboAPISDK(){
        List<InterfaceInfoEntity> list = interfaceInfoService.list();
        List<Long> ids = list.stream().map(InterfaceInfoEntity::getId).collect(Collectors.toList());
        interfaceInfoService.genSDKCode(new InterfaceIdsQuery(ids));
    }
    @Resource
    InterfaceVariableInfoService interfaceVariableInfoService;

    @GetMapping("/categories")
    @Operation(summary = "查看所有接口标签")
    public BaseResponse getInterfaceCategories() {
        return ResultUtil.success(InterfaceCategoryEnum.values());
    }

    @PostMapping("/publish")
    @Operation(summary = "发布接口服务")
    public BaseResponse publishInterface(@RequestBody @Validated InterfaceUpdateQuery query) {
        interfaceInfoService.publishInterface(query);
        return ResultUtil.success("发布成功");
    }

    @GetMapping("/list")
    @Operation(summary = "获取接口列表")
    public BaseResponse<List<InterfaceBasicInfoVO>> getInterfaceList(PageQuery pageQuery) {
        List<InterfaceBasicInfoVO> list = interfaceInfoService.getInterfaceList(pageQuery);
        return ResultUtil.success(list);
    }

    @GetMapping("/detail/{id}")
    @Operation(summary = "查看接口详情")
    public BaseResponse<InterfaceDetailVO> getInterfaceDetail(@PathVariable("id") Long id) {
        return interfaceInfoService.getInterfaceDetail(id);
    }

    @GetMapping("/example/{id}")
    @Operation(summary = "查看接口示例")
    public BaseResponse<InterfaceVariableInfoEntity> getInterfaceExample(@PathVariable("id") Long id) {
        return ResultUtil.success(interfaceVariableInfoService.findById(id));
    }

    @GetMapping("/list/rank")
    @Operation(summary = "查看调用量TOP接口")
    public BaseResponse<List<InterfaceRankInfoVo>> getRankInterfaces() {
        return interfaceInfoService.getRank5Interface();
    }


    @GetMapping("/list/categories")
    @Operation(summary = "通过标签查询接口")
    public BaseResponse<List<InterfaceBasicInfoVO>> getInterfaceByCategories(InterfaceCategoryQuery query) {
        return interfaceInfoService.getInterfaceByCategories(query);
    }

    @PostMapping("/gen/interface-doc")
    @Operation(summary = "通过id生成接口说明文档")
    public BaseResponse<String> genInterfaceDoc(@RequestBody InterfaceIdsQuery ids) {
        interfaceInfoService.genInterfaceDocMD(ids);
        return ResultUtil.success("生成成功!");
    }

    @PostMapping("/update/interface")
    @Operation(summary = "更新接口信息")
    public BaseResponse<String> updateInterfaceInfo(@RequestBody InterfaceUpdateQuery query) {
        interfaceInfoService.updateInterfaceInfo(query);
        return ResultUtil.success("更新成功!");
    }
}
