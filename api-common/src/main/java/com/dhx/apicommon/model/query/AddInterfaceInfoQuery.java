package com.dhx.apicommon.model.query;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author adorabled4
 * @className AddInterfaceInfoQuery
 * @date : 2023/12/27/ 13:42
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddInterfaceInfoQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 接口ID
     */
    @NotNull
    private Long interfaceId;

    /**
     * 接口名称
     */
    @Length(min = 1,max = 256)
    private String interfaceName;

    /**
     * 接口路径
     */
    @Length(min = 1,max = 256)
    private String callPath;

    /**
     * 是否缓存
     */
    @NotNull
    private Boolean isAigc;
}
