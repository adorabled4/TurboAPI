package com.ruoyi.api.admin.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 t_interfaceEntity
 * 
 * @author adorabled4
 * @date 2023-03-10
 */
public class InterfaceEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 接口名称 */
    @Excel(name = "接口名称")
    private String name;

    /** 接口描述 */
    @Excel(name = "接口描述")
    private String description;

    /** 是否免费 */
    @Excel(name = "是否免费")
    private Integer isFree;

    /** 接口地址 */
    @Excel(name = "接口地址")
    private String url;

    /** 请求方式 */
    @Excel(name = "请求方式")
    private String method;

    /** 请求参数 */
    @Excel(name = "请求参数")
    private String requestParam;

    /** 请求头 */
    @Excel(name = "请求头")
    private String requestHeader;

    /** 响应头 */
    @Excel(name = "响应头")
    private String responseHeader;

    /** 接口状态(0-关闭 ,1-开启) */
    @Excel(name = "接口状态(0-关闭 ,1-开启)")
    private Integer status;

    /** 创建人 */
    @Excel(name = "创建人")
    private Long userId;

    /** 逻辑删除(1删除) */
    @Excel(name = "逻辑删除(1删除)")
    private Integer isDelete;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDescription(String description) 
    {
        this.description = description;
    }

    public String getDescription() 
    {
        return description;
    }
    public void setIsFree(Integer isFree) 
    {
        this.isFree = isFree;
    }

    public Integer getIsFree() 
    {
        return isFree;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setMethod(String method) 
    {
        this.method = method;
    }

    public String getMethod() 
    {
        return method;
    }
    public void setRequestParam(String requestParam) 
    {
        this.requestParam = requestParam;
    }

    public String getRequestParam() 
    {
        return requestParam;
    }
    public void setRequestHeader(String requestHeader) 
    {
        this.requestHeader = requestHeader;
    }

    public String getRequestHeader() 
    {
        return requestHeader;
    }
    public void setResponseHeader(String responseHeader) 
    {
        this.responseHeader = responseHeader;
    }

    public String getResponseHeader() 
    {
        return responseHeader;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
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
            .append("id", getId())
            .append("name", getName())
            .append("description", getDescription())
            .append("isFree", getIsFree())
            .append("url", getUrl())
            .append("method", getMethod())
            .append("requestParam", getRequestParam())
            .append("requestHeader", getRequestHeader())
            .append("responseHeader", getResponseHeader())
            .append("status", getStatus())
            .append("userId", getUserId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
