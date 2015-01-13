package com.huawei.action.domain.view;

import java.util.Date;

import com.huawei.domain.WeiBo;

public class WeiBoQueryView {
	private Long id;
	private Long userId;
	private String userName;
	private Long userType;
	private Long shopId;//商铺Id,如果是普通用户，则为0
	private String comment;
	private Date sendTime;
	private Integer comtimes;
	private Integer resendtimes;
	private Integer readtimes;
	private String location;
	private String picture;//图片地址
	private String miniImgUrl; //缩略图地址
	private Integer accessathl;
	private Double latitude; // 纬度
	private Double longitude; // 经度
	private String distance; // 位置微博返回时的距离，数据库不需要保存，只是为了前台显示
	private WeiBo originalWeiBo;
	private Long originalWeiboId;//转发时最初始的微博的id,不是转发的则为0
	private String userHeadPortrait;//博主头像地址
	private String userMiniHeadPortrait;//博主头像缩略图头像
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}



	public Long getUserType() {
		return userType;
	}

	public void setUserType(Long userType) {
		this.userType = userType;
	}

	public Long getShopId() {
		return shopId;
	}

	public void setShopId(Long shopId) {
		this.shopId = shopId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
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

	public WeiBo getOriginalWeiBo() {
		return originalWeiBo;
	}

	public void setOriginalWeiBo(WeiBo originalWeiBo) {
		this.originalWeiBo = originalWeiBo;
	}

	public Long getOriginalWeiboId() {
		return originalWeiboId;
	}

	public void setOriginalWeiboId(Long originalWeiboId) {
		this.originalWeiboId = originalWeiboId;
	}

	public String getMiniImgUrl() {
		return miniImgUrl;
	}

	public void setMiniImgUrl(String miniImgUrl) {
		this.miniImgUrl = miniImgUrl;
	}

	public String getUserHeadPortrait() {
		return userHeadPortrait;
	}

	public void setUserHeadPortrait(String userHeadPortrait) {
		this.userHeadPortrait = userHeadPortrait;
	}

	public String getUserMiniHeadPortrait() {
		return userMiniHeadPortrait;
	}

	public void setUserMiniHeadPortrait(String userMiniHeadPortrait) {
		this.userMiniHeadPortrait = userMiniHeadPortrait;
	}

}
