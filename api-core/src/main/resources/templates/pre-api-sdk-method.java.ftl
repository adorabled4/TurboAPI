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
import ${basePackage}.${modelName};
import com.dhx.apisdk.model.TO.WeatherInfo;
import com.dhx.apicommon.common.exception.BusinessException;
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


public class ${className} {

@Slf4j
public ${basePackage}.model.${modelName} ${methodName}() {
try {
String nowTime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
String result = HttpRequest.get(SERVER_HOST + "${callPath}").addHeaders(getHeaderMap()).execute().body();
BaseResponse baseResponse = JSONUtil.toBean(result, BaseResponse.class);
if (baseResponse.getCode() == 200) {
String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
if (dataStr == null || dataStr.equals("")) {
log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[HxApiClient] 调用接口失败 --" + baseResponse.toString());
}
${basePackage}.model.${modelName} obj = JSONUtil.toBean(dataStr, ${basePackage}.model.${modelName}.class);
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