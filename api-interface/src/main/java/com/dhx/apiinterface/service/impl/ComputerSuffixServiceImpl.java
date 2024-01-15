package com.dhx.apiinterface.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.dhx.apicommon.model.v1.param.FileSuffixParam;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.domain.ComputerSuffix;
import com.dhx.apiinterface.service.ComputerSuffixService;
import com.dhx.apiinterface.mapper.ComputerSuffixMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author dhx
* @description 针对表【t_computer_suffix】的数据库操作Service实现
* @createDate 2023-04-30 17:04:41
*/
@Service
public class ComputerSuffixServiceImpl extends ServiceImpl<ComputerSuffixMapper, ComputerSuffix>
    implements ComputerSuffixService{

    @Override
    @Cacheable("suffix")
    public ComputerSuffix findBySuffix(FileSuffixParam param) {
        QueryWrapper<ComputerSuffix> wrapper = new QueryWrapper<>();
        wrapper.eq("suffix",param);
        List<ComputerSuffix> list = list(wrapper);
        if(list==null || list.size()==0){
            return null;
        }
        return list.get(0);
    }
}




