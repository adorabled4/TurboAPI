package com.dhx.apicommon.model.v3.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author adorabled4
 * @className IdiomParm
 * @date : 2023/12/30/ 19:39
 **/
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class IdiomParm implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String wd;

}
