package com.dhx.apisdk;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apisdk.client.HxApiClient;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apisdk.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.dhx.apicommon.model.v1.com.dhx.common.model.v1.Poet0;
import com.dhx.apicommon.model.v1.com.dhx.common.model.v1.Poet1;
import com.dhx.apicommon.model.v1.com.dhx.common.model.v1.Poet2;
import com.dhx.apicommon.model.v1.com.dhx.common.model.v1.Poet3;
import com.dhx.apicommon.model.v1.com.dhx.common.model.v1.Poet4;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.dhx.apisdk.HxApiClientConfig.SERVER_HOST;

@Slf4j

public class HxApiClientTest {


    public com.dhx.common.model.v1.Poet0 getWeather0(com.dhx.common.model.v1.query.PoetQuery param) {
        try {
            String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String result = HttpRequest.get(SERVER_HOST + "api/v1/test/api").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param).execute().body();
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if (baseResponse.getCode() == 200) {
                String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
                if (dataStr == null || dataStr.equals("")) {
                    log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + baseResponse.toString());
                }
                com.dhx.apisdk.model.com.dhx.common.model.v1.Poet0 obj = JSONUtil.toBean(dataStr, com.dhx.apisdk.model.com.dhx.common.model.v1.Poet0.class);
                return obj;
            } else {
                throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
            }
        } catch (IORuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 访问服务器失败 --" + e.getMessage());
        } catch (RuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + e.getMessage());
        }
    }


    public com.dhx.common.model.v1.Poet1 getWeather1(com.dhx.common.model.v1.query.PoetQuery param) {
        try {
            String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String result = HttpRequest.get(SERVER_HOST + "api/v1/test/api").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param).execute().body();
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if (baseResponse.getCode() == 200) {
                String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
                if (dataStr == null || dataStr.equals("")) {
                    log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + baseResponse.toString());
                }
                com.dhx.apisdk.model.com.dhx.common.model.v1.Poet1 obj = JSONUtil.toBean(dataStr, com.dhx.apisdk.model.com.dhx.common.model.v1.Poet1.class);
                return obj;
            } else {
                throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
            }
        } catch (IORuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 访问服务器失败 --" + e.getMessage());
        } catch (RuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + e.getMessage());
        }
    }


    public com.dhx.common.model.v1.Poet2 getWeather2(com.dhx.common.model.v1.query.PoetQuery param) {
        try {
            String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String result = HttpRequest.get(SERVER_HOST + "api/v1/test/api").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param).execute().body();
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if (baseResponse.getCode() == 200) {
                String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
                if (dataStr == null || dataStr.equals("")) {
                    log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + baseResponse.toString());
                }
                com.dhx.apisdk.model.com.dhx.common.model.v1.Poet2 obj = JSONUtil.toBean(dataStr, com.dhx.apisdk.model.com.dhx.common.model.v1.Poet2.class);
                return obj;
            } else {
                throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
            }
        } catch (IORuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 访问服务器失败 --" + e.getMessage());
        } catch (RuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + e.getMessage());
        }
    }


    public com.dhx.common.model.v1.Poet3 getWeather3(com.dhx.common.model.v1.query.PoetQuery param) {
        try {
            String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String result = HttpRequest.get(SERVER_HOST + "api/v1/test/api").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param).execute().body();
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if (baseResponse.getCode() == 200) {
                String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
                if (dataStr == null || dataStr.equals("")) {
                    log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + baseResponse.toString());
                }
                com.dhx.apisdk.model.com.dhx.common.model.v1.Poet3 obj = JSONUtil.toBean(dataStr, com.dhx.apisdk.model.com.dhx.common.model.v1.Poet3.class);
                return obj;
            } else {
                throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
            }
        } catch (IORuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 访问服务器失败 --" + e.getMessage());
        } catch (RuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + e.getMessage());
        }
    }


    public com.dhx.common.model.v1.Poet4 getWeather4(com.dhx.common.model.v1.query.PoetQuery param) {
        try {
            String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
            String result = HttpRequest.get(SERVER_HOST + "api/v1/test/api").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param).execute().body();
            BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
            if (baseResponse.getCode() == 200) {
                String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
                if (dataStr == null || dataStr.equals("")) {
                    log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + baseResponse.toString());
                }
                com.dhx.apisdk.model.com.dhx.common.model.v1.Poet4 obj = JSONUtil.toBean(dataStr, com.dhx.apisdk.model.com.dhx.common.model.v1.Poet4.class);
                return obj;
            } else {
                throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
            }
        } catch (IORuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 访问服务器失败 --" + e.getMessage());
        } catch (RuntimeException e) {
            log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + e.getMessage());
        }
    }


}