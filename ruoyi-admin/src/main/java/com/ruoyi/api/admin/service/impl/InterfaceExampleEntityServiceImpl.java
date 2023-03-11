package com.ruoyi.api.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.api.admin.mapper.InterfaceExampleEntityMapper;
import com.ruoyi.api.admin.domain.InterfaceExampleEntity;
import com.ruoyi.api.admin.service.IInterfaceExampleEntityService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author adorabled4
 * @date 2023-03-09
 */
@Service
public class InterfaceExampleEntityServiceImpl implements IInterfaceExampleEntityService 
{
    @Autowired
    private InterfaceExampleEntityMapper interfaceExampleEntityMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public InterfaceExampleEntity selectInterfaceExampleEntityById(Long id)
    {
        return interfaceExampleEntityMapper.selectInterfaceExampleEntityById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param interfaceExampleEntity 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<InterfaceExampleEntity> selectInterfaceExampleEntityList(InterfaceExampleEntity interfaceExampleEntity)
    {
        return interfaceExampleEntityMapper.selectInterfaceExampleEntityList(interfaceExampleEntity);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param interfaceExampleEntity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertInterfaceExampleEntity(InterfaceExampleEntity interfaceExampleEntity)
    {
        interfaceExampleEntity.setCreateTime(DateUtils.getNowDate());
        return interfaceExampleEntityMapper.insertInterfaceExampleEntity(interfaceExampleEntity);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param interfaceExampleEntity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateInterfaceExampleEntity(InterfaceExampleEntity interfaceExampleEntity)
    {
        interfaceExampleEntity.setUpdateTime(DateUtils.getNowDate());
        return interfaceExampleEntityMapper.updateInterfaceExampleEntity(interfaceExampleEntity);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteInterfaceExampleEntityByIds(Long[] ids)
    {
        return interfaceExampleEntityMapper.deleteInterfaceExampleEntityByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteInterfaceExampleEntityById(Long id)
    {
        return interfaceExampleEntityMapper.deleteInterfaceExampleEntityById(id);
    }
}
