package com.dhx.apiinterface.gen;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 
 * @TableName interface_metadata
 */
@TableName(value ="interface_metadata")
@Data
public class InterfaceMetadata implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 接口ID
     */
    @TableField(value = "interface_id")
    private Long interfaceId;

    /**
     * 接口名称
     */
    @TableField(value = "interface_name")
    private String interfaceName;

    /**
     * 接口路径
     */
    @TableField(value = "call_path")
    private String callPath;

    /**
     * 缓存条数
     */
    @TableField(value = "data_count")
    private Long dataCount;

    /**
     * 是否开启缓存
     */
    @TableField(value = "is_cache")
    private Integer isCache;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private LocalDateTime updateTime;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

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
        InterfaceMetadata other = (InterfaceMetadata) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getInterfaceId() == null ? other.getInterfaceId() == null : this.getInterfaceId().equals(other.getInterfaceId()))
            && (this.getInterfaceName() == null ? other.getInterfaceName() == null : this.getInterfaceName().equals(other.getInterfaceName()))
            && (this.getCallPath() == null ? other.getCallPath() == null : this.getCallPath().equals(other.getCallPath()))
            && (this.getDataCount() == null ? other.getDataCount() == null : this.getDataCount().equals(other.getDataCount()))
            && (this.getIsCache() == null ? other.getIsCache() == null : this.getIsCache().equals(other.getIsCache()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getIsDeleted() == null ? other.getIsDeleted() == null : this.getIsDeleted().equals(other.getIsDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInterfaceId() == null) ? 0 : getInterfaceId().hashCode());
        result = prime * result + ((getInterfaceName() == null) ? 0 : getInterfaceName().hashCode());
        result = prime * result + ((getCallPath() == null) ? 0 : getCallPath().hashCode());
        result = prime * result + ((getDataCount() == null) ? 0 : getDataCount().hashCode());
        result = prime * result + ((getIsCache() == null) ? 0 : getIsCache().hashCode());
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
        sb.append(", interface_id=").append(interfaceId);
        sb.append(", interface_name=").append(interfaceName);
        sb.append(", call_path=").append(callPath);
        sb.append(", data_count=").append(dataCount);
        sb.append(", is_cache=").append(isCache);
        sb.append(", create_time=").append(createTime);
        sb.append(", update_time=").append(updateTime);
        sb.append(", is_deleted=").append(isDeleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}