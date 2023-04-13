package com.dhx.apicore.util;


import com.dhx.apicore.model.DTO.UserDTO;

/**
 * @author adorabled4
 * @className UserHolder
 * @date : 2023/04/10/ 18:34
 **/
public class UserHolder {
    public static ThreadLocal<UserDTO> user = new ThreadLocal<>();

    public static UserDTO getUser() {
        return user.get();
    }

    public static void setUser(UserDTO userDTO) {
        user.set(userDTO);
    }

}
