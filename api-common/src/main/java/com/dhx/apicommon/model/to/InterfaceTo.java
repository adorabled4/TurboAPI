package com.dhx.apicommon.model.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author adorabled4
 * @className InterfaceTo
 * @date : 2023/04/19/ 14:33
 **/
@Data
public class InterfaceTo implements Serializable {
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


    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

}
