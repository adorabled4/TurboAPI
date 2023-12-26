package com.dhx.apicore.model.vo;

import com.dhx.apicore.model.enums.InterfaceCategoryEnum;
import com.dhx.apicore.model.enums.InterfaceStatusEnum;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author adorabled4
 * @className InterfaceBasicInfoVo
 * @date : 2023/04/13/ 10:31
 **/
@Data
public class InterfaceBasicInfoVO implements Serializable {

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

    private static final long serialVersionUID = 1L;
}
