package com.dhx.apicore.model.param;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author adorabled4
 * @className InterfaceInfoRequest
 * @date : 2023/04/18/ 11:31
 **/
@Data
public class InterfaceInfoRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @NotNull(message = "接口ID为空!")
    @Min(value = 0,message = "接口ID非法!")
    private Long interfaceId;

    /**
     * 用户请求参数
     */
    private String userRequestParams;



}
