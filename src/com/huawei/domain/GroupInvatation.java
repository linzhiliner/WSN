package com.huawei.domain;

import java.io.Serializable;

public class GroupInvatation extends BaseDomainObject implements Serializable {
	private static final long serialVersionUID = 48L;
	private RegisterUser user1;		//请求用户
	private RegisterUser user12;	//被请求用户
	private Group circle;			//等待好友圈类建好后添加
	private String message;
	private Integer type;				//好友圈申请或邀请
	
	
	

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public RegisterUser getUser1() {
		return user1;
	}
	public void setUser1(RegisterUser user1) {
		this.user1 = user1;
	}
	public RegisterUser getUser12() {
		return user12;
	}
	public void setUser12(RegisterUser user12) {
		this.user12 = user12;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Group getCircle() {
		return circle;
	}
	public void setCircle(Group circle) {
		this.circle = circle;
	}
	
	
}
