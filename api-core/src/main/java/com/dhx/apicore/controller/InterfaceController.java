package com.dhx.apicore.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.model.DO.InterfaceEntity;
import com.dhx.apicore.model.DO.InterfaceExampleEntity;
import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.model.DTO.UserDTO;
import com.dhx.apicore.model.param.InterfaceInfoRequest;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVo;
import com.dhx.apicore.model.vo.InterfaceDetailVo;
import com.dhx.apicore.model.vo.InterfaceRankInfoVo;
import com.dhx.apicore.service.InterfaceEntityService;
import com.dhx.apicore.service.UserService;
import com.dhx.apicore.util.UserHolder;
import com.dhx.apisdk.client.HxApiClient;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

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
    InterfaceEntityService interfaceEntityService;

    @Resource
    HxApiClient hxApiClient;

    @Resource
    UserService userService;

    @PostMapping("/invoke")
    public Object invokeInterfaceOL(@Valid @RequestBody InterfaceInfoRequest interfaceInfoRequest, HttpServletRequest request){
        if(interfaceInfoRequest==null || interfaceInfoRequest.getInterfaceId()==null){
            return ResultUtil.error(ErrorCode.PARAMS_ERROR);
        }
        long id = interfaceInfoRequest.getInterfaceId();
        Map requestParams = interfaceInfoRequest.getParams();
        boolean validInterface=  interfaceEntityService.isValidInterfaceId(id);
        if(!validInterface){
            return ResultUtil.error(ErrorCode.PARAMS_ERROR,"接口已关闭!");
        }
        // 获取接口相关的信息 : 包括 请求方式, 请求路径等
        InterfaceEntity interfaceEntity = interfaceEntityService.getById(id);
        String method = interfaceEntity.getMethod();
        String url = interfaceEntity.getUrl();
        UserDTO userDTO = UserHolder.getUser();
        UserEntity user = userService.getById(userDTO.getUserId());
        // 调用SDK 来调用接口
        return hxApiClient.invokeInterface(method, requestParams, interfaceEntity.getUrl(),request);
    }

    @GetMapping("/list")
    public BaseResponse<List<InterfaceBasicInfoVo>> getInterfaceList(@RequestParam("pageSize")int pageSize, @RequestParam("current")int current) {
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
    public BaseResponse<InterfaceDetailVo> getInterfaceDetail(@PathVariable("id")Long id){
        if(id<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return interfaceEntityService.getInterfaceDetail(id);
    }

    @GetMapping("/example/{id}")
    public BaseResponse<InterfaceExampleEntity> getInterfaceExample(@PathVariable("id")Long id){
        if(id<0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        return interfaceEntityService.getInterfaceExample(id);
    }

    @GetMapping("/list/rank")
    public BaseResponse<List<InterfaceRankInfoVo>> getRankInterfaces(){
        return interfaceEntityService.getRank5Interface();
    }

}
