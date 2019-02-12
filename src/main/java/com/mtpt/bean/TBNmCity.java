package com.mtpt.bean;

public class TBNmCity {
    private Integer id;

    private Integer citynum;

    private String cityname;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCitynum() {
        return citynum;
    }

    public void setCitynum(Integer citynum) {
        this.citynum = citynum;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname == null ? null : cityname.trim();
    }
}