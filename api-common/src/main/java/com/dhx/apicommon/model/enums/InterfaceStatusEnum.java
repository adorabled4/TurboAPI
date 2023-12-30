package com.dhx.apicommon.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author adorabled4
 * @className InterfaceTypeEnum
 * @date : 2023/12/26/ 14:35
 **/
@Getter
@AllArgsConstructor
public enum InterfaceStatusEnum {
    AVAILABLE(1, "可用"),
    CLOSED(2, "已关闭"),
    DEVELOPING(3, "开发中"),
    BANNED(4, "禁用");

    private final int index;
    private final String value;

    private static final Map<String, InterfaceStatusEnum> MAP = new HashMap<>();

    static {
        for (InterfaceStatusEnum status : InterfaceStatusEnum.values()) {
            MAP.put(status.getValue(), status);
        }
    }

    @JsonCreator
    public static InterfaceStatusEnum createByName(String name) {
        InterfaceStatusEnum status = MAP.get(name);
        if (status == null) {
            throw new IllegalArgumentException("InterfaceStatusEnum not found. name=" + name);
        }
        return MAP.get(name);
    }

}
