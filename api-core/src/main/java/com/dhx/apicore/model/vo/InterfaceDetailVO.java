package com.dhx.apicore.model.vo;

import com.dhx.apicore.model.enums.InterfaceCategoryEnum;
import com.dhx.apicore.model.enums.InterfaceStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author adorabled4
 * @className InterfaceDetailVo 接口详细信息
 * @date : 2023/04/13/ 10:48
 **/
@Data
public class InterfaceDetailVO implements Serializable {

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
     * 接口图片
     */
    private String imageUrl;

    /**
     * 接口分类
     */
    private List<InterfaceCategoryEnum> categories;

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

    private static final long serialVersionUID = 1L;
}
