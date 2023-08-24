package com.dhx.apiinterface.controller;

import com.dhx.apiinterface.domain.WeatherInfo;
import com.dhx.apiinterface.service.WeatherService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author adorabled4
 * @className WeatherController
 * @date : 2023/04/15/ 17:18
 **/
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Resource
    WeatherService weatherService;

    @GetMapping("/now")
    public BaseResponse<WeatherInfo> nowWeather(@RequestParam(value = "cityName",required = false)String cityName, HttpServletRequest request){
        if(StringUtils.isEmpty(cityName)){
            return weatherService.getWeatherByRequest(request);
        }
        return weatherService.getWeatherByCityName(cityName);
    }

}
