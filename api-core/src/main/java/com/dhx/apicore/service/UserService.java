package com.dhx.apicore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.model.query.LoginQuery;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.query.RegisterQuery;
import com.dhx.apicore.model.vo.UserVo;

import java.util.List;

/**
* @author dhx
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-08-24 12:31:33
*/
public interface UserService extends IService<UserEntity> {
    Boolean deleteUserById(Long userId);

    BaseResponse<List<UserVo>> getUserList(PageQuery pageQuery);

    BaseResponse<UserVo> getUserById(Long userId);

    BaseResponse<Long> addUser(UserVo userVo);

    /**
     * 用户注册
     *
     * @param param 参数
     * @return {@link BaseResponse}
     */
    BaseResponse<Long> register(RegisterQuery param);

    /**
     * 用户登录
     *
     * @param param 参数
     * @return 返回token
     */
    BaseResponse<String> login(LoginQuery param);

    Boolean deleteUserByAccount(String userAccount);

    UserVo getCurrentUser();
}
