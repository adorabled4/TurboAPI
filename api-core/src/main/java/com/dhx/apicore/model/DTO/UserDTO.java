package com.dhx.apicore.model.DTO;

import lombok.Data;

import java.util.Date;

/**
 * @author adorabled4
 * @className UserDTO
 * @date : 2023/04/10/ 18:35
 **/
@Data
public class UserDTO {

    /**
     * id
     */
    private Long userId;

//    /**
//     * 微信OpenId（为了用户登录）
//     */
//    private String wxOpenid;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * 用户角色
     */
    private String userRole;
//    /**
//     * 性别：0-男，1-女
//     */
//    private Integer gender;


    public UserDTO(Long userId, String userName, String avatarUrl) {
        this.userId = userId;
        this.userName = userName;
        this.avatarUrl = avatarUrl;
    }
}
