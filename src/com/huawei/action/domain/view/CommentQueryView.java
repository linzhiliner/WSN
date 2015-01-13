package com.huawei.action.domain.view;

import java.util.Date;

import com.huawei.domain.Comment;
import com.huawei.domain.WeiBo;

public class CommentQueryView {
	private Long weiboId;
	private String comment;
	private Date sendTime;
	private Long registerUserId;
	private String registerUserName;
	private String registerUserImg;       //评论者的头像地址
	private String registerUserMiniImg;   //评论者的头像缩略图地址
	private WeiBoQueryView weibo;
	private Long receiverId;              //接受者的Id
	private String receiverName;          //接受者的名字
	private CommentQueryView relatedComment;      //相关评论
	private Long id;
    
	private Long receiverType;  //评论接收者类型
	private Long receiverShopId;//评论接收者为商铺的时的Id
	
	private Long registerUserType;  //被评论者类型
	private Long registerUserShopId;//被评论者为商铺的时的Id
	
	


	public Long getReceiverType() {
		return receiverType;
	}

	public void setReceiverType(Long receiverType) {
		this.receiverType = receiverType;
	}

	public Long getReceiverShopId() {
		return receiverShopId;
	}

	public void setReceiverShopId(Long receiverShopId) {
		this.receiverShopId = receiverShopId;
	}

	public Long getRegisterUserType() {
		return registerUserType;
	}

	public void setRegisterUserType(Long registerUserType) {
		this.registerUserType = registerUserType;
	}

	public Long getRegisterUserShopId() {
		return registerUserShopId;
	}

	public void setRegisterUserShopId(Long registerUserShopId) {
		this.registerUserShopId = registerUserShopId;
	}

	public Long getWeiboId() {
		return weiboId;
	}

	public void setWeiboId(Long weiboId) {
		this.weiboId = weiboId;
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

	public Long getRegisterUserId() {
		return registerUserId;
	}

	public void setRegisterUserId(Long registerUserId) {
		this.registerUserId = registerUserId;
	}

	public String getRegisterUserName() {
		return registerUserName;
	}

	public void setRegisterUserName(String registerUserName) {
		this.registerUserName = registerUserName;
	}

	public String getRegisterUserImg() {
		return registerUserImg;
	}

	public void setRegisterUserImg(String registerUserImg) {
		this.registerUserImg = registerUserImg;
	}

	public String getRegisterUserMiniImg() {
		return registerUserMiniImg;
	}

	public void setRegisterUserMiniImg(String registerUserMiniImg) {
		this.registerUserMiniImg = registerUserMiniImg;
	}



	public WeiBoQueryView getWeibo() {
		return weibo;
	}

	public void setWeibo(WeiBoQueryView weibo) {
		this.weibo = weibo;
	}

	public Long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Long receiverId) {
		this.receiverId = receiverId;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public CommentQueryView getRelatedComment() {
		return relatedComment;
	}

	public void setRelatedComment(CommentQueryView relatedComment) {
		this.relatedComment = relatedComment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

   
	
}