package com.dhx.apiinterface.controller;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.model.v3.ReviewTextResult;
import com.dhx.apicommon.model.v3.param.ReviewTextParam;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.manager.ReviewManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author adorabled4
 * @className OwnApiController
 * @date : 2023-12-29 15:28:47
 **/
@RestController
@RequestMapping("/v3")
public class OwnApiController {

    @Resource
    ReviewManager reviewManager;

    @PostMapping("/review/text")
    public BaseResponse<ReviewTextResult> reviewText(@Validated @RequestBody ReviewTextParam param) {
        return ResultUtil.success(reviewManager.doFilter(param.getText()));
    }
}
