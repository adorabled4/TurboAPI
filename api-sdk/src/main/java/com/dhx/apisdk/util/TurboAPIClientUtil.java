package com.dhx.apisdk.util;


import com.dhx.apisdk.client.TurboAPIClient;

/**
 * @author adorabled4
 * @className ApiClientFactory
 * @date : 2023/04/17/ 11:17
 **/
public class TurboAPIClientUtil{
    private static volatile TurboAPIClient instance;

    /**
     * 使用双检锁来获取实例
     * @return
     */
    public static TurboAPIClient getInstance(String accessKey, String secretKey) {
        if (instance == null) {
            synchronized (TurboAPIClientUtil.class) {
                if (instance == null) {
                    instance = new TurboAPIClient(accessKey,secretKey);
                }
            }
        }
        return instance;
    }
}
