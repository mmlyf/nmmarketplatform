package com.mtpt.bean;

import java.util.Date;

public class DsjAll {
    private Integer id;

    private String dxDn;

    private String dxCity;

    private String dxSys;

    private String dxFirp;

    private String dxFirdw;

    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDxDn() {
        return dxDn;
    }

    public void setDxDn(String dxDn) {
        this.dxDn = dxDn == null ? null : dxDn.trim();
    }

    public String getDxCity() {
        return dxCity;
    }

    public void setDxCity(String dxCity) {
        this.dxCity = dxCity == null ? null : dxCity.trim();
    }

    public String getDxSys() {
        return dxSys;
    }

    public void setDxSys(String dxSys) {
        this.dxSys = dxSys == null ? null : dxSys.trim();
    }

    public String getDxFirp() {
        return dxFirp;
    }

    public void setDxFirp(String dxFirp) {
        this.dxFirp = dxFirp == null ? null : dxFirp.trim();
    }

    public String getDxFirdw() {
        return dxFirdw;
    }

    public void setDxFirdw(String dxFirdw) {
        this.dxFirdw = dxFirdw == null ? null : dxFirdw.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}