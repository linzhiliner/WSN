package com.huawei.domain;

import java.io.Serializable;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;


public class Role extends BaseDomainObject implements Serializable {
	private static final long serialVersionUID = 48L;
	
	private String name;
	
	private Set<RegisterUser> registerUsers; 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JSON(serialize=false)
	public Set<RegisterUser> getRegisterUsers() {
		return registerUsers;
	}

	public void setRegisterUsers(Set<RegisterUser> registerUsers) {
		this.registerUsers = registerUsers;
	}

}
