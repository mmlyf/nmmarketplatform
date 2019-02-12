package com.mtpt.bean;

import java.util.Date;

public class MoBookData {
    private Integer id;

    private String dn;

    private String message;

    private Integer state;

    private Date moTime;

    private Date confimTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn == null ? null : dn.trim();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getMoTime() {
        return moTime;
    }

    public void setMoTime(Date moTime) {
        this.moTime = moTime;
    }

    public Date getConfimTime() {
        return confimTime;
    }

    public void setConfimTime(Date confimTime) {
        this.confimTime = confimTime;
    }
}