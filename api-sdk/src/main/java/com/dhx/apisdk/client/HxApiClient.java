package com.dhx.apisdk.client;

import com.dhx.apisdk.model.BaseResponse;
import com.dhx.apisdk.model.TO.Poet;
import com.dhx.apisdk.model.TO.WeatherInfo;
import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;

public interface HxApiClient {


    /**
     * 获取随机诗句
     *
     * @return
     */
    Poet getRandomPoet();


    /**
     * 分析IP属地
     * @param ipv4
     * @return
     */
    String analyseIP(String ipv4);


    /**
     * 获取指定地区的当前天气情况 , 如果参数为空那么会通过request的IP获取
     * @param cityName 地区名称
     * @return
     */
    WeatherInfo getNowWeather(@Nullable String cityName);


    BaseResponse invokeInterface(String method, String params, String url, HttpServletRequest request);
}
