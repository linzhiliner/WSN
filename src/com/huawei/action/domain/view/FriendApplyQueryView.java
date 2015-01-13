package com.huawei.action.domain.view;

public class FriendApplyQueryView {

	public FriendApplyQueryView(String user1Name, String message) {
		super();
		this.user1Name = user1Name;
		this.message=message;
	}
	public FriendApplyQueryView()
	{
		super();
	}
	
	private String user1Name;
	private String message;
	
	
	public String getUser1Name() {
		return user1Name;
	}
	public void setUser1Name(String user12Name) {
		this.user1Name = user1Name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
