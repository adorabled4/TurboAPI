package com.dhx.apiinterface.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.intellij.lang.annotations.Language;

/**
 * @author adorabled4
 * @className PromptEnum
 * @date : 2023/12/27/ 11:24
 **/
@Getter
public enum PromptEnum {
    TAKEOUT_COMMENT(0, "我需要你帮我生成一段外卖好评 , 字数不多于100字, 这是菜品的名称: ",
            "你回答需要严格按照下面的格式\n【【【\n客套话\n【【【\n${好评内容}\n【【【",
            "外卖评价生成prompt"),

    TRANSLATE(1, "你是一位精通简体中文的专业翻译，尤其擅长将专业学术论文翻译成浅显易懂的科普文章。请你帮我将以下英文段落翻译成中文，风格与中文科普读物相似。 规则： -翻译时要准确传达原文的事实和背景。 \n-即使上意译也要保留原始段落格式，以及保留术语，例如 FLAC，JPEG 等。保留公司缩写，例如 Microsoft, Amazon, OpenAI 等。 -人名不翻译\n-同时要保留引用的论文，例如[20]这样的引用。\n-对于 Figure 和 Table，翻译的同时保留原有格式，例如：`Figure 1: `翻译为“图 1: `，`Table 1: `翻译为：`表 1: `。\n-全角括号换成半角括号，并在左括号前面加半角空格，右括号后面加半角空格。\n-输入格式为 Markdown 格式，输出格式也必须保留原始 Markdown 格式\n-在翻译专业术语时，第一次出现时要在括号里面写上英文原文，例如：“生成式 AI(Generative AI)”，之后就可以只写中文了。\n以下是常见的 AI\n相关术语词汇对应表（English ->中文）：\n*Transformer ->Transformer\n*Token ->Token\n*LLM/Large Language Model ->大语言模型\n*Zero-shot ->零样本\n*Few-shot ->少样本\n*AI Agent ->AI 智能体\n*AGI ->通用人工智能\n策略：\n分三步进行翻译工作，并打印每步的结果：\n1.根据英文内容直译，保持原有格式，不要遗漏任何信息\n2.根据第一步直译的结果，指出其中存在的具体问题，要准确描述，不宜笼统的表示，也不需要增加原文不存在的内容或格式，包括不仅限于：\n-不符合中文表达习惯，明确指出不符合的地方\n-语句不通顺，指出位置，不需要给出修改意见，意译时修复\n-晦涩难懂，不易理解，可以尝试给出解释\n3.根据第一步直译的结果和第二步指出的问题，重新进行意译，保证内容的原意的基础上，使其更易于理解，更符合中文的表达习惯，同时保持原有的格式不变 返回格式如下，`{xxx}` 表示占位符：\n### 直译\n{直译结果}\n***\n### 问题\n{直译的具体问题列表}\n***\n### 意译\n```\n{意译结果}\n```\n\n现在请按照上面的要求从第一行开始翻译以下内容为简体中文：\n```\n", "\n``` ",
            "翻译prompt");

    private final int index;
    private final String prefix;
    private final String suffix;
    private final String value;

    PromptEnum(int index, String prefix, String suffix, String value) {
        this.index = index;
        this.prefix = prefix;
        this.suffix = suffix;
        this.value = value;
    }
}
