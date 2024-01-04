package com.dhx.apicommon.model.v1.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author adorabled4
 * @className FileSuffixParam
 * @date : 2023/12/29/ 00:30
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class IpAnaParam implements Serializable {

    private static final long serialVersionUID = 1L;
    @NotNull(message = "参数不能为空")
    private String ip;

}
