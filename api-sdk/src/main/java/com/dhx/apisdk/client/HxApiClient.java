package com.dhx.apisdk.client;

import com.dhx.apicommon.model.v1.param.FileSuffixParam;
import com.dhx.apicommon.model.v1.param.IpAnaParam;
import com.dhx.apicommon.model.v1.param.WeatherParam;
import com.dhx.apisdk.model.TO.ComputerSuffix;
import com.dhx.apisdk.model.TO.LovelornSentence;
import com.dhx.apisdk.model.TO.Poet;
import com.dhx.apisdk.model.TO.WeatherInfo;

public interface HxApiClient {


    /**
     * 获取随机诗句
     *
     * @return
     */
    Poet getRandomPoet();


    /**
     * 获取随机失恋文案
     *
     * @return
     */
    LovelornSentence getRandomLovelornSentence();

    /**
     * 获取 后缀名 详情
     * @param param
     * @return
     */
    ComputerSuffix getDescriptionOfSuffix(FileSuffixParam param);

    /**
     * 分析IP属地
     * @param param
     * @return
     */
    String analyseIP(IpAnaParam param);


    /**
     * 获取指定地区的当前天气情况 , 如果参数为空那么会通过request的IP获取
     * @param weatherParam 地区名称
     * @return
     */
    WeatherInfo getNowWeather( WeatherParam weatherParam);


}
