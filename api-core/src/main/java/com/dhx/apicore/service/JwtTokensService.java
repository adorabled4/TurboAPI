package com.dhx.apicore.service;


import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.model.DTO.JwtToken;
import com.dhx.apicore.model.DTO.UserDTO;

/**
* @author dhx
* @description 针对【jwt_tokens】的操作Service
* @createDate 2023-04-10 18:48:30
*/
public interface JwtTokensService  {


    /**
     * 生成JWT访问token
     * @param user
     * @return
     */
    String generateAccessToken(UserEntity user);


    /**
     * 生成refreshToken
     * @param user
     * @return
     */
    String generateRefreshToken(UserEntity user);


    /**
     * 验证token
     *
     * @param token
     * @return
     */
    UserEntity validateToken(String token);

    /**
     * 获取令牌中的用户id
     * @param token
     * @return
     */
    String getUserIdFromToken(String token);

    /**
     * 撤销JWT令牌
     * @param user
     */
    public void revokeToken(UserEntity user) ;


    /**
     * 验证token是否过期
     * @param token
     * @return
     */
    boolean isTokenExpired(String token);

    /**
     * 清除过期的令牌
     */
    void cleanExpiredTokens();

    /**
     * 保存token到redis
     * @param jwtToken
     * @param user
     */
    void save2Redis(JwtToken jwtToken,UserEntity user);
}
