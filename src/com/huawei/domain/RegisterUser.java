package com.huawei.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class RegisterUser extends BaseDomainObject implements Serializable {
	private static final long serialVersionUID = 48L;
	private String userName;
	private String password;
	private String email;
	@JSON(serialize=false)
	public Set<ReadMessage> getReadMessages1() {
		return readMessages1;
	}
	
	public void setReadMessages1(Set<ReadMessage> readMessages1) {
		this.readMessages1 = readMessages1;
	}
	@JSON(serialize=false)
	public Set<ReadMessage> getReadMessages2() {
		return readMessages2;
	}
	public void setReadMessages2(Set<ReadMessage> readMessages2) {
		this.readMessages2 = readMessages2;
	}



	private UserType userType;
	
	private Role role;
	private String userRealName;
	private String userLocation;
	private Character sex;
	private Date birthDay;
	private String bloodGroup;
	private String introduction;
	private String qq;
	private String address;
	private String tel;
	private String job;
	private Integer credit;
	private Integer authority;
	private Integer degree;
	private Integer state;
	private String  imgUrl;
	private String  miniImgUrl;
	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	private Long msgId;
	private Set<Friend> friends1 = new HashSet<Friend>();
	private Set<Friend> friends2 = new HashSet<Friend>();
	private Set<Group> groups = new HashSet<Group>();
	private Set<GroupMember> groupMembers = new HashSet<GroupMember>();
	@JSON(serialize=false)
	public Set<GroupMember> getGroupMembers() {
		return groupMembers;
	}

	public void setGroupMembers(Set<GroupMember> groupMembers) {
		this.groupMembers = groupMembers;
	}



	private Set<UnreadMessage> unreadMessages1 = new HashSet<UnreadMessage>();
	private Set<ReadMessage>  unreadMessages2 = new HashSet<ReadMessage>();
	private Set<ReadMessage> readMessages1 = new HashSet<ReadMessage>();
	private Set<ReadMessage> readMessages2 = new HashSet<ReadMessage>();
	private Set<FriendApply> friendApply1 = new HashSet<FriendApply>();
	private Set<FriendApply> friendApply2 = new HashSet<FriendApply>();
	private Set<ShopInfo> shopInfos = new HashSet<ShopInfo>();
	private Set<UserGroupTag> userGroupTags = new HashSet<UserGroupTag>();
	private Set<Tag> tags = new HashSet<Tag>();
	private Set<Coupon> coupons = new HashSet<Coupon>();
	
	@JSON(serialize=false)
	public Set<UnreadMessage> getUnreadMessages1() {
		return unreadMessages1;
	}
	public void setUnreadMessages1(Set<UnreadMessage> unreadMessages1) {
		this.unreadMessages1 = unreadMessages1;
	}
	@JSON(serialize=false)
	public Set<ReadMessage> getUnreadMessages2() {
		return unreadMessages2;
	}
	public void setUnreadMessages2(Set<ReadMessage> unreadMessages2) {
		this.unreadMessages2 = unreadMessages2;
	}
	@JSON(serialize=false)
	public Set<Coupon> getCoupons() {
		return coupons;
	}
	public void setCoupons(Set<Coupon> coupons) {
		this.coupons = coupons;
	}
	@JSON(serialize=false)
	public Set<Tag> getTags() {
		return tags;
	}
	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}
	@JSON(serialize=false)
	public Set<ShopInfo> getShopInfos() {
		return shopInfos;
	}
	public void setShopInfos(Set<ShopInfo> shopInfos) {
		this.shopInfos = shopInfos;
	}
	@JSON(serialize=false)
	public Set<UserGroupTag> getUserGroupTags() {
		return userGroupTags;
	}
	public void setUserGroupTags(Set<UserGroupTag> userGroupTags) {
		this.userGroupTags = userGroupTags;
	}


	@JSON(serialize=false)
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}



	private Date lastLoginTime;
	
	//edit by JH
	private Set<GroupInvatation> groupInvatations1 = new HashSet<GroupInvatation>();
	private Set<GroupInvatation> groupInvatations12 = new HashSet<GroupInvatation>();
	
	@JSON(serialize=false)
	public Set<GroupInvatation> getGroupInvatations1() {
		return groupInvatations1;
	}
	public void setGroupInvatations1(Set<GroupInvatation> groupInvatations1) {
		this.groupInvatations1 = groupInvatations1;
	}
	
	@JSON(serialize=false)
	public Set<GroupInvatation> getGroupInvatations12() {
		return groupInvatations12;
	}
	public void setGroupInvatations12(Set<GroupInvatation> groupInvatations12) {
		this.groupInvatations12 = groupInvatations12;
	}
	
	
	
	//edit by JH END
	
	
	
	private Set<Focus> owners = new HashSet<Focus>();//被关注者set
	private Set<Focus> idols = new HashSet<Focus>();//关注者set
	private Set<WeiBo> weiBos=new  HashSet<WeiBo>();
	private Set<ShopBlog> shopBlogs=new HashSet<ShopBlog>();
	

	@JSON(serialize=false)
	public Set<Focus> getOwners() {
		return owners;
	}
	public void setOwners(Set<Focus> owners) {
		this.owners = owners;
	}
	@JSON(serialize=false)
	public Set<Focus> getIdols() {
		return idols;
	}
	public void setIdols(Set<Focus> idols) {
		this.idols = idols;
	}
	@JSON(serialize=false)
	public Set<WeiBo> getWeiBos() {
		return weiBos;
	}
	public void setWeiBos(Set<WeiBo> weiBos) {
		this.weiBos = weiBos;
	}
	@JSON(serialize=false)
	public Set<ShopBlog> getShopBlogs() {
		return shopBlogs;
	}
	
	public void setShopBlogs(Set<ShopBlog> shopBlogs) {
		this.shopBlogs = shopBlogs;
	}



	

	


	private Set<ExchangedCou> exchangedCous = new HashSet<ExchangedCou>();  //与优惠券表示多对多关系，因为中间表有其他属性，故要设置成两个一对多关联

	@JSON(serialize=false)
	public Set<ExchangedCou> getExchangedCous() {
		return exchangedCous;
	}
	public void setExchangedCous(Set<ExchangedCou> exchangedCous) {
		this.exchangedCous = exchangedCous;
	}
	public UserType getUserType() {
		return userType;
	}
	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUserRealName() {
		return userRealName;
	}
	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}
	public String getUserLocation() {
		return userLocation;
	}
	public void setUserLocation(String userLocation) {
		this.userLocation = userLocation;
	}


	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	public Integer getAuthority() {
		return authority;
	}
	public void setAuthority(Integer authority) {
		this.authority = authority;
	}
	public Integer getDegree() {
		return degree;
	}
	public void setDegree(Integer degree) {
		this.degree = degree;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setSex(Character sex) {
		this.sex = sex;
	}
	public Character getSex() {
		return sex;
	}
	public void setFriends1(Set<Friend> friends1) {
		this.friends1 = friends1;
	}
	@JSON(serialize=false)
	public Set<Friend> getFriends1() {
		return friends1;
	}
	public void setFriends2(Set<Friend> friends2) {
		this.friends2 = friends2;
	}
	@JSON(serialize=false)
	public Set<Friend> getFriends2() {
		return friends2;
	}
	@JSON(serialize=false)
	public Set<FriendApply> getFriendApply1() {
		return friendApply1;
	}
	public void setFriendApply1(Set<FriendApply> friendApply1) {
		this.friendApply1 = friendApply1;
	}
	@JSON(serialize=false)
	public Set<FriendApply> getFriendApply2() {
		return friendApply2;
	}
	public void setFriendApply2(Set<FriendApply> friendApply2) {
		this.friendApply2 = friendApply2;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getMiniImgUrl() {
		return miniImgUrl;
	}

	public void setMiniImgUrl(String miniImgUrl) {
		this.miniImgUrl = miniImgUrl;
	}

	
	
}
