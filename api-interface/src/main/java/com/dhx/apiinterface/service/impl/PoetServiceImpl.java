package com.dhx.apiinterface.service.impl;

import java.util.List;

import cn.hutool.core.bean.BeanUtil;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.vo.PoetVO;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dhx.apiinterface.mapper.PoetMapper;
import com.dhx.apiinterface.domain.Poet;
import com.dhx.apiinterface.service.IPoetService;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author adorabled4
 * @date 2023-03-11
 */
@Service
public class PoetServiceImpl implements IPoetService 
{
    @Autowired
    private PoetMapper poetMapper;


    @Override
    public long getTotal() {
        return poetMapper.getTotal();
    }

    @Override
    public BaseResponse<PoetVO> getPoetVO(long id) {
        Poet poet = poetMapper.selectPoetById(id);
        while(poet==null){
            poet = poetMapper.selectPoetById(1L);
        }
        PoetVO poetVO = BeanUtil.copyProperties(poet, PoetVO.class);
        return ResultUtil.success(poetVO);
    }

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public Poet selectPoetById(Long id)
    {
        return poetMapper.selectPoetById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param poet 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<Poet> selectPoetList(Poet poet)
    {
        return poetMapper.selectPoetList(poet);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param poet 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertPoet(Poet poet)
    {
        poet.setCreateTime(DateUtils.getNowDate());
        return poetMapper.insertPoet(poet);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param poet 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updatePoet(Poet poet)
    {
        return poetMapper.updatePoet(poet);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePoetByIds(Long[] ids)
    {
        return poetMapper.deletePoetByIds(ids);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deletePoetById(Long id)
    {
        return poetMapper.deletePoetById(id);
    }
}
