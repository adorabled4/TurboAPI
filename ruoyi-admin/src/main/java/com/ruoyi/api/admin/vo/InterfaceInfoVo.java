package com.ruoyi.api.admin.vo;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.annotation.Excel;

import java.io.Serializable;

/**
 * @author adorabled4
 * @className UserInfoVo
 * @date : 2023/04/15/ 12:16
 **/
public class InterfaceInfoVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 接口名称 */
    private String name;

    /** 接口描述 */
    private String description;

    /** 是否免费 */
    private Integer isFree;

    /** 接口地址 */
    private String url;

    /** 请求方式 */
    private String method;

    /** 请求参数 */
    private String requestParam;

    /** 请求头 */
    private String requestHeader;

    /** 响应头 */
    private String responseHeader;

    /** 接口状态(0-关闭 ,1-开启) */
    private Integer status;

    /** 创建人 */
    private Long userId;

    /** 逻辑删除(1删除) */
    private Integer isDelete;

    private String userName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(String requestParam) {
        this.requestParam = requestParam;
    }

    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
