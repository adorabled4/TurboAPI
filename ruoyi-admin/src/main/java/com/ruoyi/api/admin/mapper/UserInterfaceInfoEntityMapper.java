package com.ruoyi.api.admin.mapper;

import java.util.List;
import com.ruoyi.api.admin.domain.UserInterfaceInfoEntity;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author adorabled4
 * @date 2023-03-09
 */
public interface UserInterfaceInfoEntityMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public UserInterfaceInfoEntity selectUserInterfaceInfoEntityById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param userInterfaceInfoEntity 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<UserInterfaceInfoEntity> selectUserInterfaceInfoEntityList(UserInterfaceInfoEntity userInterfaceInfoEntity);

    /**
     * 新增【请填写功能名称】
     * 
     * @param userInterfaceInfoEntity 【请填写功能名称】
     * @return 结果
     */
    public int insertUserInterfaceInfoEntity(UserInterfaceInfoEntity userInterfaceInfoEntity);

    /**
     * 修改【请填写功能名称】
     * 
     * @param userInterfaceInfoEntity 【请填写功能名称】
     * @return 结果
     */
    public int updateUserInterfaceInfoEntity(UserInterfaceInfoEntity userInterfaceInfoEntity);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteUserInterfaceInfoEntityById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserInterfaceInfoEntityByIds(Long[] ids);
}
