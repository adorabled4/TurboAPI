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
public class UserInterfaceInfoEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 接口id */
    @Excel(name = "接口id")
    private Long interfaceId;

    /** 总次数 */
    @Excel(name = "总次数")
    private Long totalNum;

    /** 剩余次数 */
    @Excel(name = "剩余次数")
    private Long leftNum;

    /** 0-正常 ，1-禁用 */
    @Excel(name = "0-正常 ，1-禁用")
    private Integer status;

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
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setInterfaceId(Long interfaceId) 
    {
        this.interfaceId = interfaceId;
    }

    public Long getInterfaceId() 
    {
        return interfaceId;
    }
    public void setTotalNum(Long totalNum) 
    {
        this.totalNum = totalNum;
    }

    public Long getTotalNum() 
    {
        return totalNum;
    }
    public void setLeftNum(Long leftNum) 
    {
        this.leftNum = leftNum;
    }

    public Long getLeftNum() 
    {
        return leftNum;
    }
    public void setStatus(Integer status) 
    {
        this.status = status;
    }

    public Integer getStatus() 
    {
        return status;
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
            .append("userId", getUserId())
            .append("interfaceId", getInterfaceId())
            .append("totalNum", getTotalNum())
            .append("leftNum", getLeftNum())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
