package com.dhx.apicore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicore.model.DO.InterfaceInfoEntity;
import com.dhx.apicore.model.query.InterfacePubQuery;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVo;
import com.dhx.apicore.model.vo.InterfaceDetailVo;
import com.dhx.apicore.model.vo.InterfaceRankInfoVo;

import java.util.List;

/**
 * @author dhx
 * @description 针对表【t_interface_entity】的数据库操作Service
 * @createDate 2023-04-12 09:38:35
 */
public interface InterfaceInfoService extends IService<InterfaceInfoEntity> {

    /**
     * 获取接口列表
     *
     * @param pageQuery 页面查询
     * @return {@link List}<{@link InterfaceBasicInfoVo}>
     */
    List<InterfaceBasicInfoVo> getInterfaceList(PageQuery pageQuery);

    /**
     * 获取接口详细信息
     *
     * @param id
     * @return
     */
    BaseResponse<InterfaceDetailVo> getInterfaceDetail(Long id);

    /**
     * 是否是可用的接口id ( 接口状态 )
     *
     * @param id
     * @return
     */
    boolean isValidInterfaceId(long id);

    /**
     * 添加接口在排行榜中的分数
     *
     * @param id
     */
    void addRankScore(Long id);


    /**
     * 获取top 接口
     *
     * @return
     */
    BaseResponse<List<InterfaceRankInfoVo>> getRank5Interface();

    /**
     * 发布接口
     *
     * @param query 查询
     * @return boolean
     */
    void publishInterface(InterfacePubQuery query);

}
