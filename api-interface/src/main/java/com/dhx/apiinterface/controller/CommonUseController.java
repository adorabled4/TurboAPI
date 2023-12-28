package com.dhx.apiinterface.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
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
import com.dhx.apiinterface.vo.PoetVO;
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
public class CommonUseController {
    @Resource
    ComputerSuffixService computerSuffixService;
    @Resource
    LovelornSentenceService loveSentenceService;
    @Autowired
    private IPoetService poetService;
    @Resource
    WeatherService weatherService;

    /**
     * 查询 文件后缀名 介绍
     * @param param
     * @return
     */
    @GetMapping("/suffix")
    public BaseResponse<ComputerSuffix> getSuffixDetail(@RequestBody FileSuffixParam param){
        QueryWrapper<ComputerSuffix> wrapper = new QueryWrapper<>();
        wrapper.eq("suffix",param);
        List<ComputerSuffix> list = computerSuffixService.list(wrapper);
        if(list==null || list.size()==0){
            return  ResultUtil.error(ErrorCode.PARAMS_ERROR,"未知的文件后缀~");
        }
        return ResultUtil.success(list.get(0));
    }

    /**
     * 随机 失恋 文案
     * @return
     */
    @GetMapping("/lovelorn")
    public BaseResponse<LovelornSentence> getSuffixDetail(){
        long total = loveSentenceService.count();
        long id = (long) (Math.random()*total+1);
        while(id<0 || id >= total){
            id = (long) (Math.random()*total+1);
        }
        // id 不存在,  返回一号
        if(loveSentenceService.list(new QueryWrapper<LovelornSentence>().eq("id",id)).size()==0){
            return ResultUtil.success(loveSentenceService.getById(1));
        }
        // 正常返回
        return ResultUtil.success(loveSentenceService.getById(id));
    }


    /**
     * 获取IPv4地址的location
     * @param param
     * @return
     */
    @GetMapping("/ip/ana")
    public BaseResponse<String> analysisIP(@RequestBody IpAnaParam param){
        try{
            InetAddress inetAddress = InetAddress.getByName(param.getIp());
            if (inetAddress instanceof Inet4Address) {
                String location = IpUtil.getIpLocation(param.getIp());
                return ResultUtil.success(location);
            } else {
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数不是合法的IPv4地址!");
            }
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取请求者的IP地址
     * @param request
     * @return
     */
    @GetMapping("/ip/me")
    public BaseResponse<String> getIpOfRequest(HttpServletRequest request){
        String ipAddr = IpUtil.getIpAddr(request);
        return ResultUtil.success(ipAddr);
    }

    @GetMapping("/weather/now")
    public BaseResponse<WeatherInfo> nowWeather(@RequestBody WeatherParam weatherParam, HttpServletRequest request){
        if(StringUtils.isEmpty(weatherParam.getCityName())){
            return weatherService.getWeatherByRequest(request);
        }
        return weatherService.getWeatherByCityName(weatherParam.getCityName());
    }
    @GetMapping("/poet/random")
    public BaseResponse<PoetVO> getRandomPoet() {
        long total = poetService.getTotal();
        long id = (long) (Math.random() * total + 1);
        while (id < 0 || id >= total) {
            id = (long) (Math.random() * total + 1);
        }
        return poetService.getPoetVO(id);
    }

}
