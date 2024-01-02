package com.dhx.apicommon.model.to;

import com.dhx.apicommon.model.enums.InterfaceStatusEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
     * 花费
     */
    private Integer cost;

    /**
     * 接口图片
     */
    private String imageUrl;

    /**
     * 接口状态
     */
    private InterfaceStatusEnum status;

    /**
     * 接口文档地址
     */
    private String docUrl;

    /**
     * 请求方式
     */
    private String requestMethod;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求头
     */
    private String requestHeaders;

    /**
     * 调用路径
     */
    private String callPath;

    /**
     * 服务地址
     */
    private String serviceAddress;

    /**
     * 请求示例
     */
    private String requestExample;

    /**
     * 响应示例
     */
    private String responseExample;

    /**
     * 总调用次数
     */
    private Long totalCallCount;

    /**
     * 是否是异步接口
     */
    private boolean isAsync;


    private static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date createTime;

}
