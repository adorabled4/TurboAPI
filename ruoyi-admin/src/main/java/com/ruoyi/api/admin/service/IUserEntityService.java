package com.ruoyi.api.admin.service;

import java.util.List;
import com.ruoyi.api.admin.domain.UserEntity;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author adorabled4
 * @date 2023-03-09
 */
public interface IUserEntityService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param userId 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public UserEntity selectUserEntityByUserId(Long userId);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param userEntity 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserEntity> selectUserEntityList(UserEntity userEntity);

    /**
     * 新增【请填写功能名称】
     * 
     * @param userEntity 【请填写功能名称】
     * @return 结果
     */
    public int insertUserEntity(UserEntity userEntity);

    /**
     * 修改【请填写功能名称】
     * 
     * @param userEntity 【请填写功能名称】
     * @return 结果
     */
    public int updateUserEntity(UserEntity userEntity);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param userIds 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteUserEntityByUserIds(Long[] userIds);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param userId 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserEntityByUserId(Long userId);
}
