package com.dhx.apicore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apicore.model.DO.InterfaceVariableInfoEntity;

/**
* @author dhx
* @description 针对表【t_interface_example_entity】的数据库操作Service
* @createDate 2023-04-12 09:38:35
*/
public interface InterfaceVariableInfoService extends IService<InterfaceVariableInfoEntity> {

    InterfaceVariableInfoEntity findById(Long id);
}
