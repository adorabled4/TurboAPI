package com.dhx.apisdk.client.impl;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.dhx.apisdk.HxApiClientConfig.SERVER_HOST;

/**
 * @author adorabled4
 * @className HxApiClient
 * @date : 2023/04/14/ 12:51
 **/
@Component
@Slf4j
public class HxApiClientImpl implements HxApiClient {

    String accessKey;

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
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtil.genSign(body, secretKey));
        return hashMap;
    }

    Map<String, String> getHeaderMap() {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtil.genSign("", secretKey)); // 空字符串作为 body 参数
        return hashMap;
    }

    @Override
    public Poet getRandomPoet(){
        try{
            //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
            String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String result =  HttpRequest.get(SERVER_HOST + "/dev-api/api/apiinterface/poet/random").header("dhx.sdk",nowTime).addHeaders(getHeaderMap()).execute().body();
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
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 访问服务器失败 --"+e.getMessage() );
        }catch(RuntimeException e){
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 调用接口失败 --"+e.getMessage() );
        }
        return null;
    }

    @Override
    public String analyseIP(String ipv4) {
        try{
            HashMap<String, Object> paramMap = new HashMap<>();
            paramMap.put("ip", ipv4);
            //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
            String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String result =  HttpRequest.get(SERVER_HOST + "/dev-api/api/ip/ana").header("dhx.sdk",nowTime).addHeaders(getHeaderMap()).form(paramMap).execute().body();
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
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 访问服务器失败 --"+e.getMessage() );
        }catch(RuntimeException e){
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 调用接口失败 --"+e.getMessage() );
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
            String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String result =  HttpRequest.get(SERVER_HOST + "/dev-api/api/weather/now").header("dhx.sdk",nowTime).addHeaders(getHeaderMap()).execute().body();
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
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 访问服务器失败 --"+e.getMessage() );
        }catch(RuntimeException e){
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 调用接口失败 --"+e.getMessage() );
        }
        return null;
    }

    @Override
    public BaseResponse invokeInterface(String method, Map params, String url, HttpServletRequest request) {
        try{
            // 处理url , 通过uri 类, 获取path
            URI uri = new URI(url);
            // 转换数据格式
            String paramsStr= JSONUtil.toJsonStr(params);
//            String authorization = request.getHeader("Authorization"); // 如果在发送的请求中添加这个,那么会被拦截(被网关识别为非前端来源)
            //准备httpRequest
            // 判断请求方式
            String result="";
            String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            if(method.equalsIgnoreCase("GET")){
                result= HttpRequest.get(SERVER_HOST + uri.getPath()).header("dhx.SDK",nowTime).addHeaders(getHeaderMap()).form(params).execute().body();
            }else if(method.equalsIgnoreCase("POST")){
                result= HttpRequest.get(SERVER_HOST + uri.getPath()).header("dhx.SDK",nowTime).addHeaders(getHeaderMap(paramsStr)).form(params).execute().body();
            }
            try{
                BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
                if(baseResponse.getMessage()==null && baseResponse.getDescription()==null ||baseResponse.getCode()!=200){
                    return ResultUtil.error();
                }
                return baseResponse;
            }catch(RuntimeException e){
                log.error("接口调用失败, result: {}",result);
                return ResultUtil.error(HttpStatus.TOO_MANY_REQUESTS.value(),"请求次数过多!");
            }
        } catch (URISyntaxException e) {
            return ResultUtil.error(ErrorCode.SYSTEM_ERROR);
        }
    }
}
