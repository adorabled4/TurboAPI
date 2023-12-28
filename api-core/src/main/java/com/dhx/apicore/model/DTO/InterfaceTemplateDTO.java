package com.dhx.apicore.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 接口模板dto
 *
 * @author adorabled4
 * @className InterfaceTemplateDTO 接口模板dto
 * @date 2023/12/28
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InterfaceTemplateDTO implements Serializable {

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
    private List<String> categories;

    /**
     * 接口状态
     */
    private String status;

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

    private static final long serialVersionUID = 1L;
}
