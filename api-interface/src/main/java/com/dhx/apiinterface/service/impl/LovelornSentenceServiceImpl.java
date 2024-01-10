package com.dhx.apiinterface.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apicommon.util.ResultUtil;
import com.dhx.apiinterface.domain.LovelornSentence;
import com.dhx.apiinterface.service.LovelornSentenceService;
import com.dhx.apiinterface.mapper.LovelornSentenceMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author dhx
 * @description 针对表【t_lovelorn_sentence】的数据库操作Service实现
 * @createDate 2023-04-30 17:03:14
 */
@Service
public class LovelornSentenceServiceImpl extends ServiceImpl<LovelornSentenceMapper, LovelornSentence>
        implements LovelornSentenceService {

    @Override
    @Cacheable("randomSentence")
    public LovelornSentence getSentenceById(Long id) {
        LovelornSentence sentence = getById(id);
        if (sentence == null) {
            return getById(1);
        }
        // 正常返回
        return sentence;
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




