package com.mtpt.bean.page;

public class DsjBookPage {
	private Integer page = 1;
	private Integer limit = 10;
	private Integer totalRecord;
	private Integer totalPage;
	private String dn;
	private String busdate_star;
	private String busdate_end;
	private String modate_star;
	private String modate_end;
	private Integer qudao;
	private String dangw;
	private String city;
	private Integer start;
	
	public DsjBookPage() {}
	public Integer getStart() {
		start=(page-1)*limit;
        return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(Integer totalRecord) {
		this.totalRecord = totalRecord;
	}
	public Integer getTotalPage() {
		totalPage=(totalRecord-1)/limit+1;
        return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	
	public Integer getQudao() {
		return qudao;
	}
	public void setQudao(Integer qudao) {
		this.qudao = qudao;
	}
	public String getBusdate_star() {
		return busdate_star;
	}
	public void setBusdate_star(String busdate_star) {
		this.busdate_star = busdate_star;
	}
	public String getBusdate_end() {
		return busdate_end;
	}
	public void setBusdate_end(String busdate_end) {
		this.busdate_end = busdate_end;
	}
	public String getModate_star() {
		return modate_star;
	}
	public void setModate_star(String modate_star) {
		this.modate_star = modate_star;
	}
	public String getModate_end() {
		return modate_end;
	}
	public void setModate_end(String modate_end) {
		this.modate_end = modate_end;
	}
	public String getDangw() {
		return dangw;
	}
	public void setDangw(String dangw) {
		this.dangw = dangw;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
}
