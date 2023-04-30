package com.dhx.apicore.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author adorabled4
 * @className InterfaceRankInfoVo
 * @date : 2023/04/30/ 11:35
 **/
@Data
public class InterfaceRankInfoVo implements Serializable {


    private static final long serialVersionUID = 1L;

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
     * 接口调用次数
     */
    private Long callTimes;

    /**
     * 接口标签
     */
    private String tag;

    /**
     * 接口背景图片
     */
    private String background;

}
