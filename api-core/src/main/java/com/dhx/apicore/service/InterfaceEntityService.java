package com.dhx.apicore.service;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicore.model.DO.InterfaceEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVo;

import java.util.List;

/**
* @author dhx
* @description 针对表【t_interface_entity】的数据库操作Service
* @createDate 2023-04-12 09:38:35
*/
public interface InterfaceEntityService extends IService<InterfaceEntity> {

    /**
     * 获取接口列表(首页)
     * @param pageSize
     * @param current
     * @return
     */
    List<InterfaceBasicInfoVo> getInterfaceList(int pageSize, int current);

    /**
     * 获取接口详细信息
     * @param id
     * @return
     */
    BaseResponse getInterfaceDetail(Long id);


    /**
     * 获取接口调用示例
     * @param id
     * @return
     */
    BaseResponse getInterfaceExample(Long id);
}
