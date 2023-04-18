package com.dhx.apisdk.client.impl;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.dhx.apisdk.client.HxApiClient;
import com.dhx.apisdk.model.BaseResponse;
import com.dhx.apisdk.model.TO.Poet;
import com.dhx.apisdk.model.TO.WeatherInfo;
import com.dhx.apisdk.model.exception.BusinessException;
import com.dhx.apisdk.model.exception.ErrorCode;
import com.dhx.apisdk.util.ResultUtil;
import com.dhx.apisdk.util.SignUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static com.dhx.apisdk.HxApiClientConfig.SERVER_HOST;

/**
 * @author adorabled4
 * @className HxApiClient
 * @date : 2023/04/14/ 12:51
 **/
@Component
public class HxApiClientImpl implements HxApiClient {

    @Value("dhx.client.accessKey")
    String accessKey;

    @Value("dhx.client.secretKey")
    String secretKey;

    public HxApiClientImpl(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public HxApiClientImpl(){

    }

    Map<String, String> getHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        // 一定不能直接发送
//        hashMap.put("secretKey", secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtil.genSign(body, secretKey));
        return hashMap;
    }

    @Override
    public Poet getRandomPoet(){
        try{
            //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
            String result = HttpUtil.get(SERVER_HOST + "/dev-api/api/apiinterface/poet/random");
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if(baseResponse.getCode()==200){
                String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
                if(dataStr==null || dataStr.equals("") ){
                    throw new RuntimeException();
                }
                Poet poet = JSONUtil.toBean(dataStr, Poet.class);
                return poet;
            }else{
                throw new BusinessException(baseResponse.getCode(), baseResponse.getDescription(), baseResponse.getMessage());
            }
        }catch (IORuntimeException e){
            System.out.println("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 访问服务器失败 --"+e.getMessage() );
        }catch(RuntimeException e){
            System.out.println("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 调用接口失败 --"+e.getMessage() );
        }
        return null;
    }

    @Override
    public String analyseIP(String ipv4) {
        try{
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("ip", ipv4);
            //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
            String result = HttpUtil.get(SERVER_HOST + "/dev-api/api/ip/ana",paramMap);
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if(baseResponse.getCode()==200){
                String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
                if(dataStr==null || dataStr.equals("") ){
                    throw new RuntimeException();
                }else{
                    return dataStr;
                }
            }else{
                throw new BusinessException(baseResponse.getCode(), baseResponse.getDescription(), baseResponse.getMessage());
            }
        }catch (IORuntimeException e){
            System.out.println("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 访问服务器失败 --"+e.getMessage() );
        }catch(RuntimeException e){
            System.out.println("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 调用接口失败 --"+e.getMessage() );
        }
        return null;
    }

    @Override
    public WeatherInfo getNowWeather(String cityName) {
        try{

            HashMap<String, Object> paramMap = new HashMap<>();
            if(cityName!=null && !cityName.equals("")){
                paramMap.put("cityName", cityName);
            }
            //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
            String result = HttpUtil.get(SERVER_HOST + "/dev-api/api/weather/now",paramMap);
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if(baseResponse.getCode()==200){
                String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
                if(dataStr==null || dataStr.equals("") ){
                    throw new RuntimeException();
                }else{
                    WeatherInfo weatherInfo = JSONUtil.toBean(dataStr, WeatherInfo.class);
                    return weatherInfo;
                }
            }else{
                throw new BusinessException(baseResponse.getCode(), baseResponse.getDescription(), baseResponse.getMessage());
            }
        }catch (IORuntimeException e){
            System.out.println("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 访问服务器失败 --"+e.getMessage() );
        }catch(RuntimeException e){
            System.out.println("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 调用接口失败 --"+e.getMessage() );
        }
        return null;
    }

    @Override
    public BaseResponse invokeInterface(String method, String params, String url) {
        try{
            // 处理url , 通过uri 类, 获取path
            URI uri = new URI(url);
            // 转换数据格式
            JSONObject jsonObject = JSONUtil.parseObj(params);
            Map<String, Object> map = jsonObject.toBean(Map.class);

            // 判断请求方式
            String result="";
            if(method.equalsIgnoreCase("GET")){
                result = HttpUtil.get(SERVER_HOST + uri.getPath(), map);
            }else if(method.equalsIgnoreCase("POST")){
                result = HttpUtil.post(SERVER_HOST + uri.getPath(), map);
            }
            if(result.equals("")){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if(baseResponse.getMessage()==null && baseResponse.getDescription()==null){
                return ResultUtil.error();
            }
            return baseResponse;
        } catch (URISyntaxException e) {
            return ResultUtil.error(ErrorCode.SYSTEM_ERROR);
        }
    }
}
