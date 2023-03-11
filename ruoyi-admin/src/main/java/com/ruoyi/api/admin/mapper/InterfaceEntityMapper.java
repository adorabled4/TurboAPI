package com.ruoyi.api.admin.mapper;

import java.util.List;
import com.ruoyi.api.admin.domain.InterfaceEntity;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author adorabled4
 * @date 2023-03-10
 */
public interface InterfaceEntityMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public InterfaceEntity selectInterfaceEntityById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param interfaceEntity 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<InterfaceEntity> selectInterfaceEntityList(InterfaceEntity interfaceEntity);

    /**
     * 新增【请填写功能名称】
     * 
     * @param interfaceEntity 【请填写功能名称】
     * @return 结果
     */
    public int insertInterfaceEntity(InterfaceEntity interfaceEntity);

    /**
     * 修改【请填写功能名称】
     * 
     * @param interfaceEntity 【请填写功能名称】
     * @return 结果
     */
    public int updateInterfaceEntity(InterfaceEntity interfaceEntity);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteInterfaceEntityById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInterfaceEntityByIds(Long[] ids);
}
