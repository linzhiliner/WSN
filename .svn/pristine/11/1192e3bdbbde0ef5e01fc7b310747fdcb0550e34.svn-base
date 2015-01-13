package com.huawei.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class Friend extends BaseDomainObject implements Serializable{
	private static final long serialVersionUID = 48L;
	private RegisterUser user1;		
	private RegisterUser user12;	
	private String user1Group;
	private String user12Group;
	private Integer user1Level;
	private Integer user12Level;
	
	private Set<FriendApply> friendApply = new HashSet<FriendApply>();
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
	public String getUser1Group() {
		return user1Group;
	}
	public void setUser1Group(String user1Group) {
		this.user1Group = user1Group;
	}


	public Integer getUser1Level() {
		return user1Level;
	}
	public void setUser1Level(Integer user1Level) {
		this.user1Level = user1Level;
	}
	public String getUser12Group() {
		return user12Group;
	}
	public void setUser12Group(String user12Group) {
		this.user12Group = user12Group;
	}
	public Integer getUser12Level() {
		return user12Level;
	}
	public void setUser12Level(Integer user12Level) {
		this.user12Level = user12Level;
	}
	public void setFriendApply(Set<FriendApply> friendApply) {
		this.friendApply = friendApply;
	}
	public Set<FriendApply> getFriendApply() {
		return friendApply;
	}


	
	
}
