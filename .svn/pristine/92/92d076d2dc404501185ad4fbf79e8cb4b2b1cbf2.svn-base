package com.huawei.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;


public class WeiBo extends BaseDomainObject implements Serializable {
	private static final long serialVersionUID = 48L;
	private RegisterUser registerUser;
	private Set<Comment> comments;
	private String comment;
	@JSON(serialize=false)
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	private Integer	comtimes;
	private Integer resendtimes;
	private Integer readtimes;
	private String 	location;

	private Integer accessathl;
	private Double latitude;  //纬度
	private Double longitude; //经度
	private String distance;   //位置微博返回时的距离，数据库不需要保存，只是为了前台显示
	private String 	picture;     //图片地址
	private String miniImgUrl; //缩略图地址
	private Long originalWeiboId;//转发时最初始的微博的id,不是转发的则为0
	
	private Long shopId;//商铺Id,普通用户为0
	
	

	
	public Double getLatitude() {
		return latitude;
	}
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}
	public String getDistance() {
		return distance;
	}
	public void setDistance(String distance) {
		this.distance = distance;
	}
	public RegisterUser getRegisterUser() {
		return registerUser;
	}
	public void setRegisterUser(RegisterUser registerUser) {
		this.registerUser = registerUser;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Integer getComtimes() {
		return comtimes;
	}
	public void setComtimes(Integer comtimes) {
		this.comtimes = comtimes;
	}
	public Integer getResendtimes() {
		return resendtimes;
	}
	public void setResendtimes(Integer resendtimes) {
		this.resendtimes = resendtimes;
	}
	public Integer getReadtimes() {
		return readtimes;
	}
	public void setReadtimes(Integer readtimes) {
		this.readtimes = readtimes;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getAccessathl() {
		return accessathl;
	}
	public void setAccessathl(Integer accessathl) {
		this.accessathl = accessathl;
	}
	public String getMiniImgUrl() {
		return miniImgUrl;
	}
	public void setMiniImgUrl(String miniImgUrl) {
		this.miniImgUrl = miniImgUrl;
	}
	public Long getOriginalWeiboId() {
		return originalWeiboId;
	}
	public void setOriginalWeiboId(Long originalWeiboId) {
		this.originalWeiboId = originalWeiboId;
	}
	public Long getShopId() {
		return shopId;
	}
	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}


}
