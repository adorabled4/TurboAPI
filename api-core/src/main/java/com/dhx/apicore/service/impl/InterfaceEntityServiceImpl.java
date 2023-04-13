package com.dhx.apicore.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.constants.InterfaceConstant;
import com.dhx.apicore.mapper.InterfaceExampleEntityMapper;
import com.dhx.apicore.model.DO.InterfaceEntity;
import com.dhx.apicore.model.DO.InterfaceExampleEntity;
import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.model.vo.InterfaceBasicInfoVo;
import com.dhx.apicore.model.vo.InterfaceDetailVo;
import com.dhx.apicore.service.InterfaceEntityService;
import com.dhx.apicore.mapper.InterfaceEntityMapper;
import com.dhx.apicore.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dhx
 * @description 针对表【t_interface_entity】的数据库操作Service实现
 * @createDate 2023-04-12 09:38:35
 */
@Service
public class InterfaceEntityServiceImpl extends ServiceImpl<InterfaceEntityMapper, InterfaceEntity>
        implements InterfaceEntityService {

    @Resource
    UserService userService;

    @Resource
    InterfaceExampleEntityMapper interfaceExampleEntityMapper;

    @Override
    public List<InterfaceBasicInfoVo> getInterfaceList(int pageSize, int current) {
        Page<InterfaceEntity> page = query().ne("status", 0).page(new Page<InterfaceEntity>(current, pageSize));
        List<InterfaceBasicInfoVo> voList = page.getRecords().stream()
                .filter(item -> item.getStatus() != InterfaceConstant.CLOSED_STATUS)
                .map(item -> {
                    InterfaceBasicInfoVo interfaceBasicInfoVo = BeanUtil.copyProperties(item, InterfaceBasicInfoVo.class);
                    Long userId = item.getUserId(); // 创建者的id
                    UserEntity user = userService.getById(userId);
                    if(user!=null){
                        interfaceBasicInfoVo.setUserName(user.getUserName());
                    }else{
                        interfaceBasicInfoVo.setUserName("adorabled4");
                    }
                    return interfaceBasicInfoVo;
                }).collect(Collectors.toList());
        return voList;
    }


    @Override
    public BaseResponse getInterfaceDetail(Long id) {
        InterfaceEntity interfaceEntity = getById(id);
        InterfaceDetailVo interfaceBasicInfoVo = BeanUtil.copyProperties(interfaceEntity, InterfaceDetailVo.class);
        // 设置创建者相关的信息
        Long userId = interfaceEntity.getUserId(); // 创建者的id
        UserEntity user = userService.getById(userId);
        if(user!=null){
            interfaceBasicInfoVo.setUserName(user.getUserName());
        }else{
            interfaceBasicInfoVo.setUserName("adorabled4");
        }
        return ResultUtil.success(interfaceBasicInfoVo);
    }

    @Override
    public BaseResponse getInterfaceExample(Long id) {
        InterfaceExampleEntity interfaceExampleEntity = interfaceExampleEntityMapper.selectById(id);
        if(interfaceExampleEntity==null){
            return ResultUtil.error(ErrorCode.NULL_ERROR,"该接口暂无示例");
        }
        return null;
    }

}




