package com.dhx.apicore.model.vo;

import com.dhx.apicore.common.constant.UserConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

import static com.dhx.apicore.common.constant.UserConstant.USER_NAME_REGEX;

/**
 * @author adorabled4
 * @className UserVo
 * @date : 2023/04/10/ 15:03
 * 注意需要写上@Data注解, 否则会出现获取不到值的情况
 **/
@Data
public class UserVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long userId;

    /**
     * 账户名 : 4到16位（字母，数字，下划线，减号）
     */
    @Pattern(regexp = UserConstant.USER_ACCOUNT_REGEX,message = "账户名不符合规范")
    private String userAccount;

    /**
     * 用户昵称 : 4到16位（字母，数字，下划线，减号）
     */
    @Pattern(regexp = USER_NAME_REGEX,message = "账户名不符合规范")
    private String userName;


    /**
     * 用户头像
     */
    private String avatarUrl;

    /**
     * AK
     */
    private String accessKey;

    /**
     * SK
     */
    private String secretKey;

    /**
     * 手机号
     */
    @Pattern(regexp = UserConstant.PHONE_REGEX,message = "手机号不符合规范")
    private String phone;

    /**
     * 电子邮箱
     */
    @Email(regexp = UserConstant.EMAIL_REGEX,message = "邮箱格式不符合规范")
    private String email;

    /**
     * 生日
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date birthday;

    /**
     * 性别：0-男，1-女
     */
    @NotNull(message = "性别不能为空")
    private Integer gender;


    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

}
