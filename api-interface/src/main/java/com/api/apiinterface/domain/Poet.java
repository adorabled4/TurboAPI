package com.api.apiinterface.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 诗句对象 t_poet
 * 
 * @author adorabled4
 * @date 2023-03-11
 */
public class Poet extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 作者 */
//    @Excel(name = "作者")
    private String author;

    /** 朝代 */
//    @Excel(name = "朝代")
    private String dynasty;

    /** 题目 */
//    @Excel(name = "题目")
    private String title;

    /** 诗句 */
//    @Excel(name = "诗句")
    private String poetry;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setAuthor(String author) 
    {
        this.author = author;
    }

    public String getAuthor() 
    {
        return author;
    }
    public void setDynasty(String dynasty) 
    {
        this.dynasty = dynasty;
    }

    public String getDynasty() 
    {
        return dynasty;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setPoetry(String poetry) 
    {
        this.poetry = poetry;
    }

    public String getPoetry() 
    {
        return poetry;
    }

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
