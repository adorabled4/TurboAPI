package com.dhx.apiinterface.manager;

import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;

/**
 * @author adorabled4
 * @interface BigModelChat
 * @date : 2024/01/02/ 08:00
 **/
public interface BigModelChat {

    String doChat(String input) throws Exception;
}
