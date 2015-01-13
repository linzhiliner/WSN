package com.huawei.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;


public class ShopBlog extends BaseDomainObject implements Serializable {
	private static final long serialVersionUID = 48L;
	private RegisterUser registerUser;
	private String comment;
	private Integer num;
	private String picture;
	private Integer readtimes;


	public RegisterUser getRegisterUser() {
		return registerUser;
	}

	public void setRegisterUser(RegisterUser registerUser) {
		this.registerUser = registerUser;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}


	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getReadtimes() {
		return readtimes;
	}

	public void setReadtimes(Integer readtimes) {
		this.readtimes = readtimes;
	}

}
