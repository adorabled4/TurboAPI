package com.dhx.apicore.model.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author adorabled4
 * @className InterfaceDetailVo 接口详细信息
 * @date : 2023/04/13/ 10:48
 **/
@Data
public class InterfaceDetailVo {
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
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求头
     */
    private String requestHeader;

    /**
     * 响应头
     */
    private String responseHeader;

    /**
     * 创建人
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;


}
