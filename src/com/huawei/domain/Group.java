package com.huawei.domain;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;
public class Group extends BaseDomainObject implements Serializable{
	private static final long serialVersionUID = 48L;
	private Set<UnreadMessage> unreadMessages = new HashSet<UnreadMessage>();
	
	@JSON(serialize=false)
	public Set<UnreadMessage> getUnreadMessages() {
		return unreadMessages;
	}
	public void setUnreadMessages(Set<UnreadMessage> unreadMessages) {
		this.unreadMessages = unreadMessages;
	}

	private RegisterUser  ownerId;
	private String groupName;
	private String intro;
	private String information;
	private String type;

	
	//edit by JH
	
	private Set<GroupInvatation> groupInvatations = new HashSet<GroupInvatation>();
	
	@JSON(serialize=false)
	public Set<GroupInvatation> getGroupInvatations() {
		return groupInvatations;
	}
	public void setGroupInvatations(Set<GroupInvatation> groupInvatations) {
		this.groupInvatations = groupInvatations;
	}
	
	private Set<GroupMember> groupMembers = new HashSet<GroupMember>();
	
	@JSON(serialize=false)
	public Set<GroupMember> getGroupMembers() {
		return groupMembers;
	}
	public void setGroupMembers(Set<GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}
	
	//edit by JH END
	
	
	

	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public RegisterUser getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(RegisterUser ownerId) {
		this.ownerId = ownerId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	

}
