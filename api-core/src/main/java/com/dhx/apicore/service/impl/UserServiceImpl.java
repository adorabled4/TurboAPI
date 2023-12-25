package com.dhx.apicore.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.mapper.UserEntityMapper;
import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.model.DTO.JwtToken;
import com.dhx.apicore.model.DTO.UserDTO;
import com.dhx.apicore.model.query.LoginQuery;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.query.RegisterQuery;
import com.dhx.apicore.model.vo.UserVo;
import com.dhx.apicore.service.JwtTokensService;
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

    @Resource
    JwtTokensService jwtTokensService;


    @Override
    public BaseResponse<String> login(LoginQuery param) {
        //1. 获取的加密密码
        UserEntity user = query().eq("user_account", param.getUserAccount()).one();
        String handlerPassword = user.getPassword();

        //2. 查询用户密码是否正确
        boolean checkpw = BCrypt.checkpw(param.getPassword(), handlerPassword);
        ThrowUtil.throwIf(!checkpw,ErrorCode.PARAMS_ERROR, "账户名或密码错误!");
        //3. 获取jwt的token并将token写入Redis
        String token = jwtTokensService.generateAccessToken(user);
        String refreshToken = jwtTokensService.generateRefreshToken(user);
        JwtToken jwtToken = new JwtToken(token, refreshToken);
        jwtTokensService.save2Redis(jwtToken, user);
        // 返回jwtToken
        return ResultUtil.success(token);
    }

    @Override
    public BaseResponse<Long> register(RegisterQuery param) {
        String password = param.getPassword();
        String checkPassword = param.getCheckPassword();
        String userAccount = param.getUserAccount();
        if (!password.equals(checkPassword)) {
            return ResultUtil.error(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致!");
        }
        Long cnt = query().eq("user_account", userAccount).count();
        if (cnt != 0) {
            return ResultUtil.error(ErrorCode.PARAMS_ERROR, "用户名已被注册!");
        }
        UserEntity user = new UserEntity();
        String handlerPassword = BCrypt.hashpw(password);
        user.setUserAccount(userAccount);
        user.setUserName("user-" + UUID.randomUUID().toString().substring(0, 10));
        user.setPassword(handlerPassword);
        boolean save = save(user);
        return ResultUtil.success(user.getUserId());
    }

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
}





