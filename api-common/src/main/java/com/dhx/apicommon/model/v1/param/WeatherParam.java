package com.dhx.apicommon.model.v1.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author adorabled4
 * @className WeatherParam
 * @date : 2023/12/28/ 23:35
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class WeatherParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "参数不能为空")
    String cityName;

}
