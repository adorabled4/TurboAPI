package com.dhx.apicore.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicore.model.DTO.JwtToken;
import com.dhx.apicore.service.JwtTokensService;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.mapper.UserEntityMapper;
import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.model.vo.UserVo;
import com.dhx.apicore.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
* @author dhx
* @description 针对表【user(用户)】的数据库操作Service实现
* @createDate 2023-04-10 14:18:26
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserEntityMapper, UserEntity>
    implements UserService {

    @Resource
    UserEntityMapper userMapper;

    @Resource
    JwtTokensService jwtTokensService;


    @Override
    public BaseResponse login(String userAccount, String password) {
        //1. 获取的加密密码
        UserEntity user = query().eq("user_account",userAccount).one();
        String handlerPassword=user.getPassword();

        //2. 查询用户密码是否正确
        boolean checkpw = BCrypt.checkpw(password, handlerPassword);
        if(!checkpw){
            return ResultUtil.error(ErrorCode.PARAMS_ERROR,"账户名或密码错误!");
        }
        //3. 获取jwt的token并将token写入Redis
        String token = jwtTokensService.generateAccessToken(user);
        String refreshToken = jwtTokensService.generateRefreshToken(user);
        JwtToken jwtToken = new JwtToken(token,refreshToken);
        jwtTokensService.save2Redis(jwtToken,user);
        // 返回jwtToken
        return ResultUtil.success(token);
    }

    @Override
    public BaseResponse register(String userAccount, String password, String checkPassword) {
        if(!password.equals(checkPassword)){
            return ResultUtil.error(ErrorCode.PARAMS_ERROR,"两次输入的密码不一致!");
        }
        Long cnt = query().eq("user_account", userAccount).count();
        if(cnt!=0){
            return ResultUtil.error(ErrorCode.PARAMS_ERROR,"用户名已被注册!");
        }
        UserEntity user = new UserEntity();
        String handlerPassword = BCrypt.hashpw(password);
        user.setUserAccount(userAccount);
        user.setUserName("user-"+ UUID.randomUUID().toString().substring(0,10));
        user.setPassword(handlerPassword);
        boolean save = save(user);
        return ResultUtil.success(user.getUserId());
    }

    @Override
    public BaseResponse<UserVo> getUserById(Long userId) {
        UserEntity user = userMapper.selectById(userId);
        System.out.println(user);
        if(user==null){
            return ResultUtil.error(ErrorCode.PARAMS_ERROR,"用户不存在!");
        }
        // 转换成vo 对象
        UserVo userVo = BeanUtil.copyProperties(user, UserVo.class);
        return ResultUtil.success(userVo);
    }

    @Override
    public BaseResponse<Boolean> deleteUserById(Long userId) {
        boolean result = remove(new QueryWrapper<UserEntity>().eq("user_id", userId));
        return ResultUtil.success(result);
    }

    @Override
    public BaseResponse<List<UserVo>> getUserList(int pageSize, int current) {
        // 分页查询数据
        List<UserEntity> records = query().page(new Page<>(current,pageSize)).getRecords();
        // 转换为userVO
        List<UserVo> userVoList = records.stream().map(item -> BeanUtil.copyProperties(item, UserVo.class)).collect(Collectors.toList());
        return ResultUtil.success(userVoList);
    }


    @Override
    public BaseResponse addUser(UserVo userVo) {
        UserEntity user = BeanUtil.copyProperties(userVo, UserEntity.class);
        save(user);
        return ResultUtil.success();
    }
}





