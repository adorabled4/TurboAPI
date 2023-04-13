package com.dhx.apicore.model.DTO;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName  仅仅用于判断token是否过期 ,具体的通过token查询数据是通过jwtUtils来解密token
 */
@Data
public class JwtToken implements Serializable {

    private String token;
    private String refreshToken;

    public JwtToken(String token, String refreshToken) {
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}