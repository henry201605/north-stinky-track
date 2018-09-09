package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>Project：north-stinky-track</p>
 * <p>Package：com.nstepa.wc.beans</p>
 * <p>Date: 2018/9/9 </p>
 * <p>Time：22:20</p>
 * <p>Description: 描述 </p>
 *
 * @author liuchundong
 * @version 1.0
 */
@TableName("sys_area")
public class SysArea implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 区域名称
     */
    private String name;
    /**
     * 区域编码
     */
    private String code;
    /**
     * 区域多边形
     */
    private String polygon;
    /**
     * 区域中心x坐标
     */
    @TableField("center_x")
    private double centerX;
    /**
     * 区域中心y坐标
     */
    @TableField("center_y")
    private double centerY;
    /**
     * 父级区域ID
     */
    @TableField("parent_id")
    private Integer parentId;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPolygon() {
        return polygon;
    }

    public void setPolygon(String polygon) {
        this.polygon = polygon;
    }

    public double getCenterX() {
        return centerX;
    }

    public void setCenterX(double centerX) {
        this.centerX = centerX;
    }

    public double getCenterY() {
        return centerY;
    }

    public void setCenterY(double centerY) {
        this.centerY = centerY;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "SysArea{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", polygon='" + polygon + '\'' +
                ", centerX=" + centerX +
                ", centerY=" + centerY +
                ", parentId=" + parentId +
                '}';
    }
}
