package com.dhx.apicore.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apicore.manager.RedisLockManager;
import com.dhx.apicore.model.DO.DailyCheckIn;
import com.dhx.apicore.model.vo.UserVo;
import com.dhx.apicore.service.DailyCheckInService;
import com.dhx.apicore.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: QiMu
 * @Date: 2023/08/31 11:51:14
 * @Version: 1.0
 * @Description: 签到接口
 */
@RestController
@RequestMapping("/dailyCheckIn")
@Slf4j
public class DailyCheckInController {

    @Resource
    private DailyCheckInService dailyCheckInService;

    @Resource
    private UserService userService;
    @Resource
    private RedisLockManager redisLockManager;

    public static final Long LOGIN_ADD_COIN = 10L;


    /**
     * 签到
     *
     * @return {@link BaseResponse}<{@link Boolean}>
     */
    @PostMapping("/doCheckIn")
    @Transactional(rollbackFor = Exception.class)
    public BaseResponse<Boolean> doDailyCheckIn() {
        UserVo loginUser = userService.getCurrentUser();
        String redissonLock = ("doDailyCheckIn_" + loginUser.getUserAccount()).intern();
        return redisLockManager.redissonDistributedLocks(redissonLock, () -> {
            LambdaQueryWrapper<DailyCheckIn> dailyCheckInLambdaQueryWrapper = new LambdaQueryWrapper<>();
            dailyCheckInLambdaQueryWrapper.eq(DailyCheckIn::getUserId, loginUser.getUserId());
            DailyCheckIn dailyCheckIn = dailyCheckInService.getOne(dailyCheckInLambdaQueryWrapper);
            if (ObjectUtils.isNotEmpty(dailyCheckIn)) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "签到失败,今日已签到");
            }
            dailyCheckIn = new DailyCheckIn();
            dailyCheckIn.setUserId(loginUser.getUserId());
            dailyCheckIn.setAddCoins(LOGIN_ADD_COIN);
            boolean dailyCheckInResult = dailyCheckInService.save(dailyCheckIn);
            userService.addLeftCoin(loginUser.getUserId(), dailyCheckIn.getAddCoins());
            if (!dailyCheckInResult) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR);
            }
            return ResultUtil.success(true);
        }, "签到失败");
    }
}
