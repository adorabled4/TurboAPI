package com.ruoyi.api.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.api.admin.mapper.UserInterfaceInfoEntityMapper;
import com.ruoyi.api.admin.domain.UserInterfaceInfoEntity;
import com.ruoyi.api.admin.service.IUserInterfaceInfoEntityService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author adorabled4
 * @date 2023-03-09
 */
@Service
public class UserInterfaceInfoEntityServiceImpl implements IUserInterfaceInfoEntityService 
{
    @Autowired
    private UserInterfaceInfoEntityMapper userInterfaceInfoEntityMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public UserInterfaceInfoEntity selectUserInterfaceInfoEntityById(Long id)
    {
        return userInterfaceInfoEntityMapper.selectUserInterfaceInfoEntityById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param userInterfaceInfoEntity 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<UserInterfaceInfoEntity> selectUserInterfaceInfoEntityList(UserInterfaceInfoEntity userInterfaceInfoEntity)
    {
        return userInterfaceInfoEntityMapper.selectUserInterfaceInfoEntityList(userInterfaceInfoEntity);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param userInterfaceInfoEntity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertUserInterfaceInfoEntity(UserInterfaceInfoEntity userInterfaceInfoEntity)
    {
        userInterfaceInfoEntity.setCreateTime(DateUtils.getNowDate());
        return userInterfaceInfoEntityMapper.insertUserInterfaceInfoEntity(userInterfaceInfoEntity);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param userInterfaceInfoEntity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUserInterfaceInfoEntity(UserInterfaceInfoEntity userInterfaceInfoEntity)
    {
        userInterfaceInfoEntity.setUpdateTime(DateUtils.getNowDate());
        return userInterfaceInfoEntityMapper.updateUserInterfaceInfoEntity(userInterfaceInfoEntity);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserInterfaceInfoEntityByIds(Long[] ids)
    {
        return userInterfaceInfoEntityMapper.deleteUserInterfaceInfoEntityByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserInterfaceInfoEntityById(Long id)
    {
        return userInterfaceInfoEntityMapper.deleteUserInterfaceInfoEntityById(id);
    }
}
