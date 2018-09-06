package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 字典数据表
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@TableName("sys_dict_item")
public class DictItem implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Integer id;
    /**
     * 字典内容
     */
    private String name;
    /**
     * 序号
     */
    private Integer sort;
    /**
     * 类型id
     */
    @TableField("type_id")
    private Integer typeId;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
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
        return "DictItem{" +
        ", id=" + id +
        ", name=" + name +
        ", sort=" + sort +
        ", typeId=" + typeId +
        ", note=" + note +
        ", createTime=" + createTime +
        ", createUid=" + createUid +
        ", updateTime=" + updateTime +
        ", updateUid=" + updateUid +
        ", delFlag=" + delFlag +
        "}";
    }
}
