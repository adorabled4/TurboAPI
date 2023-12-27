package com.dhx.apiinterface.v2;

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
import com.alibaba.dashscope.utils.JsonUtils;

import java.util.List;

/**
 * @author adorabled4
 * @className AliTest
 * @date : 2023/12/26/ 22:38
 **/
public class AliTest {
    public static void callWithMessageTest() throws NoApiKeyException, ApiException, InputRequiredException {
        Generation gen = new Generation();
        MessageManager msgManager = new MessageManager(10);
        Message systemMsg = Message.builder().role(Role.SYSTEM.getValue()).content("You are a helpful assistant.").build();
        Message userMsg = Message.builder().role(Role.USER.getValue()).content("你好，周末去哪里玩？").build();
        msgManager.add(systemMsg);
        msgManager.add(userMsg);
        ThreadLocal<String> key = new ThreadLocal<>();
        QwenParam param =
                QwenParam.builder()
                        .model(Generation.Models.QWEN_MAX)
                        .messages(msgManager.get())
                        .resultFormat(QwenParam.ResultFormat.MESSAGE)
                        .apiKey("sk-1b25a81b832f4a289bc34e2088f99594")
                        .topP(0.8)
                        .enableSearch(true)
                        .build();
        GenerationResult result = gen.call(param);
        System.out.println(result);
        msgManager.add(result);
        System.out.println(JsonUtils.toJson(result));
        param.setPrompt("找个近点的");
        param.setMessages(msgManager.get());
        result = gen.call(param);
        System.out.println(result);
        System.out.println(JsonUtils.toJson(result));
    }


    public static void genGoodReputationTest(String recipe) throws NoApiKeyException, ApiException, InputRequiredException {
        Generation gen = new Generation();
        MessageManager msgManager = new MessageManager(10);
        Message userMsg = Message.builder().role(Role.USER.getValue()).content(
                "我需要你帮我生成一段外卖好评 , 字数不多于100字, 这是菜品的名称:  " + recipe + "\n" +
                        "你回答需要严格按照下面的格式\n" +
                        "【【【\n" +
                        "${客套话}\n" +
                        "【【【\n" +
                        "${好评内容}\n" +
                        "【【【").build();
        msgManager.add(userMsg);
        QwenParam param =
                QwenParam.builder()
                        .model(Generation.Models.QWEN_MAX)
                        .messages(msgManager.get())
                        .resultFormat(QwenParam.ResultFormat.MESSAGE)
                        .apiKey("sk-1b25a81b832f4a289bc34e2088f99594")
                        .topP(0.8)
                        .enableSearch(true)
                        .build();
        GenerationResult result = gen.call(param);
        GenerationOutput output = result.getOutput();
        List<GenerationOutput.Choice> choices =output.getChoices();
        GenerationOutput.Choice choice = choices.get(0);
        String content = choice.getMessage().getContent();
        System.out.println(result);
    }

    public void Test() {
        try {
            genGoodReputationTest("石锅鸡");
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }

}
