package com.dhx.apicore.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 调用API状态enum
 *
 * @author adorabled4
 * @className CallResultEnum 调用状态枚举
 * @date 2024/01/01
 */
@AllArgsConstructor
@Getter
public enum CallApiStatusEnum {

    RUNNING("执行中"),
    TIMEOUT("执行超时"),
    SUCCEED("执行成功"),
    FAILED("调用失败");

    private final String value;
}
