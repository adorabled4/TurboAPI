package com.dhx.apicore.model.enums;

import com.dhx.apicommon.common.exception.BusinessException;
import com.dhx.apicommon.common.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="https://blog.dhx.icu/"> adorabled4 </a>
 * @className UserRoleEnum
 * @date : 2023/07/05/ 15:03
 **/
@AllArgsConstructor
@Getter
public enum UserRoleEnum implements BaseEnum<Integer> {
    VISITOR(1, "游客", "visitor"),
    USER(2, "认证用户", "user"),
    VIP(3, "会员用户", "vip"),
    ADMIN(5, "管理员", "admin"),
    BAN(5, "封号用户", "ban");

    private final int index;
    private final String role;
    private final String value;

    private static final Map<String, UserRoleEnum> MAP = new HashMap<>();

    static {
        for (UserRoleEnum status : UserRoleEnum.values()) {
            MAP.put(status.getName(), status);
        }
    }

    @JsonCreator
    public static UserRoleEnum createByName(String role) {
        UserRoleEnum roleEnum = MAP.get(role);
        if (roleEnum == null) {
            throw new IllegalArgumentException("UserRoleEnum not found. role=" + role);
        }
        return MAP.get(role);
    }

    public static UserRoleEnum getUserRoleByValue(String value) {
        UserRoleEnum[] values = values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].getValue().equals(value)) {
                return values[i];
            }
        }
        return null;
    }

    public static UserRoleEnum findUserRoleByValue(String value) {
        UserRoleEnum[] values = values();
        for (int i = 0; i < values.length; i++) {
            if (values[i].getValue().equals(value)) {
                return values[i];
            }
        }
        throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "UserRoleEnum not found. value=" + value);
    }

    @Override
    public String getName() {
        return this.role;
    }

}
