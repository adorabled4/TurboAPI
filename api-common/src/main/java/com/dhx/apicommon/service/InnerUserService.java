package com.dhx.apicommon.service;

import com.dhx.apicommon.model.to.UserTo;
import org.omg.CORBA.ServerRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.server.ServerWebExchange;

/**
 * @author adorabled4
 * @className InnerUserService
 * @date : 2023/04/19/ 13:52
 **/
public interface InnerUserService {


    /**
     * 通过token获取用户信息
     * @param token
     * @param headers
     * @return
     */
    UserTo getUserEntityByAccessToken(String token, HttpHeaders headers);

    /**
     * 通过accessKey 查询用户
     * @param accessKey
     * @return
     */
    UserTo getUserEntityByAccessKey(String accessKey);
}
