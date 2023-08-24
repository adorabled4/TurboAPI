package com.dhx.apicore.model.param;

import com.dhx.apicore.common.constant.UserConstant;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

/**
 * @author adorabled4
 * @className RegisterParam 用户注册参数
 * @date : 2023/04/10/ 17:22
 **/
@Data
@Valid
public class RegisterParam {
    /**
     * 4~16位 数字,大小写字母组成
     */
    @Pattern(regexp = UserConstant.USER_NAME_REGEX,message = "用户名不符合规范")
    private String userName;


    /**
     * 至少8-16个字符，至少1个大写字母，1个小写字母和1个数字，其他可以是任意字符
     */
    @Pattern(regexp = UserConstant.PASSWORD_REGEX,message = "密码不符合规范")
    private String password;

    private String checkPassword;
}
