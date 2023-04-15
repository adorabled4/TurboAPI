package com.dhx.apisdk.client;

import cn.hutool.core.util.RandomUtil;
import com.dhx.apisdk.model.TO.Poet;
import com.dhx.apisdk.util.SignUtil;

import java.util.HashMap;
import java.util.Map;

public interface HxApiClient {


    /**
     * 获取随机诗句
     *
     * @return
     */
    Poet getRandomPoet();


    /**
     * 分析IP属地
     * @param ipv4
     * @return
     */
    String analyseIP(String ipv4);


}
