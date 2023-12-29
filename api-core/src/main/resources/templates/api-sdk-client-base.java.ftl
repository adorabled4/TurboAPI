package ${basePackage};

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
<#list apis as api>
import com.dhx.apicommon.model.${api.version}.${api.modelName};
import com.dhx.apicommon.model.${api.version}.param.${api.sdkParamName};
</#list>

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.dhx.apisdk.HxApiClientConfig.SERVER_HOST;

@Slf4j

public class ${className} {

<#list apis as api>


/**
* <p>${api.description}</p>
* @param param ${api.sdkParamName}
* @return BaseResponse<${api.modelName}>
* @doc <a href="${api.docUrl}">文档地址</a>
* @build <p>Build by Adorabled4 at ${time} , driver by FreeMarker Template Engine</p>
*/
public BaseResponse<${api.modelName}> ${api.sdkMethodName}(<#if api.sdkParamName??> ${api.sdkParamName}param </#if>) {
try {
<#if api.sdkParamName??>
<#if api.requestMethod=="GET">
String result = HttpRequest.get(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param).execute().body();
<#elseif api.requestMethod=="POST">
String result = HttpRequest.post(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param).execute().body();
</#if></#if>
<#if !api.sdkParamName??>
<#if api.requestMethod=="GET">
String result = HttpRequest.get(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).execute().body();
<#elseif api.requestMethod=="POST">
String result = HttpRequest.post(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).execute().body();
</#if></#if>
BaseResponse<${api.modelName}> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
if (baseResponse.getCode() == 200) {
String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
if (dataStr == null || dataStr.equals("")) {
log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + baseResponse.toString());
}
return baseResponse;
} else {
throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
}
} catch (IORuntimeException e) {
log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 访问服务器失败 --" + e.getMessage());
} catch (RuntimeException e) {
log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + e.getMessage());
}
}

</#list>

}