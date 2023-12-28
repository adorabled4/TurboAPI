package com.dhx.apisdk.client.impl;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.model.v1.param.FileSuffixParam;
import com.dhx.apicommon.model.v1.param.IpAnaParam;
import com.dhx.apicommon.model.v1.param.WeatherParam;
import com.dhx.apisdk.client.HxApiClient;
import com.dhx.apisdk.model.TO.ComputerSuffix;
import com.dhx.apisdk.model.TO.LovelornSentence;
import com.dhx.apisdk.model.TO.Poet;
import com.dhx.apisdk.model.TO.WeatherInfo;
import com.dhx.apisdk.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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

    public HxApiClientImpl() {

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
    public Poet getRandomPoet() {
        try {
            String result = HttpRequest.get(SERVER_HOST + "/dev-api/api/apiinterface/poet/random").addHeaders(getHeaderMap()).execute().body();
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if (baseResponse.getCode() == 200) {
                String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
                if (dataStr == null || dataStr.equals("")) {
                    throw new RuntimeException();
                }
                Poet poet = JSONUtil.toBean(dataStr, Poet.class);
                return poet;
            } else {
                throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
            }
        } catch (IORuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 访问服务器失败 --" + e.getMessage());
        } catch (RuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + e.getMessage());
        }
        return null;
    }

    @Override
    public String analyseIP(IpAnaParam param) {
        try {
            String result = HttpRequest.get(SERVER_HOST + "/dev-api/api/ip/ana").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param), "application/json").execute().body();
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if (baseResponse.getCode() == 200) {
                String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
                if (dataStr == null || dataStr.equals("")) {
                    throw new RuntimeException();
                } else {
                    return dataStr;
                }
            } else {
                throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
            }
        } catch (IORuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 访问服务器失败 --" + e.getMessage());
        } catch (RuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + e.getMessage());
        }
        return null;
    }

    @Override
    public WeatherInfo getNowWeather(WeatherParam param) {
        try {
            String result = HttpRequest.get("http://localhost:8123/api/v1/common/weather/now").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param), "application/json").execute().body();
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if (baseResponse.getCode() == 200) {
                String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
                if (dataStr == null || dataStr.equals("")) {
                    throw new RuntimeException();
                } else {
                    WeatherInfo weatherInfo = JSONUtil.toBean(dataStr, WeatherInfo.class);
                    return weatherInfo;
                }
            } else {
                throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
            }
        } catch (IORuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 访问服务器失败 --" + e.getMessage());
        } catch (RuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + e.getMessage());
        }
        return null;
    }

    @Override
    public LovelornSentence getRandomLovelornSentence() {
        try {
            String result = HttpRequest.get(SERVER_HOST + "/dev-api/api/apiinterface/common/lovelorn").addHeaders(getHeaderMap()).execute().body();
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if (baseResponse.getCode() == 200) {
                String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
                if (dataStr == null || dataStr.equals("")) {
                    throw new RuntimeException();
                }
                LovelornSentence lovelornSentence = JSONUtil.toBean(dataStr, LovelornSentence.class);
                return lovelornSentence;
            } else {
                throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
            }
        } catch (IORuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 访问服务器失败 --" + e.getMessage());
        } catch (RuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + e.getMessage());
        }
        return null;
    }


    @Override
    public ComputerSuffix getDescriptionOfSuffix(FileSuffixParam param) {
        try {
            String result = HttpRequest.get(SERVER_HOST + "/dev-api/api/common/suffix").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param)).execute().body();
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if (baseResponse.getCode() == 200) {
                String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
                if (dataStr == null || dataStr.equals("")) {
                    throw new RuntimeException();
                } else {
                    return JSONUtil.toBean(dataStr, ComputerSuffix.class);
                }
            } else {
                throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
            }
        } catch (IORuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 访问服务器失败 --" + e.getMessage());
        } catch (RuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + e.getMessage());
        }
        return null;
    }
}
