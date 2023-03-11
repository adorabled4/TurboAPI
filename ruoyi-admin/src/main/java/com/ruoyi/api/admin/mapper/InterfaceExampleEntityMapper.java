package com.ruoyi.api.admin.mapper;

import java.util.List;
import com.ruoyi.api.admin.domain.InterfaceExampleEntity;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author adorabled4
 * @date 2023-03-09
 */
public interface InterfaceExampleEntityMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public InterfaceExampleEntity selectInterfaceExampleEntityById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param interfaceExampleEntity 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<InterfaceExampleEntity> selectInterfaceExampleEntityList(InterfaceExampleEntity interfaceExampleEntity);

    /**
     * 新增【请填写功能名称】
     * 
     * @param interfaceExampleEntity 【请填写功能名称】
     * @return 结果
     */
    public int insertInterfaceExampleEntity(InterfaceExampleEntity interfaceExampleEntity);

    /**
     * 修改【请填写功能名称】
     * 
     * @param interfaceExampleEntity 【请填写功能名称】
     * @return 结果
     */
    public int updateInterfaceExampleEntity(InterfaceExampleEntity interfaceExampleEntity);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteInterfaceExampleEntityById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInterfaceExampleEntityByIds(Long[] ids);
}
