package com.dhx.apiinterface.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author adorabled4
 * @className PromptEnum
 * @date : 2023/12/27/ 11:24
 **/
@AllArgsConstructor
@Getter
public enum PromptEnum {

    TAKEOUT_COMMENT(0, "我需要你帮我生成一段外卖好评 , 字数不多于100字, 这是菜品的名称: ", "你回答需要严格按照下面的格式\n" +
            "【【【\n" +
            "客套话\n" +
            "【【【\n" +
            "${好评内容}\n" +
            "【【【", "外卖评价生成prompt");

    private final int index;

    private final String prefix;

    private final String suffix;

    private final String value;


}
