package com.dhx.apicore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.model.vo.UserVo;

import java.util.List;

/**
* @author dhx
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2023-04-10 15:56:46
*/
public interface UserService extends IService<UserEntity> {

    BaseResponse<Boolean> deleteUserById(Long userId);

    BaseResponse<List<UserVo>> getUserList(int pageSize, int current);

    BaseResponse<UserVo> getUserById(Long userId);

    BaseResponse addUser(UserVo userVo);

    /**
     * 用户注册
     * @param userAccount 用户账户名
     * @param password
     * @param checkPassword
     * @return
     */
    BaseResponse register(String userAccount, String password, String checkPassword);

    /**
     * 用户登录
     * @param userAccount 账户
     * @param password 密码
     * @return 返回token
     */
    BaseResponse login(String userAccount, String password);
}
