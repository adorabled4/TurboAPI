package com.dhx.apigateway.util;

import com.dhx.apicommon.model.to.UserTo;

/**
 * @author adorabled4
 * @className UserHolder
 * @date : 2024/01/01/ 23:32
 **/
public class UserHolder {
    public static ThreadLocal<UserTo> user = new ThreadLocal<>();

    public static UserTo getUser() {
        return user.get();
    }

    public static void setUser(UserTo userDTO) {
        user.set(userDTO);
    }
}
