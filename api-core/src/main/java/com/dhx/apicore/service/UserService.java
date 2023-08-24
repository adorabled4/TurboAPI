package com.dhx.apicore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.model.vo.UserVo;

import java.util.List;

/**
* @author dhx
* @description 针对表【t_user】的数据库操作Service
* @createDate 2023-08-24 12:31:33
*/
public interface UserService extends IService<UserEntity> {
    Boolean deleteUserById(Long userId);

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

    boolean deleteUserByAccount(String userAccount);
}
