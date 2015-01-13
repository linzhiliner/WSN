package com.huawei.action.domain.view;

public class Friend1QueryView {


	



	public Friend1QueryView(Long user12Id, Integer user12Level,
			String user12Name, String img) {
		super();
		this.user12Id = user12Id;
		this.user12Level = user12Level;
		this.user12Name = user12Name;
		this.img = img;
	}

	public Friend1QueryView() {
		super();
		// TODO Auto-generated constructor stub
	}

	private Long user12Id;
	private Integer user12Level;
	private String user12Name;
	private String img;
	public Long getUser12Id() {
		return user12Id;
	}

	public void setUser12Id(Long user12Id) {
		this.user12Id = user12Id;
	}

	public Integer getUser12Level() {
		return user12Level;
	}

	public void setUser12Level(Integer user12Level) {
		this.user12Level = user12Level;
	}

	public String getUser12Name() {
		return user12Name;
	}

	public void setUser12Name(String user12Name) {
		this.user12Name = user12Name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	
}
