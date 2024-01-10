package com.dhx.apiinterface.domain;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 诗句对象 t_poet
 *
 * @author adorabled4
 * @date 2023-03-11
 */
@TableName(value = "t_poet")
@Data
public class Poet {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

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

    private String createTime;

    @Override
    public String toString() {
        return "Poet{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", dynasty='" + dynasty + '\'' +
                ", title='" + title + '\'' +
                ", poetry='" + poetry + '\'' +
                '}';
    }
}
