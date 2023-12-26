package com.dhx.apicore.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.model.DO.InterfaceEntity;
import com.dhx.apicore.model.DO.InterfaceVariableInfoEntity;
import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.model.DTO.UserDTO;
import com.dhx.apicore.model.query.InterfaceInfoQuery;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVo;
import com.dhx.apicore.model.vo.InterfaceDetailVo;
import com.dhx.apicore.model.vo.InterfaceRankInfoVo;
import com.dhx.apicore.model.vo.InterfaceTagVo;
import com.dhx.apicore.service.InterfaceInfoService;
import com.dhx.apicore.service.InterfaceVariableInfoService;
import com.dhx.apicore.service.UserService;
import com.dhx.apicore.util.UserHolder;
import com.dhx.apisdk.client.HxApiClient;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
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
    InterfaceInfoService interfaceInfoService;

    @Resource
    HxApiClient hxApiClient;

    @Resource
    UserService userService;

    @Resource
    InterfaceVariableInfoService interfaceVariableInfoService;

    @PostMapping("/invoke")
    public BaseResponse invokeInterfaceOL(@Valid @RequestBody InterfaceInfoQuery interfaceInfoQuery, HttpServletRequest request){
        if(interfaceInfoQuery ==null || interfaceInfoQuery.getInterfaceId()==null){
            return ResultUtil.error(ErrorCode.PARAMS_ERROR);
        }
        long id = interfaceInfoQuery.getInterfaceId();
        Gson gson = new Gson();
        try{
            Map<String, Object> requestParams = gson.fromJson(interfaceInfoQuery.getParams(), new TypeToken<Map<String, Object>>(){}.getType());
            boolean validInterface=  interfaceInfoService.isValidInterfaceId(id);
            if(!validInterface){
                return ResultUtil.error(ErrorCode.PARAMS_ERROR,"接口已关闭!");
            }
            // 获取接口相关的信息 : 包括 请求方式, 请求路径等
            InterfaceEntity interfaceEntity = interfaceInfoService.getById(id);
            String method = interfaceEntity.getMethod();
            String url = interfaceEntity.getUrl();
            UserDTO userDTO = UserHolder.getUser();
            UserEntity user = userService.getById(userDTO.getUserId());
            // 调用SDK 来调用接口
            return hxApiClient.invokeInterface(method, requestParams, interfaceEntity.getUrl(),request);
        }catch (JsonSyntaxException e){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数格式错误，请输入JSON格式参数！");
        }
    }

    @GetMapping("/list")
    public BaseResponse<List<InterfaceBasicInfoVo>> getInterfaceList(PageQuery pageQuery) {
        List<InterfaceBasicInfoVo> list = interfaceInfoService.getInterfaceList(pageQuery);
        return ResultUtil.success(list);
    }

    @GetMapping("/detail/{id}")
    public BaseResponse<InterfaceDetailVo> getInterfaceDetail(@PathVariable("id")Long id){
        return interfaceInfoService.getInterfaceDetail(id);
    }

    @GetMapping("/example/{id}")
    public BaseResponse<InterfaceVariableInfoEntity> getInterfaceExample(@PathVariable("id")Long id){
        return ResultUtil.success(interfaceVariableInfoService.findById(id));
    }

    @GetMapping("/list/rank")
    public BaseResponse<List<InterfaceRankInfoVo>> getRankInterfaces(){
        return interfaceInfoService.getRank5Interface();
    }

    @GetMapping("/list/tag")
    public BaseResponse<List<InterfaceTagVo>> getInterfaceByTag(){
        return interfaceInfoService.getInterfaceByTag();
    }

}
