package com.dhx.apicommon.model.v3.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author adorabled4
 * @className ConstellationParam
 * @date : 2023/12/30/ 19:39
 **/
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ConstellationParam {

    @NotNull
    private String keyword;

}
