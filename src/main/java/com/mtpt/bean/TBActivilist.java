package com.mtpt.bean;

import java.util.Date;

public class TBActivilist {
    private Integer id;

    private String actititle;

    private Date startTime;

    private Date endTime;

    private String datadetail;

    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActititle() {
        return actititle;
    }

    public void setActititle(String actititle) {
        this.actititle = actititle == null ? null : actititle.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getDatadetail() {
        return datadetail;
    }

    public void setDatadetail(String datadetail) {
        this.datadetail = datadetail == null ? null : datadetail.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}