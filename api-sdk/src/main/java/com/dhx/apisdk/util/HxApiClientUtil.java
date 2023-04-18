package com.dhx.apisdk.util;

import com.dhx.apisdk.client.HxApiClient;
import com.dhx.apisdk.client.impl.HxApiClientImpl;

/**
 * @author adorabled4
 * @className ApiClientFactory
 * @date : 2023/04/17/ 11:17
 **/
public class HxApiClientUtil{
    private static volatile HxApiClient instance;

    /**
     * 使用双检锁来获取实例
     * @return
     */
    public static HxApiClient getInstance(String accessKey, String secretKey) {
        if (instance == null) {
            synchronized (HxApiClientUtil.class) {
                if (instance == null) {
                    instance = new HxApiClientImpl(accessKey,secretKey);
                }
            }
        }
        return instance;
    }
}
