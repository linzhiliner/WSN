package com.huawei.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

/**
 * @author zhang_xiaolei
 *
 */
public class Comment extends BaseDomainObject implements Serializable{
	private static final long serialVersionUID = 48L;
	
	private RegisterUser registerUser;
	private WeiBo weibo;
	private String comment;
	
	
	
	//add by zzq 2014-1-16
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

	
	
	
	/**
	 * @author zzq
	 * @date 2013年12月15日
	 * 评论的接收者
	 */
	private RegisterUser receiver;
	

	
	public RegisterUser getRegisterUser() {
		return registerUser;
	}
	public void setRegisterUser(RegisterUser registerUser) {
		this.registerUser = registerUser;
	}
	
	
    /**
     * @author zzq
     * @date 2013年12月15日
     * 相关评论，用于显示对某条评论的回复
     */
    private Comment relatedComment;
	
	public Comment getRelatedComment() {
		return relatedComment;
	}
	public void setRelatedComment(Comment relatedComment) {
		this.relatedComment = relatedComment;
	}
	
	private Set<Comment> comments=new HashSet<Comment>();
	
	
	@JSON(serialize=false)
	public Set<Comment> getComments() {
		return comments;
	}
	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	public WeiBo getWeibo() {
		return weibo;
	}
	public void setWeibo(WeiBo weibo) {
		this.weibo = weibo;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public RegisterUser getReceiver() {
		return receiver;
	}
	public void setReceiver(RegisterUser receiver) {
		this.receiver = receiver;
	}
	
	
	
}