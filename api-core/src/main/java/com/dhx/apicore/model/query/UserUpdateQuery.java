package com.dhx.apicore.model.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Date;

/**
 * @author adorabled4
 * @className UserUpdateQuery
 * @date : 2023/12/27/ 20:21
 **/
@Data
public class UserUpdateQuery implements Serializable {

    /**
     * 昵称
     */
    private String userName;


    /**
     * 地址
     */
    private String address;

    /**
     * 头像地址
     */
    private String avatarUrl;

    /**
     * 性别1男0女
     */
    private Integer gender;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Nullable
    private Date birth;

    private static final long serialVersionUID = 1L;
}

