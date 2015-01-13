package com.huawei.domain;

import java.io.Serializable;
import java.sql.Date;

public class ReadMessage extends BaseDomainObject implements Serializable {
	private static final long serialVersionUID = 48L;
	private RegisterUser srcUser;		//�����û�
	private RegisterUser desUser;		//�����û�
	private String info;				//������Ϣ
	private Integer messageType;
	private String minUrl;
	
	
	public String getMinUrl() {
		return minUrl;
	}
	public void setMinUrl(String minUrl) {
		this.minUrl = minUrl;
	}
	public Integer getMessageType() {
		return messageType;
	}
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	
	
	
	public RegisterUser getSrcUser() {
		return srcUser;
	}
	public void setSrcUser(RegisterUser srcUser) {
		this.srcUser = srcUser;
	}
	public RegisterUser getDesUser() {
		return desUser;
	}
	public void setDesUser(RegisterUser desUser) {
		this.desUser = desUser;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}

	
	
	
	
}
