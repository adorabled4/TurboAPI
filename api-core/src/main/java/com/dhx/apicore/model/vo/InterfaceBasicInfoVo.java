package com.dhx.apicore.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author adorabled4
 * @className InterfaceVo
 * @date : 2023/04/13/ 10:31
 **/
@Data
public class InterfaceBasicInfoVo implements Serializable {


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
     * 接口调用次数
     */
    private Long callTimes;

    /**
     * 接口地址
     */
    private String url;

    /**
     * 接口标签
     */
    private String tag;

    /**
     * 接口背景图片
     */
    private String background;

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
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

    private static final long serialVersionUID = 1L;
}
