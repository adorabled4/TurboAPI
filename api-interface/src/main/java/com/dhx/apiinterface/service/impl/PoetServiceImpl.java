package com.dhx.apiinterface.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.model.v1.PoetVO;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.constant.RedisConstant;
import com.dhx.apiinterface.domain.Poet;
import com.dhx.apiinterface.mapper.PoetMapper;
import com.dhx.apiinterface.service.IPoetService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 诗句Service业务层处理
 *
 * @author adorabled4
 * @date 2023-03-11
 */
@Service
public class PoetServiceImpl extends ServiceImpl<PoetMapper, Poet> implements IPoetService {
    @Autowired
    private PoetMapper poetMapper;

    @Resource
    StringRedisTemplate stringRedisTemplate;


    @Override
    @Cacheable("randomPoet")
    public PoetVO getPoetById(Long id) {
        Poet poet = getById(id);
        if (poet == null) {
            poet = getById(1);
        }
        // 正常返回
        return BeanUtil.copyProperties(poet, PoetVO.class);
    }

    @Override
    public Long randomId() {
        long total = count();
        long id = (long) (Math.random() * total + 1);
        while (id < 0 || id >= total) {
            id = (long) (Math.random() * total + 1);
        }
        return id;
    }


}
