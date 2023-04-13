package com.dhx.apicore.constants;

import java.util.concurrent.TimeUnit;

/**
 * @author adorabled4
 * @className RedisConstant
 * @date : 2023/04/12/ 13:26
 **/
public class RedisConstant {

    /**
     *  jwt 秘钥
     */
    public static final String SECRET_KEY = "mySecretKey";

    /**
     * token过期时间
     */
    public static final long EXPIRATION_TIME = TimeUnit.DAYS.toSeconds(1);

    /**
     * access_token 前缀
     */
    public static final String ACCESS_TOKEN_PREFIX = "access_token:";

    /**
     * 刷新token 前缀
     */
    public static final String REFRESH_TOKEN_PREFIX = "refresh_token:";
}
