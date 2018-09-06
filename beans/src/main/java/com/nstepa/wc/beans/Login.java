package com.nstepa.wc.beans;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author henry
 * @since 2018-09-06
 */
@TableName("log_login")
public class Login implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("user_id")
    private String userId;
    @TableField("login_time")
    private Date loginTime;
    /**
     * 登录Ip
     */
    @TableField("login_ip")
    private String loginIp;
    /**
     * 经度
     */
    private String longitude;
    /**
     * 纬度
     */
    private String latitude;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "Login{" +
        ", id=" + id +
        ", userId=" + userId +
        ", loginTime=" + loginTime +
        ", loginIp=" + loginIp +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        "}";
    }
}
