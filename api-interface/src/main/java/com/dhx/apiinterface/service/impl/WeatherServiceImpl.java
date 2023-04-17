package com.dhx.apiinterface.service.impl;

import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.domain.City;
import com.dhx.apiinterface.domain.WeatherInfo;
import com.dhx.apiinterface.domain.vo.WeatherResponse;
import com.dhx.apiinterface.service.CityService;
import com.dhx.apiinterface.service.WeatherService;
import com.dhx.apiinterface.util.IpUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author adorabled4
 * @className WeatherServiceImpl
 * @date : 2023/04/15/ 17:19
 **/
@Service
public class WeatherServiceImpl implements WeatherService {
    @Value("${weather.apiKey}")
    private String apiKey;
    public static final String DEFAULT_CITY = "北京";
    public static final int DEFAULT_CITY_CODE = 110000;
    public static final String API_URL = "https://restapi.amap.com/v3/weather/weatherInfo";

    @Resource
    CityService cityService;


    @Override
    public BaseResponse<WeatherInfo> getWeatherByRequest(HttpServletRequest request) {
        // request => IP => location
        String ipAddr = IpUtil.getIpAddr(request);
        String ipLocation = IpUtil.getIpLocation(ipAddr);
        String[] split = ipLocation.split("\\|");
        String cityName =DEFAULT_CITY;
        for (String str : split) {
            if(str.equals("0")||str.equals("")){
                continue;
            }else{
                cityName=str;
            }
        }
        return getWeatherByCityName(cityName);
    }

    @Override
    public BaseResponse<WeatherInfo> getWeatherByCityName(String cityName) {
        String url = API_URL;
        Map<String,Object> params = new HashMap<>();
        params.put("key",apiKey);
        City city = cityService.query().eq("city_name", cityName).one();
        if(city==null){
            String trimCityName = trimCityName(cityName);
            List<City> cities= cityService.query().like("city_name", trimCityName).list();
            if(cities.isEmpty()){
                params.put("city",DEFAULT_CITY_CODE); // 查询北京
            }else{
                for(City tmpCity : cities){
                    params.put("city",tmpCity.getAdcode());
                }
            }
        }
        String response = HttpUtil.get(url,params);
        WeatherResponse weatherResponse = JSONUtil.toBean(response, WeatherResponse.class);
        // 响应码正常 weatherResponse.getStatus().equals("1") && weatherResponse.getInfo().equals("10000")
        if(weatherResponse.getStatus().equals("1")){
            WeatherInfo forecasts = weatherResponse.getLives().get(0);
            return ResultUtil.success(forecasts);
        }else{
            return ResultUtil.error(ErrorCode.SYSTEM_ERROR);
        }
    }


    /**
     * 去掉地区名的后缀 : 比如 郑州市 => 郑州
     * @param cityName
     * @return
     */
    private String trimCityName(String cityName){
        String[]suffixs=new String[]{"自治县", "自治区", "自治州" ,"市", "县", "区", "市辖区", "省"};
        for (String suffix : suffixs) {
            if(cityName.endsWith(suffix)){
                int lastIndexOf = cityName.lastIndexOf(suffix);
                return cityName.substring(0,lastIndexOf);
            }
        }
        return cityName;
    }
}

class Solution {
    public int maxProduct(int[] nums) {
        if(nums.length==1) return nums[0];
        int[]dp1=new int[nums.length+1];
        int[]dp2=new int[nums.length+1];
        dp1[0]=nums[0];
        dp2[0]=nums[0];
        for(int i=1;i<nums.length;i++){
            // 可能会存在两个较大的负数, 负负得正的问题
            // 我们考虑两个dp数组 , 一个用来存较大的数字, 一个用来存较小的数字
            dp1[i]=max(dp1[i-1],dp1[i-1]*nums[i],nums[i]);
            dp2[i]=min(dp2[i-1],dp2[i-1]*nums[i],nums[i]);
        }
        int res=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            res=max(dp1[i],dp2[i],res);
        }
        return res;
    }
    int max(int a,int b,int c){
        return Math.max(Math.max(a,b),c);
    }
    int min(int a,int b,int c){
        return Math.min(Math.min(a,b),c);
    }
}