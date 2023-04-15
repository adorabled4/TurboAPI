package com.dhx.apicore.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author adorabled4
 * @className InterfaceVo
 * @date : 2023/04/13/ 10:31
 **/
@Data
public class InterfaceBasicInfoVo {
    /**
     * 主键
     */
    private Long id;

    /**
     * 接口名称
     */
    private String name;

    /**
     * 接口描述
     */
    private String description;

    /**
     * 是否免费
     */
    private Integer isFree;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 请求方式
     */
    private String method;


    /**
     * 创建人
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;

    private static final long serialVersionUID = 1L;
}