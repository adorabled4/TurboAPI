package com.dhx.apicore.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVo;
import com.dhx.apicore.service.InterfaceEntityService;
import io.swagger.annotations.Api;
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
@RequestMapping("interface")
public class InterfaceController {

    @Resource
    InterfaceEntityService interfaceEntityService;

    @GetMapping("/list")
    public BaseResponse getInterfaceList(@RequestParam("pageSize")int pageSize, @RequestParam("current")int current) {
        if(pageSize <= 0 ){
            pageSize=5;
        }
        if(current<0){
            current=1;
        }
        List<InterfaceBasicInfoVo> list = interfaceEntityService.getInterfaceList(pageSize,current);
        return ResultUtil.success(list);
    }

    @GetMapping("/detail/{id}")
    public BaseResponse getInterfaceDetail(@PathVariable("id")Long id){
        if(id<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return interfaceEntityService.getInterfaceDetail(id);
    }

    @GetMapping("/example/{id}")
    public BaseResponse getInterfaceExample(@PathVariable("id")Long id){
        if(id<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return interfaceEntityService.getInterfaceExample(id);
    }



}
