package com.dhx.apisdk.client;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apisdk.util.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import com.dhx.apicommon.model.v1.ComputerSuffix;
import com.dhx.apicommon.model.v2.param.TakeCommentParam;
import java.lang.String;
import com.dhx.apicommon.model.v1.param.FileSuffixParam;
import com.dhx.apicommon.model.v1.LovelornSentence;

import java.lang.String;
import com.dhx.apicommon.model.v1.param.IpAnaParam;
import com.dhx.apicommon.model.v1.WeatherInfo;
import com.dhx.apicommon.model.v1.param.WeatherParam;
import com.dhx.apicommon.model.v1.PoetVO;

import com.dhx.apicommon.model.v3.ReviewTextResult;
import com.dhx.apicommon.model.v3.param.ReviewTextParam;
import java.lang.Object;
import com.dhx.apicommon.model.v3.param.IdiomParm;
import java.lang.Object;
import com.dhx.apicommon.model.v3.param.QRCodeParam;
import java.lang.Object;
import com.dhx.apicommon.model.v3.param.LocationParm;
import java.lang.Object;
import com.dhx.apicommon.model.v3.param.ConstellationParam;
import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static com.dhx.apisdk.TurboAPIClientConfig.SERVER_HOST;

@Slf4j
public class TurboAPIClient {
	
	private String accessKey;
	private String secretKey;
	
	public TurboAPIClient(String accessKey, String secretKey) {
		this.accessKey = accessKey;
		this.secretKey = secretKey;
	}
	
	public TurboAPIClient() {
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
	* <p>快速生成菜品对应的评价</p>
	* @param param com.dhx.apicommon.model.v2.param.TakeCommentParam param
	* @return BaseResponse<com.dhx.apicommon.model.v1.ComputerSuffix> response
	* @doc <a href="https://turboapi-doc.dhx.icu/docs/v2/%E5%A4%96%E5%8D%96%E8%AF%84%E4%BB%B7%E7%94%9F%E6%88%90">点击访问文档</a>
	* @build <p>Build by Adorabled4 at 2023-12-30. Driver by FreeMarker Template Engine</p>
	*/
	public BaseResponse<com.dhx.apicommon.model.v1.ComputerSuffix> callTakeOutComment(com.dhx.apicommon.model.v2.param.TakeCommentParam param ) {
		try {
			String result = HttpRequest.get(SERVER_HOST + "api/v2/takeout/comment").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param)).execute().body();
			
			
			BaseResponse<com.dhx.apicommon.model.v1.ComputerSuffix> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
			if (baseResponse.getCode() == 200) {
				String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
				if (dataStr == null || dataStr.equals("")) {
					log.error("\u001B[31m" + this.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + baseResponse.toString());
				}
				return baseResponse;
			} else {
				throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
			}
		} catch (IORuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 访问服务器失败 --" + e.getMessage());
		} catch (RuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + e.getMessage());
		}
		return null;
	}
	
	
	/**
	* <p>输入文件名信息, 返回对应的说明信息</p>
	* @param param com.dhx.apicommon.model.v1.param.FileSuffixParam param
	* @return BaseResponse<java.lang.String> response
	* @doc <a href="https://turboapi-doc.dhx.icu/docs/v1/%E6%96%87%E4%BB%B6%E5%90%8D%E5%90%8E%E7%BC%80%E4%BF%A1%E6%81%AF%E6%9F%A5%E8%AF%A2">点击访问文档</a>
	* @build <p>Build by Adorabled4 at 2023-12-30. Driver by FreeMarker Template Engine</p>
	*/
	public BaseResponse<java.lang.String> callSuffixInfo(com.dhx.apicommon.model.v1.param.FileSuffixParam param ) {
		try {
			String result = HttpRequest.get(SERVER_HOST + "api/v1/common/suffix").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param)).execute().body();
			
			
			BaseResponse<java.lang.String> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
			if (baseResponse.getCode() == 200) {
				String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
				if (dataStr == null || dataStr.equals("")) {
					log.error("\u001B[31m" + this.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + baseResponse.toString());
				}
				return baseResponse;
			} else {
				throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
			}
		} catch (IORuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 访问服务器失败 --" + e.getMessage());
		} catch (RuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + e.getMessage());
		}
		return null;
	}
	
	
	/**
	* <p>随机一条失恋文案, 感情遇到挫折也不要灰心, 下一个更好~</p>
	* 
	* @return BaseResponse<com.dhx.apicommon.model.v1.LovelornSentence> response
	* @doc <a href="https://turboapi-doc.dhx.icu/docs/v1/%20%E9%9A%8F%E6%9C%BA%E4%B8%80%E6%9D%A1%E5%A4%B1%E6%81%8B%E6%96%87%E6%A1%88">点击访问文档</a>
	* @build <p>Build by Adorabled4 at 2023-12-30. Driver by FreeMarker Template Engine</p>
	*/
	public BaseResponse<com.dhx.apicommon.model.v1.LovelornSentence> callLovelornSentence() {
		try {
			
			String result = HttpRequest.get(SERVER_HOST + "api/v1/common/lovelorn").addHeaders(getHeaderMap()).execute().body();
			
			BaseResponse<com.dhx.apicommon.model.v1.LovelornSentence> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
			if (baseResponse.getCode() == 200) {
				String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
				if (dataStr == null || dataStr.equals("")) {
					log.error("\u001B[31m" + this.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + baseResponse.toString());
				}
				return baseResponse;
			} else {
				throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
			}
		} catch (IORuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 访问服务器失败 --" + e.getMessage());
		} catch (RuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + e.getMessage());
		}
		return null;
	}
	
	
	/**
	* <p>输入IPV4地址, 返回IP属地信息</p>
	* @param param com.dhx.apicommon.model.v1.param.IpAnaParam param
	* @return BaseResponse<java.lang.String> response
	* @doc <a href="https://turboapi-doc.dhx.icu/docs/v1/%20IP%E5%B1%9E%E5%9C%B0%E8%A7%A3%E6%9E%90">点击访问文档</a>
	* @build <p>Build by Adorabled4 at 2023-12-30. Driver by FreeMarker Template Engine</p>
	*/
	public BaseResponse<java.lang.String> callIpAnalysis(com.dhx.apicommon.model.v1.param.IpAnaParam param ) {
		try {
			String result = HttpRequest.get(SERVER_HOST + "api/v1/common/ip/ana").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param)).execute().body();
			
			
			BaseResponse<java.lang.String> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
			if (baseResponse.getCode() == 200) {
				String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
				if (dataStr == null || dataStr.equals("")) {
					log.error("\u001B[31m" + this.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + baseResponse.toString());
				}
				return baseResponse;
			} else {
				throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
			}
		} catch (IORuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 访问服务器失败 --" + e.getMessage());
		} catch (RuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + e.getMessage());
		}
		return null;
	}
	
	
	/**
	* <p>输入地区名称, 返回当地天气情况</p>
	* @param param com.dhx.apicommon.model.v1.param.WeatherParam param
	* @return BaseResponse<com.dhx.apicommon.model.v1.WeatherInfo> response
	* @doc <a href="https://turboapi-doc.dhx.icu/docs/v1/%20%E6%AF%8F%E6%97%A5%E5%A4%A9%E6%B0%94%E6%83%85%E5%86%B5">点击访问文档</a>
	* @build <p>Build by Adorabled4 at 2023-12-30. Driver by FreeMarker Template Engine</p>
	*/
	public BaseResponse<com.dhx.apicommon.model.v1.WeatherInfo> callWeatherNow(com.dhx.apicommon.model.v1.param.WeatherParam param ) {
		try {
			String result = HttpRequest.get(SERVER_HOST + "api/v1/common/weather/now").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param)).execute().body();
			
			
			BaseResponse<com.dhx.apicommon.model.v1.WeatherInfo> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
			if (baseResponse.getCode() == 200) {
				String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
				if (dataStr == null || dataStr.equals("")) {
					log.error("\u001B[31m" + this.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + baseResponse.toString());
				}
				return baseResponse;
			} else {
				throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
			}
		} catch (IORuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 访问服务器失败 --" + e.getMessage());
		} catch (RuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + e.getMessage());
		}
		return null;
	}
	
	
	/**
	* <p>随机返回一句诗词, 包含作者, 朝代等信息</p>
	* 
	* @return BaseResponse<com.dhx.apicommon.model.v1.PoetVO> response
	* @doc <a href="https://turboapi-doc.dhx.icu/docs/v1/%20%E9%9A%8F%E6%9C%BA%E8%AF%97%E8%AF%8D~">点击访问文档</a>
	* @build <p>Build by Adorabled4 at 2023-12-30. Driver by FreeMarker Template Engine</p>
	*/
	public BaseResponse<com.dhx.apicommon.model.v1.PoetVO> callRandomPoet() {
		try {
			
			String result = HttpRequest.get(SERVER_HOST + "api/v1/common/poet/random").addHeaders(getHeaderMap()).execute().body();
			
			BaseResponse<com.dhx.apicommon.model.v1.PoetVO> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
			if (baseResponse.getCode() == 200) {
				String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
				if (dataStr == null || dataStr.equals("")) {
					log.error("\u001B[31m" + this.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + baseResponse.toString());
				}
				return baseResponse;
			} else {
				throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
			}
		} catch (IORuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 访问服务器失败 --" + e.getMessage());
		} catch (RuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + e.getMessage());
		}
		return null;
	}
	
	
	/**
	* <p>敏感词过滤,将text中的敏感词信息变成**, 即便是通过字符分隔也能正确识别到。单次请求text最长为4096个字符</p>
	* @param param com.dhx.apicommon.model.v3.param.ReviewTextParam param
	* @return BaseResponse<com.dhx.apicommon.model.v3.ReviewTextResult> response
	* @doc <a href="https://turboapi-doc.dhx.icu/docs/v3/%20%E6%95%8F%E6%84%9F%E8%AF%8D%E8%BF%87%E6%BB%A4">点击访问文档</a>
	* @build <p>Build by Adorabled4 at 2023-12-30. Driver by FreeMarker Template Engine</p>
	*/
	public BaseResponse<com.dhx.apicommon.model.v3.ReviewTextResult> callReviewText(com.dhx.apicommon.model.v3.param.ReviewTextParam param ) {
		try {
			String result = HttpRequest.post(SERVER_HOST + "api/v3/review/text").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param)).execute().body();
			
			BaseResponse<com.dhx.apicommon.model.v3.ReviewTextResult> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
			if (baseResponse.getCode() == 200) {
				String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
				if (dataStr == null || dataStr.equals("")) {
					log.error("\u001B[31m" + this.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + baseResponse.toString());
				}
				return baseResponse;
			} else {
				throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
			}
		} catch (IORuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 访问服务器失败 --" + e.getMessage());
		} catch (RuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + e.getMessage());
		}
		return null;
	}
	
	
	/**
	* <p>输入成语,返回成语的拼音,详细解释,出处,近义词同义词等相关信息</p>
	* @param param com.dhx.apicommon.model.v3.param.IdiomParm param
	* @return BaseResponse<java.lang.Object> response
	* @doc <a href="https://turboapi-doc.dhx.icu/docs/v3/">点击访问文档</a>
	* @build <p>Build by Adorabled4 at 2023-12-30. Driver by FreeMarker Template Engine</p>
	*/
	public BaseResponse<java.lang.Object> callIdiomMeaning(com.dhx.apicommon.model.v3.param.IdiomParm param ) {
		try {
			String result = HttpRequest.get(SERVER_HOST + "api/v3/idiom").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param)).execute().body();
			
			
			BaseResponse<java.lang.Object> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
			if (baseResponse.getCode() == 200) {
				String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
				if (dataStr == null || dataStr.equals("")) {
					log.error("\u001B[31m" + this.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + baseResponse.toString());
				}
				return baseResponse;
			} else {
				throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
			}
		} catch (IORuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 访问服务器失败 --" + e.getMessage());
		} catch (RuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + e.getMessage());
		}
		return null;
	}
	
	
	/**
	* <p>二维码生成API，支持文本、背景色、尺寸、边距等参数设置。</p>
	* @param param com.dhx.apicommon.model.v3.param.QRCodeParam param
	* @return BaseResponse<java.lang.Object> response
	* @doc <a href="https://turboapi-doc.dhx.icu/docs/v3/生成二维码API">点击访问文档</a>
	* @build <p>Build by Adorabled4 at 2023-12-30. Driver by FreeMarker Template Engine</p>
	*/
	public BaseResponse<java.lang.Object> callQRCode(com.dhx.apicommon.model.v3.param.QRCodeParam param ) {
		try {
			String result = HttpRequest.get(SERVER_HOST + "api/v3/jqrcode").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param)).execute().body();
			
			
			BaseResponse<java.lang.Object> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
			if (baseResponse.getCode() == 200) {
				String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
				if (dataStr == null || dataStr.equals("")) {
					log.error("\u001B[31m" + this.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + baseResponse.toString());
				}
				return baseResponse;
			} else {
				throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
			}
		} catch (IORuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 访问服务器失败 --" + e.getMessage());
		} catch (RuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + e.getMessage());
		}
		return null;
	}
	
	
	/**
	* <p>全国行政区划(省/市/区/县)查询API部署包，支持省、市、区县、乡镇查询，最大为4级。</p>
	* @param param com.dhx.apicommon.model.v3.param.LocationParm param
	* @return BaseResponse<java.lang.Object> response
	* @doc <a href="https://turboapi-doc.dhx.icu/docs/v3/全国行政区划查询API">点击访问文档</a>
	* @build <p>Build by Adorabled4 at 2023-12-30. Driver by FreeMarker Template Engine</p>
	*/
	public BaseResponse<java.lang.Object> callLocationSearch(com.dhx.apicommon.model.v3.param.LocationParm param ) {
		try {
			String result = HttpRequest.get(SERVER_HOST + "api/v3/jxzqh").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param)).execute().body();
			
			
			BaseResponse<java.lang.Object> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
			if (baseResponse.getCode() == 200) {
				String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
				if (dataStr == null || dataStr.equals("")) {
					log.error("\u001B[31m" + this.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + baseResponse.toString());
				}
				return baseResponse;
			} else {
				throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
			}
		} catch (IORuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 访问服务器失败 --" + e.getMessage());
		} catch (RuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + e.getMessage());
		}
		return null;
	}
	
	
	/**
	* <p>星座查询API，根据日期或星座名称,查询星座详细信息。</p>
	* @param param com.dhx.apicommon.model.v3.param.ConstellationParam param
	* @return BaseResponse<java.lang.Object> response
	* @doc <a href="https://turboapi-doc.dhx.icu/docs/v3/星座查询API">点击访问文档</a>
	* @build <p>Build by Adorabled4 at 2023-12-30. Driver by FreeMarker Template Engine</p>
	*/
	public BaseResponse<java.lang.Object> callConstellationSearch(com.dhx.apicommon.model.v3.param.ConstellationParam param ) {
		try {
			String result = HttpRequest.get(SERVER_HOST + "api/v3/jxzcx").addHeaders(getHeaderMap()).body(JSONUtil.toJsonStr(param)).execute().body();
			
			
			BaseResponse<java.lang.Object> baseResponse = JSONUtil.toBean(result, BaseResponse.class);
			if (baseResponse.getCode() == 200) {
				String dataStr = JSONUtil.toJsonStr(baseResponse.getData());
				if (dataStr == null || dataStr.equals("")) {
					log.error("\u001B[31m" + this.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + baseResponse.toString());
				}
				return baseResponse;
			} else {
				throw new BusinessException(baseResponse.getCode(), baseResponse.getMessage());
			}
		} catch (IORuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 访问服务器失败 --" + e.getMessage());
		} catch (RuntimeException e) {
			log.error("\u001B[31m" + e.getClass() + "\u001B[0m: " + "[TurboApiClient] 调用接口失败 --" + e.getMessage());
		}
		return null;
	}
	
}
