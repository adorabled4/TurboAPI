package com.ruoyi.api.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.api.admin.mapper.UserEntityMapper;
import com.ruoyi.api.admin.domain.UserEntity;
import com.ruoyi.api.admin.service.IUserEntityService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author adorabled4
 * @date 2023-03-09
 */
@Service
public class UserEntityServiceImpl implements IUserEntityService 
{
    @Autowired
    private UserEntityMapper userEntityMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public UserEntity selectUserEntityByUserId(Long userId)
    {
        return userEntityMapper.selectUserEntityByUserId(userId);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param userEntity 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<UserEntity> selectUserEntityList(UserEntity userEntity)
    {
        return userEntityMapper.selectUserEntityList(userEntity);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param userEntity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertUserEntity(UserEntity userEntity)
    {
        userEntity.setCreateTime(DateUtils.getNowDate());
        return userEntityMapper.insertUserEntity(userEntity);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param userEntity 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateUserEntity(UserEntity userEntity)
    {
        userEntity.setUpdateTime(DateUtils.getNowDate());
        return userEntityMapper.updateUserEntity(userEntity);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserEntityByUserIds(Long[] userIds)
    {
        return userEntityMapper.deleteUserEntityByUserIds(userIds);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteUserEntityByUserId(Long userId)
    {
        return userEntityMapper.deleteUserEntityByUserId(userId);
    }
}
