package com.huawei.action.domain.view;

public class GroupQueryView {

	
	public GroupQueryView(Long id, String groupName, Integer authority,
			String img) {
		super();
		this.id = id;
		this.groupName = groupName;
		this.authority = authority;
		this.img = img;
	}
	public GroupQueryView() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Long id;
	private String groupName;
	private Integer authority;
	private String img;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
}
