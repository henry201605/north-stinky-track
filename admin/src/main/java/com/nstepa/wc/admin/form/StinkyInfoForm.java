package com.nstepa.wc.admin.form;

import com.baomidou.mybatisplus.annotations.TableField;
import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@ApiModel(value = "恶臭信息", description = "恶臭信息")
public class StinkyInfoForm {

    @ApiModelProperty(value = "恶臭类型Id", required = true)
    @NotNull(message = "恶臭类型不能为空")
    private Integer stinkyType;

    @ApiModelProperty(value = "体感类型Id", required = true)
    @NotNull(message = "体感类型不能为空")
    private Integer feelType;

    @ApiModelProperty(value = "风向", required = true)
    @NotNull(message = "风向不能为空")
    private String windDirect;

    @ApiModelProperty(value = "风力", required = true)
    @NotNull(message = "风力不能为空")
    private String windPower;

    @ApiModelProperty(value = "经度", required = true)
    @NotNull(message = "经度不能为空")
    private double longitude;

    @ApiModelProperty(value = "纬度", required = true)
    @NotNull(message = "纬度不能为空")
    private double latitude;

    @ApiModelProperty(value = "发生时间", required = true)
//    @NotNull(message = "发生时间不能为空")
    private Date occurTime;

    @ApiModelProperty(value = "备注", required = true)
    private String note;

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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Date getOccurTime() {
        return occurTime;
    }

    public void setOccurTime(Date occurTime) {
        this.occurTime = occurTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "StinkyInfoForm{" +
                "stinkyType=" + stinkyType +
                ", feelType=" + feelType +
                ", windDirect='" + windDirect + '\'' +
                ", windPower='" + windPower + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", occurTime=" + occurTime +
                ", note='" + note + '\'' +
                '}';
    }
}
