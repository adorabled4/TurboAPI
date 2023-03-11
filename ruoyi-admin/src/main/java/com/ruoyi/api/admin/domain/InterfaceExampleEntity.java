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
public class InterfaceExampleEntity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 接口id */
    @Excel(name = "接口id")
    private Long interfaceId;

    /** 调用示例 */
    @Excel(name = "调用示例")
    private String callExample;

    /** 返回示例 */
    @Excel(name = "返回示例")
    private String returnExample;

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
    public void setInterfaceId(Long interfaceId) 
    {
        this.interfaceId = interfaceId;
    }

    public Long getInterfaceId() 
    {
        return interfaceId;
    }
    public void setCallExample(String callExample) 
    {
        this.callExample = callExample;
    }

    public String getCallExample() 
    {
        return callExample;
    }
    public void setReturnExample(String returnExample) 
    {
        this.returnExample = returnExample;
    }

    public String getReturnExample() 
    {
        return returnExample;
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
            .append("interfaceId", getInterfaceId())
            .append("callExample", getCallExample())
            .append("returnExample", getReturnExample())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
