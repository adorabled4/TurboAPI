package com.dhx.apicore.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @author adorabled4
 * @className InterfaceTagVo
 * @date : 2023/04/30/ 14:18
 **/
@Data
public class InterfaceTagVo {

    /**
     * 接口的分类
     */
    private String tag;

    /**
     * 接口列表
     */
    List<InterfaceBasicInfoVO> interfaceBasicInfoVOS;
}

