package com.dhx.apiinterface.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dhx.apiinterface.gen.AigcContent;

/**
* @author dhx
* @description 针对表【aigc_content】的数据库操作Service
* @createDate 2023-12-27 11:21:55
*/
public interface AigcContentService extends IService<AigcContent> {

    String genTakeOutComment(Long interfaceId,String recipe);
}
