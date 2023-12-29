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
${"\t"}
${"\t"}<#list apis as api>
${"\t"}
${"\t"}
${"\t"}public ${api.modelName} ${api.sdkMethodName}(<#if api.sdkParamName??> ${api.sdkParamName}param </#if>) {
${"\t"}${"\t"}try {
${"\t"}${"\t"}${"\t"}
${"\t"}${"\t"}${"\t"}<#if api.sdkParamName??>
${"\t"}${"\t"}${"\t"}
${"\t"}${"\t"}${"\t"}<#if api.requestMethod=="GET">
${"\t"}${"\t"}${"\t"}String result = HttpRequest.get(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param).execute().body();
${"\t"}${"\t"}${"\t"}<#elseif api.requestMethod=="POST">
${"\t"}${"\t"}${"\t"}String result = HttpRequest.post(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param).execute().body();
${"\t"}${"\t"}${"\t"}</#if>
${"\t"}${"\t"}${"\t"}</#if>
${"\t"}${"\t"}${"\t"}
${"\t"}${"\t"}${"\t"}<#if !api.sdkParamName??>
${"\t"}${"\t"}${"\t"}<#if api.requestMethod=="GET">
${"\t"}${"\t"}${"\t"}String result = HttpRequest.get(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).execute().body();
${"\t"}${"\t"}${"\t"}<#elseif api.requestMethod=="POST">
${"\t"}${"\t"}${"\t"}String result = HttpRequest.post(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).execute().body();
${"\t"}${"\t"}${"\t"}</#if>
${"\t"}${"\t"}${"\t"}</#if>
${"\t"}${"\t"}${"\t"}
${"\t"}${"\t"}${"\t"}BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
${"\t"}${"\t"}${"\t"}if (baseResponse.getCode() == 200) {
${"\t"}${"\t"}${"\t"}${"\t"}String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
${"\t"}${"\t"}${"\t"}${"\t"}if (dataStr == null || dataStr.equals("")) {
${"\t"}${"\t"}${"\t"}${"\t"}${"\t"}log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + baseResponse.toString());
${"\t"}${"\t"}${"\t"}${"\t"}}
${"\t"}${"\t"}${"\t"}${"\t"}${basePackage}.model.${api.modelName} obj = JSONUtil.toBean(dataStr, ${basePackage}.model.${api.modelName}.class);
${"\t"}${"\t"}${"\t"}${"\t"}return obj;
${"\t"}${"\t"}${"\t"}} else {
${"\t"}${"\t"}${"\t"}${"\t"}throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
${"\t"}${"\t"}${"\t"}}
${"\t"}${"\t"}} catch (IORuntimeException e) {
${"\t"}${"\t"}${"\t"}log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 访问服务器失败 --" + e.getMessage());
${"\t"}${"\t"}} catch (RuntimeException e) {
${"\t"}${"\t"}${"\t"}log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + e.getMessage());
${"\t"}${"\t"}}
${"\t"}}
${"\t"}
${"\t"}</#list>
${"\t"}
}
