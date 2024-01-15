package com.dhx.apiinterface.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apicommon.common.BaseResponse;
import com.dhx.apicommon.model.v1.PoetVO;
import com.dhx.apiinterface.domain.Poet;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * 诗句Service接口
 * 
 * @author adorabled4
 * @date 2023-03-11
 */
public interface IPoetService extends IService<Poet> {

    PoetVO getPoetById(Long id);

    Long randomId();
}
