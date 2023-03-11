package com.ruoyi.api.admin.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.api.admin.mapper.WhiteListMapper;
import com.ruoyi.api.admin.domain.WhiteList;
import com.ruoyi.api.admin.service.IWhiteListService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author adorabled4
 * @date 2023-03-09
 */
@Service
public class WhiteListServiceImpl implements IWhiteListService 
{
    @Autowired
    private WhiteListMapper whiteListMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public WhiteList selectWhiteListById(Long id)
    {
        return whiteListMapper.selectWhiteListById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param whiteList 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<WhiteList> selectWhiteListList(WhiteList whiteList)
    {
        return whiteListMapper.selectWhiteListList(whiteList);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param whiteList 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertWhiteList(WhiteList whiteList)
    {
        whiteList.setCreateTime(DateUtils.getNowDate());
        return whiteListMapper.insertWhiteList(whiteList);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param whiteList 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateWhiteList(WhiteList whiteList)
    {
        whiteList.setUpdateTime(DateUtils.getNowDate());
        return whiteListMapper.updateWhiteList(whiteList);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteWhiteListByIds(Long[] ids)
    {
        return whiteListMapper.deleteWhiteListByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteWhiteListById(Long id)
    {
        return whiteListMapper.deleteWhiteListById(id);
    }
}
