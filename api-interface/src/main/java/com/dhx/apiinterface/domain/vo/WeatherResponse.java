package com.dhx.apiinterface.domain.vo;

import com.dhx.apiinterface.domain.WeatherInfo;
import lombok.Data;

import java.util.List;

/**
 * @author adorabled4
 * @className WeatherResponse
 * @date : 2023/04/15/ 17:15
 **/
@Data
public class WeatherResponse {
    private String status;
    private String count;
    private String info;
    private String infocode;
    private List<WeatherInfo> lives;
}
