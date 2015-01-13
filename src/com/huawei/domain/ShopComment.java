package com.huawei.domain;

import java.io.Serializable;

/**
 * @author zhang_xiaolei
 *
 */
public class ShopComment extends BaseDomainObject implements Serializable{
	private static final long serialVersionUID = 48L;
	
	private RegisterUser registerUser;
	private ShopInfo shopInfo;
	private Integer score;
	private String comment;
	
	public RegisterUser getRegisterUser() {
		return registerUser;
	}
	public void setRegisterUser(RegisterUser registerUser) {
		this.registerUser = registerUser;
	}
	public ShopInfo getShopInfo() {
		return shopInfo;
	}
	public void setShopInfo(ShopInfo shopInfo) {
		this.shopInfo = shopInfo;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
}
	
	