package com.dhx.apicore.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.model.DO.InterfaceVariableInfoEntity;
import com.dhx.apicore.model.enums.InterfaceCategoryEnum;
import com.dhx.apicore.model.query.InterfacePubQuery;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVo;
import com.dhx.apicore.model.vo.InterfaceDetailVo;
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
    public BaseResponse publishInterface(@RequestBody @Validated InterfacePubQuery query) {
        interfaceInfoService.publishInterface(query);
        return ResultUtil.success("发布成功");
    }

    @GetMapping("/list")
    public BaseResponse<List<InterfaceBasicInfoVo>> getInterfaceList(PageQuery pageQuery) {
        List<InterfaceBasicInfoVo> list = interfaceInfoService.getInterfaceList(pageQuery);
        return ResultUtil.success(list);
    }

    @GetMapping("/detail/{id}")
    public BaseResponse<InterfaceDetailVo> getInterfaceDetail(@PathVariable("id") Long id) {
        return interfaceInfoService.getInterfaceDetail(id);
    }

    @GetMapping("/example/{id}")
    public BaseResponse<InterfaceVariableInfoEntity> getInterfaceExample(@PathVariable("id") Long id) {
        return ResultUtil.success(interfaceVariableInfoService.findById(id));
    }

    @GetMapping("/list/rank")
    public BaseResponse<List<InterfaceRankInfoVo>> getRankInterfaces() {
        return interfaceInfoService.getRank5Interface();
    }

//    @GetMapping("/list/tag")
//    public BaseResponse<List<InterfaceTagVo>> getInterfaceByTag(){
//        return interfaceInfoService.getInterfaceByTag();
//    }

}
