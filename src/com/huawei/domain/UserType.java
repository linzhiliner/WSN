package com.huawei.domain;

import java.io.Serializable;

public class UserType extends BaseDomainObject implements Serializable {
	private static final long serialVersionUID = 48L;
	
	private String typeName;

	
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}


}
