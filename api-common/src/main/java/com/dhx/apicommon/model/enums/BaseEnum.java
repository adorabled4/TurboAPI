package com.dhx.apicommon.model.enums;

import com.baomidou.mybatisplus.annotation.IEnum;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

/**
 * @author adorabled4
 * @className BaseEnum
 * @date : 2023/12/26/ 14:59
 **/
public interface BaseEnum<T extends Serializable> extends IEnum<T> {
    @JsonValue
    String getName();
}