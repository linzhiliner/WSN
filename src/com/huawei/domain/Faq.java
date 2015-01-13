package com.huawei.domain;

public class Faq extends BaseDomainObject {
	private static final long serialVersionUID = 48L;
	private String yijian;
	private Integer handle;
	public String getYijian() {
		return yijian;
	}
	public void setYijian(String yijian) {
		this.yijian = yijian;
	}
	public Integer getHandle() {
		return handle;
	}
	public void setHandle(Integer handle) {
		this.handle = handle;
	}
	

}
