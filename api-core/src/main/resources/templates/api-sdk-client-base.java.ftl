package ${basePackage}.client;

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
import ${api.modelName};
<#if api.sdkParamName?has_content>import ${api.sdkParamName};</#if>
</#list>
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static com.dhx.apisdk.TurboAPIClientConfig.SERVER_HOST;

@Slf4j
public class ${className} {

private String accessKey;
private String secretKey;

public ${className}(String accessKey, String secretKey) {
this.accessKey = accessKey;
this.secretKey = secretKey;
}

public ${className}() {
}

Map${"<String, String>"} getHeaderMap() {
Map${"<String, String>"} hashMap = new HashMap<>();
hashMap.put("accessKey", accessKey);
hashMap.put("nonce", RandomUtil.randomNumbers(4));
hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
hashMap.put("sign", SignUtil.genSign("", secretKey)); // 空字符串作为 body 参数
return hashMap;
}
<#list apis as api>

/**
* <p>${api.description}</p>
* <#if api.sdkParamName?has_content>@param param ${api.sdkParamName} param</#if>
* @return BaseResponse<${api.modelName}> response
* @doc <a href="${api.docUrl}">点击访问文档</a>
* @build <p>Build by Adorabled4 at ${time}. Driver by FreeMarker Template Engine</p>
*/
public BaseResponse<${api.modelName}> ${api.sdkMethodName}(<#if api.sdkParamName?has_content>${api.sdkParamName} param </#if>) {
try {
<#if api.sdkParamName?has_content><#if api.requestMethod=="GET">String result = HttpRequest.get(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param).execute().body();
<#elseif api.requestMethod=="POST">String result = HttpRequest.post(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param).execute().body();</#if></#if>
<#if !api.sdkParamName?has_content><#if api.requestMethod=="GET">String result = HttpRequest.get(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).execute().body();
<#elseif api.requestMethod=="POST">String result = HttpRequest.post(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).execute().body();</#if></#if>
BaseResponse<${api.modelName}> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
if (baseResponse.getCode() == 200) {
String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
if (dataStr == null || dataStr.equals("")) {
log.error("\u001B[31m" + this.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + baseResponse.toString());
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
return null;
}
</#list>
}