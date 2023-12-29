package com.dhx.apicore.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.model.DO.InterfaceVariableInfoEntity;
import com.dhx.apicore.model.enums.InterfaceCategoryEnum;
import com.dhx.apicore.model.query.InterfaceCategoryQuery;
import com.dhx.apicore.model.query.InterfaceUpdateQuery;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVO;
import com.dhx.apicore.model.vo.InterfaceDetailVO;
import com.dhx.apicore.model.vo.InterfaceRankInfoVo;
import com.dhx.apicore.service.InterfaceInfoService;
import com.dhx.apicore.service.InterfaceVariableInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author adorabled4
 * @className InterfaceController
 * @date : 2023/04/13/ 10:28
 **/
@Api(tags = "interface控制层")
@RestController
@RequestMapping("/interface")
public class InterfaceController {

    @Resource
    InterfaceInfoService interfaceInfoService;
    @Resource
    InterfaceVariableInfoService interfaceVariableInfoService;

    @GetMapping("/categories")
    @ApiOperation(value = "查看所有接口标签")
    public BaseResponse getInterfaceCategories() {
        return ResultUtil.success(InterfaceCategoryEnum.values());
    }

    @PostMapping("/publish")
    @ApiOperation(value = "发布接口服务")
    public BaseResponse publishInterface(@RequestBody @Validated InterfaceUpdateQuery query) {
        interfaceInfoService.publishInterface(query);
        return ResultUtil.success("发布成功");
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取接口列表")
    public BaseResponse<List<InterfaceBasicInfoVO>> getInterfaceList(PageQuery pageQuery) {
        List<InterfaceBasicInfoVO> list = interfaceInfoService.getInterfaceList(pageQuery);
        return ResultUtil.success(list);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "查看接口详情")
    public BaseResponse<InterfaceDetailVO> getInterfaceDetail(@PathVariable("id") Long id) {
        return interfaceInfoService.getInterfaceDetail(id);
    }

    @GetMapping("/example/{id}")
    @ApiOperation(value = "查看接口示例")
    public BaseResponse<InterfaceVariableInfoEntity> getInterfaceExample(@PathVariable("id") Long id) {
        return ResultUtil.success(interfaceVariableInfoService.findById(id));
    }

    @GetMapping("/list/rank")
    @ApiOperation(value = "查看调用量TOP接口")
    public BaseResponse<List<InterfaceRankInfoVo>> getRankInterfaces() {
        return interfaceInfoService.getRank5Interface();
    }


    @GetMapping("/list/categories")
    @ApiOperation(value = "通过标签查询接口")
    public BaseResponse<List<InterfaceBasicInfoVO>> getInterfaceByCategories(InterfaceCategoryQuery query) {
        return interfaceInfoService.getInterfaceByCategories(query);
    }

    @GetMapping("/gen/interface-doc")
    @ApiOperation(value = "通过id生成接口说明文档")
    public BaseResponse<String> genInterfaceDoc(@RequestParam("ids") List<Long> interfaceIds) {
        interfaceInfoService.genInterfaceDocMD(interfaceIds);
        return ResultUtil.success("生成成功!");
    }

    @PostMapping("/update/interface")
    @ApiOperation("更新接口信息")
    public BaseResponse<String> updateInterfaceInfo(@RequestBody InterfaceUpdateQuery query) {
        interfaceInfoService.updateInterfaceInfo(query);
        return ResultUtil.success("更新成功!");
    }
}
