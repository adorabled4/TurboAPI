package com.dhx.apisdk.client;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apisdk.util.SignUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

import static com.dhx.apisdk.HxApiClientConfig.SERVER_HOST;

@Slf4j
public class TurboAPIClientImpl {
	
	private String accessKey;
	private String secretKey;
	
	public TurboAPIClientImpl(String accessKey, String secretKey) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;
	}
	
	public TurboAPIClientImpl() {
	}
	
	Map<String, String> getHeaderMap() {
		Map<String, String> hashMap = new HashMap<>();
		hashMap.put("accessKey", accessKey);
		hashMap.put("nonce", RandomUtil.randomNumbers(4));
		hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
		hashMap.put("sign", SignUtil.genSign("", secretKey)); // 空字符串作为 body 参数
		return hashMap;
	}
	
	
	/**
	* <p>获取随机的诗词信息</p>
	* 
	* @return BaseResponse<com.dhx.apicommon.model.v1.Poet> response
	* @doc <a href="https://blog.dhx.icu">点击访问文档</a>
	* @build <p>Build by Adorabled4 at 2023-12-29. Driver by FreeMarker Template Engine</p>
	*/
	public BaseResponse<com.dhx.apicommon.model.v1.Poet> callRandomPoet() {
		try {
			
			String result = HttpRequest.get(SERVER_HOST + "api/v1/common/poet/random").addHeaders(getHeaderMap()).execute().body();
			
			BaseResponse<com.dhx.apicommon.model.v1.Poet> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
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
	
}
