package com.huawei.domain;

import java.io.Serializable;

public class UserGroupTag extends BaseDomainObject implements Serializable{
	private static final long serialVersionUID = 48L;
	
	private RegisterUser registerUser;
	
	private String groupTag;
	
	private Integer groupLevel;
	
	public RegisterUser getRegisterUser() {
		return registerUser;
	}
	public void setRegisterUser(RegisterUser registerUser) {
		this.registerUser = registerUser;
	}
	public String getGroupTag() {
		return groupTag;
	}
	public void setGroupTag(String groupTag) {
		this.groupTag = groupTag;
	}
	public Integer getGroupLevel() {
		return groupLevel;
	}
	public void setGroupLevel(Integer groupLevel) {
		this.groupLevel = groupLevel;
	}
	
	
}
