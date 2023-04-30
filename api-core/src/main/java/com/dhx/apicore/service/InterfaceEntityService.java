package com.dhx.apicore.service;

import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicore.model.DO.InterfaceEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apicore.model.DO.InterfaceExampleEntity;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVo;
import com.dhx.apicore.model.vo.InterfaceDetailVo;
import com.dhx.apicore.model.vo.InterfaceRankInfoVo;

import java.util.List;

/**
* @author dhx
* @description 针对表【t_interface_entity】的数据库操作Service
* @createDate 2023-04-12 09:38:35
*/
public interface InterfaceEntityService extends IService<InterfaceEntity> {

    /**
     * 获取接口列表(首页)
     * @param pageSize
     * @param current
     * @return
     */
    List<InterfaceBasicInfoVo> getInterfaceList(int pageSize, int current);

    /**
     * 获取接口详细信息
     * @param id
     * @return
     */
    BaseResponse<InterfaceDetailVo> getInterfaceDetail(Long id);


    /**
     * 获取接口调用示例
     * @param id
     * @return
     */
    BaseResponse<InterfaceExampleEntity> getInterfaceExample(Long id);


    /**
     * 是否是可用的接口id ( 接口状态 )
     * @param id
     * @return
     */
    boolean isValidInterfaceId(long id);

    /**
     *  添加接口在排行榜中的分数
     * @param id
     */
    void addRankScore(Long id);


    /**
     * 获取top 接口
     * @return
     */
    BaseResponse<List<InterfaceRankInfoVo>> getRank5Interface();
}
