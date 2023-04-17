package com.dhx.apiinterface.service;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apiinterface.domain.WeatherInfo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author adorabled4
 * @className WeatherService
 * @date : 2023/04/15/ 17:19
 **/
public interface WeatherService {
    BaseResponse<WeatherInfo> getWeatherByCityName(String cityName);

    BaseResponse<WeatherInfo> getWeatherByRequest(HttpServletRequest request);
}
