package com.ruoyi.api.admin.mapper;

import java.util.List;
import com.ruoyi.api.admin.domain.WhiteList;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author adorabled4
 * @date 2023-03-09
 */
public interface WhiteListMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public WhiteList selectWhiteListById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param whiteList 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<WhiteList> selectWhiteListList(WhiteList whiteList);

    /**
     * 新增【请填写功能名称】
     * 
     * @param whiteList 【请填写功能名称】
     * @return 结果
     */
    public int insertWhiteList(WhiteList whiteList);

    /**
     * 修改【请填写功能名称】
     * 
     * @param whiteList 【请填写功能名称】
     * @return 结果
     */
    public int updateWhiteList(WhiteList whiteList);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteWhiteListById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteWhiteListByIds(Long[] ids);
}
