package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@TableName("b_upload_stinky")
public class UploadStinky implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 风向
     */
    @TableField("wind_direct")
    private String windDirect;
    /**
     * 风力
     */
    @TableField("wind_power")
    private String windPower;
    /**
     * 天气
     */
    private String weather;
    /**
     * 温度
     */
    private Float temperature;
    /**
     * 湿度
     */
    private Float humidity;
    /**
     * 经度
     */
    private BigDecimal longitude;
    /**
     * 纬度
     */
    private BigDecimal latitude;
    /**
     * 恶臭-sys_dict_item_id
     */
    @TableField("stinky_type")
    private Integer stinkyType;
    /**
     * 体感-sys_dict_item_id
     */
    @TableField("feel_type")
    private Integer feelType;
    /**
     * 发生时间
     */
    @TableField("occur_time")
    private Date occurTime;
    /**
     * 填报的用户id
     */
    @TableField("user_id")
    private Integer userId;
    @TableField("user_name")
    private String userName;
    @TableField("temp_fied1")
    private String tempFied1;
    @TableField("temp_fied2")
    private String tempFied2;
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

    public String getWindDirect() {
        return windDirect;
    }

    public void setWindDirect(String windDirect) {
        this.windDirect = windDirect;
    }

    public String getWindPower() {
        return windPower;
    }

    public void setWindPower(String windPower) {
        this.windPower = windPower;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getStinkyType() {
        return stinkyType;
    }

    public void setStinkyType(Integer stinkyType) {
        this.stinkyType = stinkyType;
    }

    public Integer getFeelType() {
        return feelType;
    }

    public void setFeelType(Integer feelType) {
        this.feelType = feelType;
    }

    public Date getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTempFied1() {
        return tempFied1;
    }

    public void setTempFied1(String tempFied1) {
        this.tempFied1 = tempFied1;
    }

    public String getTempFied2() {
        return tempFied2;
    }

    public void setTempFied2(String tempFied2) {
        this.tempFied2 = tempFied2;
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
        return "UploadStinky{" +
        ", id=" + id +
        ", windDirect=" + windDirect +
        ", windPower=" + windPower +
        ", weather=" + weather +
        ", temperature=" + temperature +
        ", humidity=" + humidity +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        ", stinkyType=" + stinkyType +
        ", feelType=" + feelType +
        ", occurTime=" + occurTime +
        ", userId=" + userId +
        ", userName=" + userName +
        ", tempFied1=" + tempFied1 +
        ", tempFied2=" + tempFied2 +
        ", note=" + note +
        ", createTime=" + createTime +
        ", createUid=" + createUid +
        ", updateTime=" + updateTime +
        ", updateUid=" + updateUid +
        ", delFlag=" + delFlag +
        "}";
    }
}
