package com.dhx.apisdk.model.TO;

import lombok.Data;

import java.io.Serializable;

/**
 * @author adorabled4
 * @className Poet
 * @date : 2023/04/14/ 12:53
 **/
@Data
public class Poet implements Serializable {
    /**
     * 作者
     */
    private String author;

    /**
     * 朝代
     */
    private String dynasty;

    /**
     * 题目
     */
    private String title;

    /**
     * 诗句
     */
    private String poetry;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        String authorColor = "\033[0;33m"; // 设置作者颜色为黄色
        String dynastyColor = "\033[0;36m"; // 设置朝代颜色为青色
        String resetColor = "\033[0m"; // 重置控制字符，避免影响后续输出

        StringBuilder sb = new StringBuilder();
        sb.append("题目：").append(title).append("\n");
        sb.append(authorColor).append("作者：").append(author).append(resetColor).append("\n");
        sb.append(dynastyColor).append("朝代：").append(dynasty).append(resetColor).append("\n");
        sb.append("诗句：\n").append(poetry).append("\n");

        return sb.toString();
    }

}
