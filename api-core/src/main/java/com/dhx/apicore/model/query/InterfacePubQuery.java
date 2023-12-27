package com.dhx.apicore.model.query;

import com.dhx.apicore.model.enums.InterfaceCategoryEnum;
import com.dhx.apicore.model.enums.InterfaceStatusEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author adorabled4
 * @className InterfacePubQuery
 * @date : 2023/12/26/ 14:32
 **/
@Data
@NoArgsConstructor
public class InterfacePubQuery implements Serializable {

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
     * 接口类型(免费, 付费, 限时免费, 会员免费)
     */
    private Integer type;

    /**
     * 接口状态
     */
    private InterfaceStatusEnum status;

    /**
     * 枚举类 : 仅仅用于在接口调用的时候提示, 实际上不使用这个参数
     */
    private InterfaceCategoryEnum mockCategoryEnum;

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
     * 是否是AIGC相关接口
     */
    private Boolean isAigc;

    private static final long serialVersionUID = 1L;

}
