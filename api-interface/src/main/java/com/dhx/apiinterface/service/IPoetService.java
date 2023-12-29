package com.dhx.apiinterface.service;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.model.v1.PoetVO;
import com.dhx.apiinterface.domain.Poet;

import java.util.List;

/**
 * 诗句Service接口
 * 
 * @author adorabled4
 * @date 2023-03-11
 */
public interface IPoetService 
{
    /**
     * 查询诗句
     * 
     * @param id 诗句主键
     * @return 诗句
     */
    public Poet selectPoetById(Long id);

    /**
     * 查询诗句列表
     * 
     * @param poet 诗句
     * @return 诗句集合
     */
    public List<Poet> selectPoetList(Poet poet);

    /**
     * 新增诗句
     * 
     * @param poet 诗句
     * @return 结果
     */
    public int insertPoet(Poet poet);

    /**
     * 修改诗句
     * 
     * @param poet 诗句
     * @return 结果
     */
    public int updatePoet(Poet poet);

    /**
     * 批量删除诗句
     * 
     * @param ids 需要删除的诗句主键集合
     * @return 结果
     */
    public int deletePoetByIds(Long[] ids);

    /**
     * 删除诗句信息
     * 
     * @param id 诗句主键
     * @return 结果
     */
    public int deletePoetById(Long id);

    BaseResponse<PoetVO> getPoetVO(long id);

    /**
     * 获取随机诗句
     * @return
     */
    BaseResponse<PoetVO> getRandomPoetVo();


    long getTotal();
}
