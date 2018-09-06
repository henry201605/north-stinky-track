package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 字典类型表
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@TableName("sys_dict_type")
public class DictType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 字典类型名称
     */
    private String name;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 字典的备注说明
     */
    private String note;
    @TableField("create_time")
    private Date createTime;
    @TableField("create_uid")
    private Integer createUid;
    @TableField("update_time")
    private Date updateTime;
    @TableField("update_uid")
    private Integer updateUid;
    /**
     * 假删除标识:0:未删除;1:删除
     */
    @TableField("del_flag")
    private Integer delFlag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateUid() {
        return createUid;
    }

    public void setCreateUid(Integer createUid) {
        this.createUid = createUid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateUid() {
        return updateUid;
    }

    public void setUpdateUid(Integer updateUid) {
        this.updateUid = updateUid;
    }

    public Integer getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(Integer delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        return "DictType{" +
        ", id=" + id +
        ", name=" + name +
        ", status=" + status +
        ", note=" + note +
        ", createTime=" + createTime +
        ", createUid=" + createUid +
        ", updateTime=" + updateTime +
        ", updateUid=" + updateUid +
        ", delFlag=" + delFlag +
        "}";
    }
}
