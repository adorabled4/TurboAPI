package com.dhx.apiinterface.service.impl;

import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dhx.apiinterface.domain.enums.PromptEnum;
import com.dhx.apiinterface.gen.AigcContent;
import com.dhx.apiinterface.manager.AliModelManager;
import com.dhx.apiinterface.mapper.AigcContentMapper;
import com.dhx.apiinterface.service.AigcContentService;
import com.dhx.apiinterface.service.InterfaceMetadataService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author dhx
 * @description 针对表【aigc_content】的数据库操作Service实现
 * @createDate 2023-12-27 11:21:55
 */
@Service
public class AigcContentServiceImpl extends ServiceImpl<AigcContentMapper, AigcContent> implements AigcContentService {
    @Resource
    AliModelManager aliModelManager;
    @Resource
    InterfaceMetadataService interfaceMetadataService;

    @Override
    public String genTakeOutComment(Long interfaceId, String recipe) {
        // 查询是否有已经持久化的内容
        List<AigcContent> contents = getContentByParam(interfaceId, recipe);
        if (!contents.isEmpty()) {
            AigcContent aigcContent = contents.get(0);
            return aigcContent.getData();
        } else {
            // 提问 并 持久化
            // 准备prompt
            String input = buildInputByEnum(recipe, PromptEnum.TAKEOUT_COMMENT);
            try {
                String result = aliModelManager.doChat(input);
                String handledResult = handleTakeOutResult(result);
                AigcContent content = AigcContent.builder().interfaceId(interfaceId).param(recipe).data(handledResult).createTime(LocalDateTime.now()).build();
                save(content);
                interfaceMetadataService.increDataCount(interfaceId);
                return handledResult;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String handleTakeOutResult(String result) {
        String[] split = result.split("【【【");
        StringBuilder sb = new StringBuilder();
        for (String s : split) {
            if (!s.trim().equals("")) {
                sb.append(s);
            }
        }
        String value = sb.toString();
        value = value.replace("${好评内容}", "")
                .replace("【", "")
                .replace("】", "");
        return value;
    }

    private List<AigcContent> getContentByParam(Long interfaceId, String param) {
        return query().eq("interface_id", interfaceId).like("param", param).list();
    }

    private String buildInputByEnum(String input, PromptEnum promptEnum) {
        return promptEnum.getPrefix() + input + promptEnum.getSuffix();
    }

}




