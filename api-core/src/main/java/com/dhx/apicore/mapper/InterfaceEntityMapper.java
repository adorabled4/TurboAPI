package com.dhx.apicore.mapper;

import com.dhx.apicore.model.DO.InterfaceEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author dhx
* @description 针对表【t_interface_entity】的数据库操作Mapper
* @createDate 2023-04-12 09:38:35
* @Entity generator.domain.InterfaceEntity
*/
public interface InterfaceEntityMapper extends BaseMapper<InterfaceEntity> {

    @Select("SELECT tag FROM t_interface_entity GROUP BY tag")
    List<String> getTags();
}




