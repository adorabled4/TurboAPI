package com.dhx.apicore.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.model.query.PageQuery;
import com.dhx.apicore.model.query.UserUpdateQuery;
import com.dhx.apicore.model.vo.UserVo;
import org.springframework.web.multipart.MultipartFile;

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
    

    Boolean deleteUserByAccount(String userAccount);

    UserVo getCurrentUser();

    UserEntity getUserByAccount(String userAccount);

    UserEntity findUserByAccount(String userAccount);

    UserEntity findById(Long userId);

    void updateUserInfO(MultipartFile multipartFile, UserUpdateQuery param);

    void updateUserPwd(String password);

    void reduceCoin(Long userId, Integer cost);
}
