package com.ruoyi.api.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.api.admin.service.IUserEntityService;
import com.ruoyi.api.admin.vo.InterfaceInfoVo;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import com.ruoyi.api.admin.mapper.InterfaceEntityMapper;
import com.ruoyi.api.admin.domain.InterfaceEntity;
import com.ruoyi.api.admin.service.IInterfaceEntityService;

import javax.annotation.Resource;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author adorabled4
 * @date 2023-03-10
 */
@Service
public class InterfaceEntityServiceImpl implements IInterfaceEntityService 
{
    @Autowired
    private InterfaceEntityMapper interfaceEntityMapper;

    @Resource
    private IUserEntityService userService;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public InterfaceEntity selectInterfaceEntityById(Long id)
    {
        return interfaceEntityMapper.selectInterfaceEntityById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param interfaceEntity 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<InterfaceInfoVo> selectInterfaceEntityList(InterfaceEntity interfaceEntity)
    {
        List<InterfaceEntity> interfaceEntities = interfaceEntityMapper.selectInterfaceEntityList(interfaceEntity);
        List<InterfaceInfoVo> interfaceInfoVos = interfaceEntities.stream().map(item -> {
            InterfaceInfoVo interfaceInfoVo = BeanUtil.copyProperties(item, InterfaceInfoVo.class);
            String userName = userService.getUserNameById(interfaceInfoVo.getUserId());
            interfaceInfoVo.setUserName(userName);
            return interfaceInfoVo;
        }).collect(Collectors.toList());
        return interfaceInfoVos;
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param interfaceEntity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertInterfaceEntity(InterfaceEntity interfaceEntity)
    {

        interfaceEntity.setCreateTime(DateUtils.getNowDate());
        return interfaceEntityMapper.insertInterfaceEntity(interfaceEntity);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param interfaceEntity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateInterfaceEntity(InterfaceEntity interfaceEntity)
    {
        interfaceEntity.setUpdateTime(DateUtils.getNowDate());
        return interfaceEntityMapper.updateInterfaceEntity(interfaceEntity);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteInterfaceEntityByIds(Long[] ids)
    {
        return interfaceEntityMapper.deleteInterfaceEntityByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteInterfaceEntityById(Long id)
    {
        return interfaceEntityMapper.deleteInterfaceEntityById(id);
    }
}
