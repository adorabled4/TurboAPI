package com.dhx.apicore.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicore.model.DO.DailyCheckIn;
import com.dhx.apicore.mapper.DailyCheckInMapper;
import com.dhx.apicore.service.DailyCheckInService;
import org.springframework.stereotype.Service;

/**
* @author dhx
* @description 针对表【daily_check_in(每日签到表)】的数据库操作Service实现
* @createDate 2023-12-30 17:01:21
*/
@Service
public class DailyCheckInServiceImpl extends ServiceImpl<DailyCheckInMapper, DailyCheckIn>
    implements DailyCheckInService {

}




