package com.dhx.apisdk.client;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.dhx.apisdk.model.TO.Poet;
import com.dhx.apisdk.model.common.BaseResponse;
import com.dhx.apisdk.model.exception.BusinessException;
import com.dhx.apisdk.util.SignUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adorabled4
 * @className HxApiClient
 * @date : 2023/04/14/ 12:51
 **/
public class HxApiClient {
    public static final String SERVER_HOST= "http://localhost:88";

    private String accessKey;

    private String secretKey;

    public HxApiClient(String accessKey, String secretKey){
        this.accessKey=accessKey;
        this.secretKey=secretKey;
    }

    public Poet getRandomPoet(){
        try{
            //可以单独传入http参数，这样参数会自动做URL编码，拼接在URL中
            String result = HttpUtil.get(SERVER_HOST + "/dev-api/api/apiinterface/poet/random");
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
            System.out.println("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 访问服务器失败 --"+e.getMessage() );
        }catch(RuntimeException e){
            System.out.println("\u001B[31m" + e.getClass() + "\u001B[0m: " +"[HxApiClient] 调用接口失败 --"+e.getMessage() );
        }
        return null;
    }

    private Map<String, String> getHeaderMap(String body) {
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("accessKey", accessKey);
        // 一定不能直接发送
//        hashMap.put("secretKey", secretKey);
        hashMap.put("nonce", RandomUtil.randomNumbers(4));
        hashMap.put("body", body);
        hashMap.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        hashMap.put("sign", SignUtil.genSign(body, secretKey));
        return hashMap;
    }

}
