package com.dhx.apicore.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.mapper.UserEntityMapper;
import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.model.DTO.UserDTO;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.vo.UserVo;
import com.dhx.apicore.service.UserService;
import com.dhx.apicore.util.ThrowUtil;
import com.dhx.apicore.util.UserHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dhx
 * @description 针对表【t_user】的数据库操作Service实现
 * @createDate 2023-08-24 12:31:33
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserEntityMapper, UserEntity> implements UserService {

    @Resource
    UserEntityMapper userMapper;

    @Override
    public BaseResponse<UserVo> getUserById(Long userId) {
        UserEntity user = userMapper.selectById(userId);
        System.out.println(user);
        if (user == null) {
            return ResultUtil.error(ErrorCode.PARAMS_ERROR, "用户不存在!");
        }
        // 转换成vo 对象
        UserVo userVo = BeanUtil.copyProperties(user, UserVo.class);
        return ResultUtil.success(userVo);
    }

    @Override
    public Boolean deleteUserById(Long userId) {
        return remove(new QueryWrapper<UserEntity>().eq("user_id", userId));
    }

    @Override
    public BaseResponse<List<UserVo>> getUserList(PageQuery pageQuery) {
        // 分页查询数据
        List<UserEntity> records = query().page(new Page<>(pageQuery.getCurrentPage(), pageQuery.getPageSize())).getRecords();
        // 转换为userVO
        List<UserVo> userVoList = records.stream().map(item -> BeanUtil.copyProperties(item, UserVo.class)).collect(Collectors.toList());
        return ResultUtil.success(userVoList);
    }


    @Override
    public BaseResponse<Long> addUser(UserVo userVo) {
        UserEntity user = BeanUtil.copyProperties(userVo, UserEntity.class);
        save(user);
        return ResultUtil.success(user.getUserId());
    }

    @Override
    public Boolean deleteUserByAccount(String userAccount) {
        return remove(new QueryWrapper<UserEntity>().eq("userAccount", userAccount));
    }

    @Override
    public UserVo getCurrentUser() {
        UserDTO user = UserHolder.getUser();
        UserEntity userEntity = getById(user.getUserId());
        return BeanUtil.copyProperties(userEntity, UserVo.class);
    }

    @Override
    public UserEntity getUserByAccount(String userAccount) {
        return query().eq("user_account", userAccount).one();
    }

    @Override
    public UserEntity findUserByAccount(String userAccount) {
        UserEntity userEntity = query().eq("user_account", userAccount).one();
        ThrowUtil.throwIf(userEntity == null, ErrorCode.PARAMS_ERROR, "用户不存在!");
        return userEntity;
    }
}





