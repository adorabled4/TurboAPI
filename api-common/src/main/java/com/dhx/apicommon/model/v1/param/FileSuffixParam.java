package com.dhx.apicommon.model.v1.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author adorabled4
 * @className FileSuffixParam
 * @date : 2023/12/29/ 00:30
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileSuffixParam {

    @NotNull(message = "参数不能为空")
    private String suffix;

}
