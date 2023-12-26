package com.dhx.apicore.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.common.constant.InterfaceConstant;
import com.dhx.apicore.common.constant.RedisConstant;
import com.dhx.apicore.mapper.InterfaceInfoEntityMapper;
import com.dhx.apicore.model.DO.InterfaceInfoEntity;
import com.dhx.apicore.model.DO.InterfaceInfoEntity;
import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVo;
import com.dhx.apicore.model.vo.InterfaceDetailVo;
import com.dhx.apicore.model.vo.InterfaceRankInfoVo;
import com.dhx.apicore.model.vo.InterfaceTagVo;
import com.dhx.apicore.service.InterfaceInfoService;
import com.dhx.apicore.service.InterfaceVariableInfoService;
import com.dhx.apicore.service.UserService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    UserService userService;

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    InterfaceVariableInfoService interfaceVariableInfoService;

    @Override
    public List<InterfaceBasicInfoVo> getInterfaceList(PageQuery pageQuery) {
        Page<InterfaceInfoEntity> page = query().ne("status", 0).page(new Page<InterfaceInfoEntity>(pageQuery.getCurrentPage(),
                pageQuery.getCurrentPage()));
        List<InterfaceBasicInfoVo> voList = page.getRecords().stream()
                .filter(item -> item.getStatus() != InterfaceConstant.CLOSED_STATUS)
                .map(item -> {
                    InterfaceBasicInfoVo interfaceBasicInfoVo = BeanUtil.copyProperties(item, InterfaceBasicInfoVo.class);
//                    Integer userId = item.getUserId(); // 创建者的id
//                    UserEntity user = userService.getById(userId);
//                    if(user!=null){
//                        interfaceBasicInfoVo.setUserName(user.getUserName());
//                    }else{
                    interfaceBasicInfoVo.setUserName("adorabled4");
//                    }
                    return interfaceBasicInfoVo;
                }).collect(Collectors.toList());
        return voList;
    }


    @Override
    public BaseResponse<InterfaceDetailVo> getInterfaceDetail(Long id) {
        InterfaceInfoEntity interfaceEntity = getById(id);
        InterfaceDetailVo interfaceBasicInfoVo = BeanUtil.copyProperties(interfaceEntity, InterfaceDetailVo.class);
        // 设置创建者相关的信息
//        Integer userId = interfaceEntity.getUserId(); // 创建者的id
//        UserEntity user = userService.getById(userId);
//        if(user!=null){
//            interfaceBasicInfoVo.setUserName(user.getUserName());
//        }else{
        interfaceBasicInfoVo.setUserName("adorabled4");
//        }
        return ResultUtil.success(interfaceBasicInfoVo);
    }

    @Override
    public boolean isValidInterfaceId(long id) {
        if (id < 0) {
            return false;
        }
        InterfaceInfoEntity byId = getById(id);
        if (byId == null || byId.getStatus() == InterfaceConstant.CLOSED_STATUS) {
            return false;
        }
        return true;
    }


    @Override
    public void addRankScore(Long interfaceId) {
        System.out.println("addRankScore:" + interfaceId);
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
            List<InterfaceRankInfoVo> collect = interfaceEntities.stream().map(item -> {
                InterfaceRankInfoVo interfaceRankInfoVo = BeanUtil.copyProperties(item, InterfaceRankInfoVo.class);
                return interfaceRankInfoVo;
            }).collect(Collectors.toList());
            return ResultUtil.success(collect);
        } else {
            // 从数据库中查询
//            QueryWrapper<InterfaceInfoEntity> wrapper = new QueryWrapper<>();
//            wrapper.ne("status",0).orderByAsc("call_times");
            List<InterfaceInfoEntity> interfaceEntities = query().gt("status", 0).orderByDesc("call_times").page(new Page<>(1, 5)).getRecords();
            List<InterfaceRankInfoVo> collect = interfaceEntities.stream().map(item -> {
                InterfaceRankInfoVo interfaceRankInfoVo = BeanUtil.copyProperties(item, InterfaceRankInfoVo.class);
                return interfaceRankInfoVo;
            }).collect(Collectors.toList());
            return ResultUtil.success(collect);
        }
    }


//    @Override
//    public BaseResponse<List<InterfaceTagVo>> getInterfaceByTag() {
//        List<String> tags = this.baseMapper.getTags();
//        List<InterfaceTagVo> result = new ArrayList<>();
//        List<InterfaceTagVo> tagVos = tags.stream().map(item -> {
//            List<InterfaceInfoEntity> interfaceEntities = query().eq("tag", item).list();
//            // 封装vo
//            List<InterfaceBasicInfoVo> basicInfoVos = interfaceEntities.stream().map(interfaceEntity -> {
//                InterfaceBasicInfoVo interfaceBasicInfoVo = BeanUtil.copyProperties(interfaceEntity, InterfaceBasicInfoVo.class);
//                setUserName(interfaceEntity, interfaceBasicInfoVo);
//                return interfaceBasicInfoVo;
//            }).collect(Collectors.toList());
//            // 封装tagVo
//            InterfaceTagVo interfaceTagVo = new InterfaceTagVo();
//            interfaceTagVo.setTag(item);
//            interfaceTagVo.setInterfaceBasicInfoVos(basicInfoVos);
//            return interfaceTagVo;
//        }).collect(Collectors.toList());
//        return ResultUtil.success(tagVos);
//    }


    /**
     * 封装接口的创建者
     *
     * @param interfaceEntity
     * @param interfaceBasicInfoVo
     */
    private void setUserName(InterfaceInfoEntity interfaceEntity, InterfaceBasicInfoVo interfaceBasicInfoVo) {
//        if(interfaceEntity.getUserId()==null){
        interfaceBasicInfoVo.setUserName("adorabled4");
//        }else{
//            UserEntity user = userService.getById(interfaceEntity.getUserId().longValue());
//            if(user==null){
//                interfaceBasicInfoVo.setUserName("adorabled4");
//            }else{
//                String userName = user.getUserName();
//                interfaceBasicInfoVo.setUserName(userName);
//            }
//        }
    }
}




