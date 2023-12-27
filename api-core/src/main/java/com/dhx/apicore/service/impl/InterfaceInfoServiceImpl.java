package com.dhx.apicore.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.model.query.AddInterfaceInfoQuery;
import com.dhx.apicommon.service.api.ApiInterfaceService;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.common.constant.RedisConstant;
import com.dhx.apicore.mapper.InterfaceInfoEntityMapper;
import com.dhx.apicore.model.DO.InterfaceInfoEntity;
import com.dhx.apicore.model.DO.InterfaceVariableInfoEntity;
import com.dhx.apicore.model.enums.InterfaceCategoryEnum;
import com.dhx.apicore.model.enums.InterfaceStatusEnum;
import com.dhx.apicore.model.query.InterfaceCategoryQuery;
import com.dhx.apicore.model.query.InterfacePubQuery;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVO;
import com.dhx.apicore.model.vo.InterfaceDetailVO;
import com.dhx.apicore.model.vo.InterfaceRankInfoVo;
import com.dhx.apicore.service.InterfaceInfoService;
import com.dhx.apicore.service.InterfaceVariableInfoService;
import com.dhx.apicore.util.CategoryBitMapUtil;
import com.dhx.apicore.util.ThrowUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author dhx
 * @description 针对表【t_interface_entity】的数据库操作Service实现
 * @createDate 2023-04-12 09:38:35
 */
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoEntityMapper, InterfaceInfoEntity>
        implements InterfaceInfoService {
    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    InterfaceVariableInfoService interfaceVariableInfoService;

    @DubboReference
    ApiInterfaceService apiInterfaceService;

    @Override
    public List<InterfaceBasicInfoVO> getInterfaceList(PageQuery pageQuery) {
        Page<InterfaceInfoEntity> page = query().ne("status", InterfaceStatusEnum.BANNED).page(new Page<>(pageQuery.getCurrentPage(),
                pageQuery.getPageSize()));
        return page.getRecords().stream()
//                .filter(item -> item.getStatus() != InterfaceStatusEnum.DEVELOPING)
                .map(item -> {
                    InterfaceBasicInfoVO interfaceBasicInfoVO = BeanUtil.copyProperties(item, InterfaceBasicInfoVO.class);
                    // 设置分类信息
                    Long categoryBitMap = item.getCategoryBitMap();
                    List<InterfaceCategoryEnum> interfaceCategoryEnums = CategoryBitMapUtil.parseCategoryValue(categoryBitMap);
                    interfaceBasicInfoVO.setCategories(interfaceCategoryEnums);
                    return interfaceBasicInfoVO;
                }).collect(Collectors.toList());
    }


    @Override
    public BaseResponse<InterfaceDetailVO> getInterfaceDetail(Long id) {
        InterfaceInfoEntity interfaceEntity = findById(id);
        InterfaceVariableInfoEntity variableInfo = interfaceVariableInfoService.findById(id);
        InterfaceDetailVO interfaceDetailVO = BeanUtil.copyProperties(interfaceEntity, InterfaceDetailVO.class);
        BeanUtil.copyProperties(variableInfo, interfaceDetailVO);
        // 设置分类信息
        Long categoryBitMap = interfaceEntity.getCategoryBitMap();
        List<InterfaceCategoryEnum> interfaceCategoryEnums = CategoryBitMapUtil.parseCategoryValue(categoryBitMap);
        interfaceDetailVO.setCategories(interfaceCategoryEnums);
        return ResultUtil.success(interfaceDetailVO);
    }

    private InterfaceInfoEntity findById(Long id) {
        InterfaceInfoEntity interfaceInfo = getById(id);
        ThrowUtil.throwIf(interfaceInfo == null, ErrorCode.NOT_FOUND_ERROR);
        return interfaceInfo;
    }

    @Override
    public void addRankScore(Long interfaceId) {
        stringRedisTemplate.opsForZSet().incrementScore(RedisConstant.INTERFACE_RANK_KEY, String.valueOf(interfaceId), 1);
    }

    @Override
    public BaseResponse<List<InterfaceRankInfoVo>> getRank5Interface() {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringRedisTemplate.opsForZSet().reverseRangeWithScores(RedisConstant.INTERFACE_RANK_KEY, 0, 4);
        // 获取ids , 批量查询更快
        List<Long> ids = typedTuples.stream().map(item -> {
            String interfaceId = item.getValue();
            return Long.valueOf(interfaceId);
        }).collect(Collectors.toList());
        // 封装vo
        if (ids.size() >= 5) {
            List<InterfaceInfoEntity> interfaceEntities = listByIds(ids);
            List<InterfaceRankInfoVo> collect = interfaceEntities
                    .stream().map(item -> BeanUtil.copyProperties(item, InterfaceRankInfoVo.class))
                    .collect(Collectors.toList());
            return ResultUtil.success(collect);
        } else {
            // 从数据库中查询
            List<InterfaceInfoEntity> interfaceEntities = query()
                    .eq("status", InterfaceStatusEnum.AVAILABLE)
                    .orderByDesc("call_times")
                    .page(new Page<>(1, 5)).getRecords();
            List<InterfaceRankInfoVo> collect = interfaceEntities
                    .stream().map(item -> BeanUtil.copyProperties(item, InterfaceRankInfoVo.class))
                    .collect(Collectors.toList());
            return ResultUtil.success(collect);
        }
    }

    @Override
    @Transactional
    public void publishInterface(InterfacePubQuery query) {
        InterfaceVariableInfoEntity variableInfo = new InterfaceVariableInfoEntity(query);
        InterfaceInfoEntity interfaceInfo = getInterfaceEntity(query);
        boolean update = saveOrUpdate(interfaceInfo);
        boolean saveOrUpdate = interfaceVariableInfoService.saveOrUpdate(variableInfo);
        // 同步到 api-interface 的 接口meta-data中
        boolean syncResult = sync2ApiInterface(query, interfaceInfo.getId());
        ThrowUtil.throwIf(!update || !saveOrUpdate, ErrorCode.OPERATION_ERROR, "保存接口信息失败");
    }

    private boolean sync2ApiInterface(InterfacePubQuery query,Long interfaceId) {
        AddInterfaceInfoQuery addInterfaceInfoQuery = AddInterfaceInfoQuery.builder()
                .interfaceName(query.getName())
                .isAigc(query.getIsAigc())
                .interfaceId(interfaceId)
                .callPath(query.getCallPath())
                .build();
        return apiInterfaceService.addInterfaceData(addInterfaceInfoQuery);
    }

    private InterfaceInfoEntity getInterfaceEntity(InterfacePubQuery query) {
        InterfaceInfoEntity interfaceInfo = BeanUtil.copyProperties(query, InterfaceInfoEntity.class);
        List<InterfaceCategoryEnum> categories = query.getCategories();
        long combinedCategoryValue = CategoryBitMapUtil.getCombinedCategoryValue(categories);
        interfaceInfo.setCategoryBitMap(combinedCategoryValue);
        return interfaceInfo;
    }


    @Override
    public BaseResponse<List<InterfaceBasicInfoVO>> getInterfaceByCategories(InterfaceCategoryQuery query) {
        List<InterfaceCategoryEnum> categories = query.getCategories();
        long categoryBitMapValue = CategoryBitMapUtil.getCombinedCategoryValue(categories);
        List<InterfaceBasicInfoVO> interfaceBasicInfoVOS = query()
                .eq("category_bit_map", categoryBitMapValue)
                .ne("status", InterfaceStatusEnum.BANNED)
                .page(new Page<>(query.getCurrentPage(), query.getPageSize())).getRecords()
                .stream().map(item -> {
                    InterfaceBasicInfoVO interfaceBasicInfoVO = BeanUtil.copyProperties(item, InterfaceBasicInfoVO.class);
                    // 设置分类信息
                    Long categoryBitMap = item.getCategoryBitMap();
                    List<InterfaceCategoryEnum> interfaceCategoryEnums = CategoryBitMapUtil.parseCategoryValue(categoryBitMap);
                    interfaceBasicInfoVO.setCategories(interfaceCategoryEnums);
                    return interfaceBasicInfoVO;
                }).collect(Collectors.toList());
        return ResultUtil.success(interfaceBasicInfoVOS);
    }
}




