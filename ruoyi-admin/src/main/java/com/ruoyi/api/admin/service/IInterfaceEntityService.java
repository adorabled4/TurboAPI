package com.ruoyi.api.admin.service;

import java.util.List;
import com.ruoyi.api.admin.domain.InterfaceEntity;
import com.ruoyi.api.admin.vo.InterfaceInfoVo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author adorabled4
 * @date 2023-03-10
 */
public interface IInterfaceEntityService 
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
    public List<InterfaceInfoVo> selectInterfaceEntityList(InterfaceEntity interfaceEntity);

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
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteInterfaceEntityByIds(Long[] ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteInterfaceEntityById(Long id);
}
