package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

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
@TableName("area_weather")
public class AreaWeather implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 温度
     */
    private double temp;
    /**
     * 天气
     */
    private String weather;
    /**
     * 风力
     */
    @TableField("wind_power")
    private Integer windPower;
    /**
     * 风向
     */
    @TableField("wind_direct")
    private Integer windDirect;
    /**
     * 区域ID
     */
    @TableField("area_id")
    private Integer areaId;
    /**
     * 天气时间
     */
    @TableField("info_time")
    private Date infoTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Integer getWindPower() {
        return windPower;
    }

    public void setWindPower(Integer windPower) {
        this.windPower = windPower;
    }

    public Integer getWindDirect() {
        return windDirect;
    }

    public void setWindDirect(Integer windDirect) {
        this.windDirect = windDirect;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Date getInfoTime() {
        return infoTime;
    }

    public void setInfoTime(Date infoTime) {
        this.infoTime = infoTime;
    }

    @Override
    public String toString() {
        return "AreaWeather{" +
                "id=" + id +
                ", temp=" + temp +
                ", weather='" + weather + '\'' +
                ", windPower=" + windPower +
                ", windDirect=" + windDirect +
                ", areaId=" + areaId +
                ", infoTime=" + infoTime +
                '}';
    }
}
