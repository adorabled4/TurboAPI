package com.dhx.apisdk.model.TO;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @TableName t_computer_suffix
 */
@Data
public class ComputerSuffix implements Serializable {
    /**
     * 后缀名
     */
    private String suffix;

    /**
     * 描述
     */
    private String description;

    /**
     * 分类
     */
    private String category;


    private static final long serialVersionUID = 1L;

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}