package com.dhx.apicore.service.impl;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.common.constant.RedisConstant;
import com.dhx.apicore.common.constant.UserConstant;
import com.dhx.apicore.manager.EmailManager;
import com.dhx.apicore.model.DO.UserEntity;
import com.dhx.apicore.model.DTO.JwtToken;
import com.dhx.apicore.model.query.LoginQuery;
import com.dhx.apicore.model.query.QuickLoginEmailRequest;
import com.dhx.apicore.model.query.RegisterQuery;
import com.dhx.apicore.service.JwtTokensService;
import com.dhx.apicore.service.LoginService;
import com.dhx.apicore.service.UserService;
import com.dhx.apicore.util.ThrowUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author adorabled4
 * @className LoginServiceImpl
 * @date : 2023/12/27/ 19:39
 **/
public class LoginServiceImpl implements LoginService {

    @Resource
    StringRedisTemplate stringRedisTemplate;

    @Resource
    UserService userService;

    @Resource
    JwtTokensService jwtTokensService;

    @Resource
    EmailManager emailManager;

    @Override
    public BaseResponse<String> quickLoginByEmail(QuickLoginEmailRequest param) {
        verifyCode(param.getCode(), param.getEmail());
        //1. 获取的加密密码
        List<UserEntity> users = userService.list(new QueryWrapper<UserEntity>().eq("email", param.getEmail()));
        UserEntity user;
        if (users == null || users.size() == 0) {
            user = quickRegister(param.getEmail());
        } else {
            user = users.get(0);
        }
        //3. 获取jwt的token并将token写入Redis
        String token = jwtTokensService.generateAccessToken(user);
        String refreshToken = jwtTokensService.generateRefreshToken(user);
        JwtToken jwtToken = new JwtToken(token, refreshToken);
        jwtTokensService.save2Redis(jwtToken, user);
        return ResultUtil.success(token);
    }


    @Override
    public void sendVerifyCode(String email, HttpServletRequest request) {
        // 校验
        ThrowUtil.throwIf(!email.matches(UserConstant.EMAIL_REGEX), ErrorCode.PARAMS_ERROR, "邮箱格式非法!");
        Long cnt = userService.query().eq("email", email).count();
        // 发送验证码
        String code = RandomUtil.randomNumbers(6);
        String codeKey = RedisConstant.VERIFY_CODE_KEY + email;
        verifyCodeSend(email);
        // 发送
        emailManager.sendHtmlVerifyCode(email, code, request);
        // 保存验证码的有效期
        long now = new Date().getTime() / 1000;
        String codeVal = now + "-" + code;
        // 存储
        stringRedisTemplate.opsForValue().set(codeKey, codeVal, RedisConstant.VERIFY_CODE_TTL, TimeUnit.SECONDS);
    }

    @Override
    public BaseResponse<String> login(LoginQuery param) {
        //1. 获取的加密密码
        UserEntity user = userService.findUserByAccount(param.getUserAccount());
        String handlerPassword = user.getPassword();
        //2. 查询用户密码是否正确
        boolean checkpw = BCrypt.checkpw(param.getPassword(), handlerPassword);
        ThrowUtil.throwIf(!checkpw, ErrorCode.PARAMS_ERROR, "账户名或密码错误!");
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
        ThrowUtil.throwIf(!password.equals(checkPassword), ErrorCode.PARAMS_ERROR, "两次输入的密码不一致!");
        UserEntity user = userService.getUserByAccount(userAccount);
        ThrowUtil.throwIf(user != null, ErrorCode.PARAMS_ERROR, "账户已被注册!");
        user = new UserEntity();
        String handlerPassword = BCrypt.hashpw(password);
        user.setUserAccount(userAccount);
        user.setUserName("user-" + UUID.randomUUID().toString().substring(0, 10));
        user.setPassword(handlerPassword);
        boolean save = userService.save(user);
        return ResultUtil.success(user.getUserId());
    }

    /**
     * 验证是否可以允许发送验证码
     *
     * @param email 电子邮件
     * @return boolean
     */
    void verifyCodeSend(String email) {
        String codeKey = RedisConstant.VERIFY_CODE_KEY + email;
        String oldCode = stringRedisTemplate.opsForValue().get(codeKey);
        // 判断是否之前发送过
        if (StringUtils.isNotBlank(oldCode)) {
            String[] split = oldCode.split("-");
            long time = Long.parseLong(split[0]);
            // 如果两次发送的间隔小于 60s => reject
            ThrowUtil.throwIf(new Date().getTime() / 1000 - time < 60,
                    ErrorCode.FORBIDDEN_ERROR, "请稍后发送验证码!");
        }
    }

    /**
     * 快速注册
     *
     * @param email 电子邮件
     * @return {@link UserEntity}
     */
    private UserEntity quickRegister(String email) {
        // 封装信息
        UserEntity user = new UserEntity();
        // 加密用户密码
        user.setUserAccount(email);
        user.setUserName(email);
        user.setEmail(email);
        boolean save = userService.save(user);
        ThrowUtil.throwIf(!save, ErrorCode.SYSTEM_ERROR, "登录失败!");
        return user;
    }

    private void verifyCode(String code, String email) {
        String codeKey = RedisConstant.VERIFY_CODE_KEY + email;
        String realCodeValue = stringRedisTemplate.opsForValue().get(codeKey);
        if (StringUtils.isBlank(realCodeValue)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "请重新发送验证码!");
        }
        String[] split = realCodeValue.split("-");
        String verifyCode = split[1];
        if (!verifyCode.equals(code)) {
            throw new BusinessException(ErrorCode.FORBIDDEN_ERROR, "验证码错误!");
        }
    }

}
