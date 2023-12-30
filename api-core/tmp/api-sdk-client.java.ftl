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
${"\t"}
${"\t"}private String accessKey;
${"\t"}private String secretKey;
${"\t"}
${"\t"}public TurboAPIClientImpl(String accessKey, String secretKey) {
${"\t"}${"\t"}this.accessKey = accessKey;
${"\t"}${"\t"}this.secretKey = secretKey;
${"\t"}}
${"\t"}
${"\t"}public TurboAPIClientImpl() {
${"\t"}}
${"\t"}
${"\t"}Map${"<String, String>"} getHeaderMap() {
${"\t"}${"\t"}Map${"<String, String>"} hashMap = new HashMap<>();
${"\t"}${"\t"}hashMap.put("accessKey", accessKey);
${"\t"}${"\t"}hashMap.put("nonce", RandomUtil.randomNumbers(4));
${"\t"}${"\t"}hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
${"\t"}${"\t"}hashMap.put("sign", SignUtil.genSign("", secretKey)); // 空字符串作为 body 参数
${"\t"}${"\t"}return hashMap;
${"\t"}}
${"\t"}<#list apis as api>
${"\t"}
${"\t"}/**
${"\t"}* <p>${api.description}</p>
${"\t"}* <#if api.sdkParamName?has_content>@param param ${api.sdkParamName} param</#if>
${"\t"}* @return BaseResponse<${api.modelName}> response
${"\t"}* @doc <a href="${api.docUrl}">点击访问文档</a>
${"\t"}* @build <p>Build by Adorabled4 at ${time}. Driver by FreeMarker Template Engine</p>
${"\t"}*/
${"\t"}public BaseResponse<${api.modelName}> ${api.sdkMethodName}(<#if api.sdkParamName?has_content>${api.sdkParamName} param </#if>) {
${"\t"}${"\t"}try {
${"\t"}${"\t"}${"\t"}<#if api.sdkParamName?has_content><#if api.requestMethod=="GET">String result = HttpRequest.get(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param).execute().body();
${"\t"}${"\t"}${"\t"}<#elseif api.requestMethod=="POST">String result = HttpRequest.post(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param).execute().body();</#if></#if>
${"\t"}${"\t"}${"\t"}<#if !api.sdkParamName?has_content><#if api.requestMethod=="GET">String result = HttpRequest.get(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).execute().body();
${"\t"}${"\t"}${"\t"}<#elseif api.requestMethod=="POST">String result = HttpRequest.post(SERVER_HOST + "${api.callPath}").addHeaders(getHeaderMap()).execute().body();</#if></#if>
${"\t"}${"\t"}${"\t"}BaseResponse<${api.modelName}> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
${"\t"}${"\t"}${"\t"}if (baseResponse.getCode() == 200) {
${"\t"}${"\t"}${"\t"}${"\t"}String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
${"\t"}${"\t"}${"\t"}${"\t"}if (dataStr == null || dataStr.equals("")) {
${"\t"}${"\t"}${"\t"}${"\t"}${"\t"}log.error("\u001B[31m" + this.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + baseResponse.toString());
${"\t"}${"\t"}${"\t"}${"\t"}}
${"\t"}${"\t"}${"\t"}${"\t"}return baseResponse;
${"\t"}${"\t"}${"\t"}} else {
${"\t"}${"\t"}${"\t"}${"\t"}throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
${"\t"}${"\t"}${"\t"}}
${"\t"}${"\t"}} catch (IORuntimeException e) {
${"\t"}${"\t"}${"\t"}log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 访问服务器失败 --" + e.getMessage());
${"\t"}${"\t"}} catch (RuntimeException e) {
${"\t"}${"\t"}${"\t"}log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + e.getMessage());
${"\t"}${"\t"}}
${"\t"}${"\t"}return null;
${"\t"}}
${"\t"}</#list>
}
