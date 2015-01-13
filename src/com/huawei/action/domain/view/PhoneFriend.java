package com.huawei.action.domain.view;

public class PhoneFriend {
	private String userName;
	private String tel;
	// 默认为非好友
	private boolean isFriends = false;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public boolean isIsFriends() {
		return isFriends;
	}

	public void setIsFriends(boolean isFriends) {
		this.isFriends = isFriends;
	}

}
