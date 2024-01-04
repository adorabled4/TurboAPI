package com.dhx.apicommon.model.v1;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author adorabled4
 * @className WeatherResponse
 * @date : 2023/04/15/ 17:15
 **/
@Data
public class WeatherResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String status;
    private String count;
    private String info;
    private String infocode;
    private List<WeatherInfo> lives;
}
