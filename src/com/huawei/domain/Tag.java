package com.huawei.domain;

import java.io.Serializable;

public class Tag extends BaseDomainObject implements Serializable {
	private static final long serialVersionUID = 48L;
	private String tagName;
	private RegisterUser registerUser;
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public RegisterUser getRegisterUser() {
		return registerUser;
	}
	public void setRegisterUser(RegisterUser registerUser) {
		this.registerUser = registerUser;
	}
}
