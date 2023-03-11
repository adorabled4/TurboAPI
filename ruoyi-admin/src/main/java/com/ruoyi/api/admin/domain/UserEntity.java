package com.ruoyi.api.admin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 *
 * @author adorabled4
 * @date 2023-03-09
 */
public class UserEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long userId;

    /** 昵称 */
    @Excel(name = "昵称")
    private String userName;

    /** 账号 */
    @Excel(name = "账号")
    private String userAccount;

    /** 头像 */
    @Excel(name = "头像")
    private String avatarUrl;

    /** 性别(1男0女) */
    @Excel(name = "性别(1男0女)")
    private Integer gender;

    /** 密码 */
    @Excel(name = "密码")
    private String password;

    /** 电话 */
    @Excel(name = "电话")
    private String phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String email;

    /** ak */
    @Excel(name = "ak")
    private String accessKey;

    /** sk */
    @Excel(name = "sk")
    private String secretKey;

    /** 0-用户 1-管理员 */
    @Excel(name = "0-用户 1-管理员")
    private Integer userRole;

    /** 逻辑删除(1删除) */
    @Excel(name = "逻辑删除(1删除)")
    private Integer isDelete;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserAccount(String userAccount) 
    {
        this.userAccount = userAccount;
    }

    public String getUserAccount() 
    {
        return userAccount;
    }
    public void setAvatarUrl(String avatarUrl) 
    {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() 
    {
        return avatarUrl;
    }
    public void setGender(Integer gender) 
    {
        this.gender = gender;
    }

    public Integer getGender() 
    {
        return gender;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setAccessKey(String accessKey) 
    {
        this.accessKey = accessKey;
    }

    public String getAccessKey() 
    {
        return accessKey;
    }
    public void setSecretKey(String secretKey) 
    {
        this.secretKey = secretKey;
    }

    public String getSecretKey() 
    {
        return secretKey;
    }
    public void setUserRole(Integer userRole) 
    {
        this.userRole = userRole;
    }

    public Integer getUserRole() 
    {
        return userRole;
    }
    public void setIsDelete(Integer isDelete) 
    {
        this.isDelete = isDelete;
    }

    public Integer getIsDelete() 
    {
        return isDelete;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("userName", getUserName())
            .append("userAccount", getUserAccount())
            .append("avatarUrl", getAvatarUrl())
            .append("gender", getGender())
            .append("password", getPassword())
            .append("phone", getPhone())
            .append("email", getEmail())
            .append("accessKey", getAccessKey())
            .append("secretKey", getSecretKey())
            .append("userRole", getUserRole())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
