package com.dhx.apiinterface.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.model.v3.ReviewTextResult;
import com.dhx.apicommon.model.v3.param.*;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.manager.ReviewManager;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author adorabled4
 * @className OwnApiController
 * @date : 2023-12-29 15:28:47
 **/
@RestController
@RequestMapping("/v3")
@Tag(name = "v3接口")
public class OwnApiController {

    @Value("${spring.profiles.active}")
    private String env;

    private static final String VM_ADDRESS = "http://192.168.159.134:";

    @Resource
    ReviewManager reviewManager;

    Map<String, String> path2Url;

    @PostConstruct
    private void fillValues() {
        path2Url = new HashMap<>();
        if (env.equals("dev")) {
            path2Url.put("idiom", VM_ADDRESS + "11000/query?");
            path2Url.put("jqrcode", VM_ADDRESS + "11001/query?");
            path2Url.put("jxzqh", VM_ADDRESS + "11002/query?");
            path2Url.put("jxzcx", VM_ADDRESS + "11003/query?");
        }
    }

    @PostMapping("/review/text")
    @Operation(summary = "文本审核API")
    public BaseResponse<ReviewTextResult> reviewText(@Validated @RequestBody ReviewTextParam param) {
        return ResultUtil.success(reviewManager.doFilter(param.getText()));
    }

    @GetMapping("/idiom")
    @Operation(summary = "成语大全API")
    public BaseResponse<Object> anaIdiom(@Validated @RequestBody IdiomParm param) {
        return queryAndReturn("idiom", param);
    }


    @GetMapping("/jqrcode")
    @Operation(summary = "二维码生成API")
    public BaseResponse<Object> jqrcode(@Validated @RequestBody QRCodeParam param) {
        return queryAndReturn("jqrcode", param);
    }

    @GetMapping("/jxzqh")
    @Operation(summary = "全国行政区划查询API")
    public BaseResponse<Object> locationSearch(@Validated @RequestBody LocationParm param) {
        return queryAndReturn("jxzqh", param);
    }


    @GetMapping("/jxzcx")
    @Operation(summary = "星座查询API")
    public BaseResponse<Object> constellationParamSearch(@Validated @RequestBody ConstellationParam param) {
        return queryAndReturn("jxzcx", param);
    }

    private BaseResponse<Object> queryAndReturn(String path, Object param) {
        Map<String, Object> hashMap = BeanUtil.beanToMap(param);
        StringBuilder query = new StringBuilder();
        hashMap.forEach((key, value) -> {
            if (query.length() > 0) {
                query.append("&");
            }
            query.append(key).append("=").append(value);
        });
        String body = HttpRequest.get(path2Url.get(path) + query).execute().body();
        JSONObject jsonObject = new JSONObject(body);
        return ResultUtil.success(jsonObject.get("result"));
    }
}
