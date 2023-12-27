package com.dhx.apicore.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author adorabled4
 * @className MailEnum
 * @date : 2023/12/27/ 17:54
 **/
@Getter
@AllArgsConstructor
public enum MailEnum {

    /**
     * 验证码
     */
    VERIFY_CODE("[TurboAPI]您的验证码为: ", "请确保信息安全, 不要泄露您的验证码", "[hxBI]验证码");

    /**
     * 前缀
     */
    private final String prefix;
    /**
     * 后缀
     */
    private final String suffix;
    /**
     * 标题
     */
    private final String title;

}
