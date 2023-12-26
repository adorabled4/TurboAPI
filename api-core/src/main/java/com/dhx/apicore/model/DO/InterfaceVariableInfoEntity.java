package com.dhx.apicore.model.DO;

import com.baomidou.mybatisplus.annotation.*;
import com.dhx.apicore.model.query.InterfacePubQuery;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @TableName interface_variable_info
 */
@TableName(value = "interface_variable_info")
@Data
public class InterfaceVariableInfoEntity implements Serializable {
    /**
     * 接口ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 请求参数
     */
    private String requestParam;

    /**
     * 请求头
     */
    private String requestHeaders;

    /**
     * 请求示例
     */
    private String requestExample;

    /**
     * 响应示例
     */
    private String responseExample;

    /**
     * 总调用次数
     */
    private Long totalCallCount;

    /**
     * 调用路径
     */
    private String callPath;

    /**
     * 服务地址
     */
    private String serviceAddress;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 逻辑删除字段
     */
    @TableLogic
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public InterfaceVariableInfoEntity(InterfacePubQuery query) {
        this.serviceAddress = query.getServiceAddress();
        this.callPath = query.getCallPath();
        this.requestParam = query.getRequestParam();
        this.requestHeaders = query.getRequestHeaders();
        this.totalCallCount = 0L;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        InterfaceVariableInfoEntity other = (InterfaceVariableInfoEntity) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
                && (this.getRequestParam() == null ? other.getRequestParam() == null : this.getRequestParam().equals(other.getRequestParam()))
                && (this.getRequestHeaders() == null ? other.getRequestHeaders() == null : this.getRequestHeaders().equals(other.getRequestHeaders()))
                && (this.getRequestExample() == null ? other.getRequestExample() == null : this.getRequestExample().equals(other.getRequestExample()))
                && (this.getResponseExample() == null ? other.getResponseExample() == null : this.getResponseExample().equals(other.getResponseExample()))
                && (this.getTotalCallCount() == null ? other.getTotalCallCount() == null : this.getTotalCallCount().equals(other.getTotalCallCount()))
                && (this.getCallPath() == null ? other.getCallPath() == null : this.getCallPath().equals(other.getCallPath()))
                && (this.getServiceAddress() == null ? other.getServiceAddress() == null : this.getServiceAddress().equals(other.getServiceAddress()))
                && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
                && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
                && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getRequestParam() == null) ? 0 : getRequestParam().hashCode());
        result = prime * result + ((getRequestHeaders() == null) ? 0 : getRequestHeaders().hashCode());
        result = prime * result + ((getRequestExample() == null) ? 0 : getRequestExample().hashCode());
        result = prime * result + ((getResponseExample() == null) ? 0 : getResponseExample().hashCode());
        result = prime * result + ((getTotalCallCount() == null) ? 0 : getTotalCallCount().hashCode());
        result = prime * result + ((getCallPath() == null) ? 0 : getCallPath().hashCode());
        result = prime * result + ((getServiceAddress() == null) ? 0 : getServiceAddress().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getIsDeleted() == null) ? 0 : getIsDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", requestParam=").append(requestParam);
        sb.append(", requestHeaders=").append(requestHeaders);
        sb.append(", requestExample=").append(requestExample);
        sb.append(", responseExample=").append(responseExample);
        sb.append(", totalCallCount=").append(totalCallCount);
        sb.append(", callPath=").append(callPath);
        sb.append(", serviceAddress=").append(serviceAddress);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", isDeleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}