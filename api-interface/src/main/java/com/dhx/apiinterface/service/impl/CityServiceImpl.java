package com.dhx.apiinterface.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apiinterface.domain.City;
import com.dhx.apiinterface.service.CityService;
import com.dhx.apiinterface.mapper.CityMapper;
import org.springframework.stereotype.Service;

/**
* @author dhx
* @description 针对表【t_city】的数据库操作Service实现
* @createDate 2023-04-15 17:46:51
*/
@Service
public class CityServiceImpl extends ServiceImpl<CityMapper, City>
    implements CityService{

}




