package com.dhx.apiinterface.service;

import com.dhx.apiinterface.domain.LovelornSentence;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author dhx
* @description 针对表【t_lovelorn_sentence】的数据库操作Service
* @createDate 2023-04-30 17:03:14
*/
public interface LovelornSentenceService extends IService<LovelornSentence> {

    LovelornSentence getSentenceById(Long id);

    Long randomId();
}
