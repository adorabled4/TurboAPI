package com.dhx.apicore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicore.model.DO.InterfaceInfoEntity;
import com.dhx.apicore.model.query.InterfaceCategoryQuery;
import com.dhx.apicore.model.query.InterfaceIdsQuery;
import com.dhx.apicore.model.query.InterfaceUpdateQuery;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVO;
import com.dhx.apicore.model.vo.InterfaceDetailVO;
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
     * @return {@link List}<{@link InterfaceBasicInfoVO}>
     */
    List<InterfaceBasicInfoVO> getInterfaceList(PageQuery pageQuery);

    /**
     * 获取接口详细信息
     *
     * @param id
     * @return
     */
    BaseResponse<InterfaceDetailVO> getInterfaceDetail(Long id);

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
    void publishInterface(InterfaceUpdateQuery query);

    /**
     * 按类别获取接口
     *
     * @param query 查询
     * @return {@link BaseResponse}<{@link List}<{@link InterfaceBasicInfoVO}>>
     */
    BaseResponse<List<InterfaceBasicInfoVO>> getInterfaceByCategories(InterfaceCategoryQuery query);

    /**
     * 通用接口文档md
     *
     * @param query 接口ids
     */
    void genInterfaceDocMD(InterfaceIdsQuery query);

    /**
     * 生成SDK代码
     *
     * @param query 接口id
     */
    void genSDKCode(InterfaceIdsQuery query);

    /**
     * 更新接口信息
     *
     * @param query query
     */
    void updateInterfaceInfo(InterfaceUpdateQuery query);
}
