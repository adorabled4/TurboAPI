package com.dhx.apiinterface.manager;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationOutput;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.MessageManager;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.dhx.apiinterface.config.AIGCKeyConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author adorabled4
 * @className AliModelManager
 * @date : 2023/12/27/ 10:01
 **/
@Service(value = "QWEN_MAX")
@Slf4j
public class AliModelManager implements BigModelChat {

    // 准备一个线程池, 每个线程都包含一个专属的key,存储到ThreadLocal中, 达到避免干扰的效果

    private ThreadLocal<String> apiKey = new ThreadLocal<>();

    @Resource
    AIGCKeyConfig aigcKeyConfig;


    @Override
    public String doChat(String input) throws Exception {
        String localApiKey = apiKey.get();
        if (localApiKey == null) {
            handleSK();
        }
        Generation gen = new Generation();
        MessageManager msgManager = new MessageManager(10);
        Message userMsg = Message.builder().role(Role.USER.getValue()).content(input).build();
        msgManager.add(userMsg);
        QwenParam param = QwenParam.builder()
                .model(Generation.Models.QWEN_MAX)
                .messages(msgManager.get())
                .resultFormat(QwenParam.ResultFormat.MESSAGE)
                .apiKey(apiKey.get())
                .topP(0.8)
                .enableSearch(true)
                .build();
        GenerationResult result = gen.call(param);
        GenerationOutput output = result.getOutput();
        List<GenerationOutput.Choice> choices = output.getChoices();
        GenerationOutput.Choice choice = choices.get(0);
        return choice.getMessage().getContent();
    }

    private void handleSK() {
        List<String> sks = aigcKeyConfig.getKeys().get("ali");
        if (sks.isEmpty()) {
            throw new UnsupportedOperationException("api key is null!");
        }
        apiKey.set(sks.get(0));
    }
}
