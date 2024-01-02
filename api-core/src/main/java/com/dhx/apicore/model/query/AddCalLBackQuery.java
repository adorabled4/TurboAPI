package com.dhx.apicore.model.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

/**
 * @author adorabled4
 * @className AddCalLBackQuery
 * @date : 2024/01/02/ 00:08
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCalLBackQuery {

    @Pattern(regexp ="^(http|https)://.*$", message = "非法的回调消息接收地址!")
    private String callBackUrl;

    private Long interfaceId;

}
