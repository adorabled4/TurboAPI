package com.dhx.apiinterface.gen;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
    private Long interface_id;

    /**
     * 接口名称
     */
    @TableField(value = "interface_name")
    private String interface_name;

    /**
     * 接口路径
     */
    @TableField(value = "call_path")
    private String call_path;

    /**
     * 缓存条数
     */
    @TableField(value = "data_count")
    private Long data_count;

    /**
     * 是否开启缓存
     */
    @TableField(value = "is_cache")
    private Integer is_cache;

    /**
     * 创建时间
     */
    @TableField(value = "create_time")
    private Date create_time;

    /**
     * 更新时间
     */
    @TableField(value = "update_time")
    private Date update_time;

    /**
     * 逻辑删除
     */
    @TableField(value = "is_delete")
    private Integer is_delete;

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
            && (this.getInterface_id() == null ? other.getInterface_id() == null : this.getInterface_id().equals(other.getInterface_id()))
            && (this.getInterface_name() == null ? other.getInterface_name() == null : this.getInterface_name().equals(other.getInterface_name()))
            && (this.getCall_path() == null ? other.getCall_path() == null : this.getCall_path().equals(other.getCall_path()))
            && (this.getData_count() == null ? other.getData_count() == null : this.getData_count().equals(other.getData_count()))
            && (this.getIs_cache() == null ? other.getIs_cache() == null : this.getIs_cache().equals(other.getIs_cache()))
            && (this.getCreate_time() == null ? other.getCreate_time() == null : this.getCreate_time().equals(other.getCreate_time()))
            && (this.getUpdate_time() == null ? other.getUpdate_time() == null : this.getUpdate_time().equals(other.getUpdate_time()))
            && (this.getIs_delete() == null ? other.getIs_delete() == null : this.getIs_delete().equals(other.getIs_delete()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getInterface_id() == null) ? 0 : getInterface_id().hashCode());
        result = prime * result + ((getInterface_name() == null) ? 0 : getInterface_name().hashCode());
        result = prime * result + ((getCall_path() == null) ? 0 : getCall_path().hashCode());
        result = prime * result + ((getData_count() == null) ? 0 : getData_count().hashCode());
        result = prime * result + ((getIs_cache() == null) ? 0 : getIs_cache().hashCode());
        result = prime * result + ((getCreate_time() == null) ? 0 : getCreate_time().hashCode());
        result = prime * result + ((getUpdate_time() == null) ? 0 : getUpdate_time().hashCode());
        result = prime * result + ((getIs_delete() == null) ? 0 : getIs_delete().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", interface_id=").append(interface_id);
        sb.append(", interface_name=").append(interface_name);
        sb.append(", call_path=").append(call_path);
        sb.append(", data_count=").append(data_count);
        sb.append(", is_cache=").append(is_cache);
        sb.append(", create_time=").append(create_time);
        sb.append(", update_time=").append(update_time);
        sb.append(", is_delete=").append(is_delete);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}