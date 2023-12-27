package com.dhx.apicore.model.query;

import com.dhx.apicore.common.constant.UserConstant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * @author adorabled4
 * @className QuickLoginEmailRequest
 * @date : 2023/12/27/ 19:36
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuickLoginEmailRequest {

    /**
     * 4~16位 数字,大小写字母组成
     */
    @Pattern(regexp = UserConstant.EMAIL_REGEX,message = "邮箱不符合规范")
    private String email;


    /**
     * 代码
     */
    @Pattern(regexp = "[0-9]{6}", message = "验证码错误!")
    private String code;
}
