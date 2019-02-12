package com.mtpt.bean;

import java.util.Date;

public class TBBlackList {
    private Integer id;

    private String dn;

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

}