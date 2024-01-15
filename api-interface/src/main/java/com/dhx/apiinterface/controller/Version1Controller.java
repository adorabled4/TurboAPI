package com.dhx.apiinterface.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.model.v1.PoetVO;
import com.dhx.apicommon.model.v1.param.FileSuffixParam;
import com.dhx.apicommon.model.v1.param.IpAnaParam;
import com.dhx.apicommon.model.v1.param.WeatherParam;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.domain.ComputerSuffix;
import com.dhx.apiinterface.domain.LovelornSentence;
import com.dhx.apiinterface.domain.WeatherInfo;
import com.dhx.apiinterface.service.ComputerSuffixService;
import com.dhx.apiinterface.service.IPoetService;
import com.dhx.apiinterface.service.LovelornSentenceService;
import com.dhx.apiinterface.service.WeatherService;
import com.dhx.apiinterface.util.IpUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author adorabled4
 * @className CommonUseController
 * @date : 2023/04/30/ 17:38
 **/
@RestController
@RequestMapping("/v1/common")
@Tag(name = "v1接口")
public class Version1Controller {
    @Resource
    ComputerSuffixService computerSuffixService;
    @Resource
    LovelornSentenceService loveSentenceService;
    @Autowired
    private IPoetService poetService;
    @Resource
    WeatherService weatherService;


    @GetMapping("/suffix")
    @Operation(summary = "文件后缀信息查询API")
    public BaseResponse<ComputerSuffix> getSuffixDetail(@RequestBody FileSuffixParam param) {
        ComputerSuffix suffix = computerSuffixService.findBySuffix(param);
        if (suffix == null) {
            return ResultUtil.error(ErrorCode.PARAMS_ERROR, "未知的文件后缀~");
        }
        return ResultUtil.success(suffix);
    }


    @GetMapping("/lovelorn")
    @Operation(summary = "随机失恋文案API")
    public BaseResponse<LovelornSentence> getRandomLovelornSentence() {
        Long id = loveSentenceService.randomId();
        return ResultUtil.success(loveSentenceService.getSentenceById(id));
    }


    @GetMapping("/ip/ana")
    @Operation(summary = "IPv4属地查询API")
    public BaseResponse<String> analysisIP(@RequestBody IpAnaParam param) {
        try {
            InetAddress inetAddress = InetAddress.getByName(param.getIp());
            if (inetAddress instanceof Inet4Address) {
                String location = IpUtil.getIpLocation(param.getIp());
                return ResultUtil.success(location);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数不是合法的IPv4地址!");
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/ip/me")
    @Operation(summary = "获取自身IP属地API")
    public BaseResponse<String> getIpOfRequest(HttpServletRequest request) {
        String ipAddr = IpUtil.getIpAddr(request);
        return ResultUtil.success(ipAddr);
    }

    @GetMapping("/weather/now")
    @Operation(summary = "天气查询API")
    public BaseResponse<WeatherInfo> nowWeather(@RequestBody WeatherParam weatherParam, HttpServletRequest request) {
        if (StringUtils.isEmpty(weatherParam.getCityName())) {
            return weatherService.getWeatherByRequest(request);
        }
        return weatherService.getWeatherByCityName(weatherParam.getCityName());
    }

    @GetMapping("/poet/random")
    @Operation(summary = "随机诗句API")
    public BaseResponse<PoetVO> getRandomPoet() {
        Long id = poetService.randomId();
        return ResultUtil.success(poetService.getPoetById(id));
    }

}
