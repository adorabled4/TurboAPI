package com.dhx.apicommon.model.v1;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dhx_
 * @className PoetVO
 * @date : 2023/03/13/ 12:26
 **/
@Data
public class PoetVO implements Serializable {
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

}
