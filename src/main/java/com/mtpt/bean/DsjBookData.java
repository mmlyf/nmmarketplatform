package com.mtpt.bean;

import java.util.Date;
/**
 * 
 * @author lvgordon
 *
 */
public class DsjBookData {
    private Integer id;

    private String dn;

    private Integer state;

    private Date mtTime;

    private Date moTime;

    private String moMessage;

    private String firp;

    private String firdw;

    private String systype;

    private String city;

    private Integer msgid;

    private Date addtime;
    
    private int datafrom;

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

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getMtTime() {
        return mtTime;
    }

    public void setMtTime(Date mtTime) {
        this.mtTime = mtTime;
    }

    public Date getMoTime() {
        return moTime;
    }

    public void setMoTime(Date moTime) {
        this.moTime = moTime;
    }

    public String getMoMessage() {
        return moMessage;
    }

    public void setMoMessage(String moMessage) {
        this.moMessage = moMessage == null ? null : moMessage.trim();
    }

    public String getFirp() {
        return firp;
    }

    public void setFirp(String firp) {
        this.firp = firp == null ? null : firp.trim();
    }

    public String getFirdw() {
        return firdw;
    }

    public void setFirdw(String firdw) {
        this.firdw = firdw == null ? null : firdw.trim();
    }

    public String getSystype() {
        return systype;
    }

    public void setSystype(String systype) {
        this.systype = systype == null ? null : systype.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Integer getMsgid() {
        return msgid;
    }

    public void setMsgid(Integer msgid) {
        this.msgid = msgid;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

	public int getDatafrom() {
		return datafrom;
	}

	public void setDatafrom(int datafrom) {
		this.datafrom = datafrom;
	}
    
    
}