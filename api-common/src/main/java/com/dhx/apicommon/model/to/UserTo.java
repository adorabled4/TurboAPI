package com.dhx.apicommon.model.to;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * @author adorabled4
 * @className UserTo
 * @date : 2023/04/19/ 13:53
 **/
@Data
public class UserTo implements Serializable {

    /**
     * 主键
     */
    private Long userId;

    /**
     * 昵称
     */
    private String userName;

    /**
     * 账号
     */
    private String userAccount;

    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * ak
     */
    private String accessKey;

    /**
     * sk
     */
    private String secretKey;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTo userTo = (UserTo) o;
        return Objects.equals(userId, userTo.userId) && Objects.equals(userName, userTo.userName) && Objects.equals(userAccount, userTo.userAccount) && Objects.equals(avatarUrl, userTo.avatarUrl) && Objects.equals(accessKey, userTo.accessKey) && Objects.equals(secretKey, userTo.secretKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userAccount, avatarUrl, accessKey, secretKey);
    }

    @Override
    public String toString() {
        return "UserTo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", accessKey='" + accessKey + '\'' +
                ", secretKey='" + secretKey + '\'' +
                '}';
    }
}
