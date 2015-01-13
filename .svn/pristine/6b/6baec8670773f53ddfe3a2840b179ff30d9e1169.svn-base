package com.huawei.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class Coupon extends BaseDomainObject implements Serializable {
	private static final long serialVersionUID = 48L;
	private RegisterUser registerUser;		
	private Set<ExchangedCou> exchangedCous = new HashSet<ExchangedCou>();  
	private String information;
	private Integer num;
	private Integer rest;
	private Date createTime;
	private Date expire;
	private Integer credit;
	private Integer discount;
	
	public Integer getDiscount() {
		return discount;
	}
	public void setDiscount(Integer discount) {
		this.discount = discount;
	}
	
	public RegisterUser getRegisterUser() {
		return registerUser;
	}
	public void setRegisterUser(RegisterUser registerUser) {
		this.registerUser = registerUser;
	}
	
	@JSON(serialize=false)
	public Set<ExchangedCou> getExchangedCous() {
		return exchangedCous;
	}
	public void setExchangedCous(Set<ExchangedCou> exchangedCous) {
		this.exchangedCous = exchangedCous;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getRest() {
		return rest;
	}
	public void setRest(Integer rest) {
		this.rest = rest;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getExpire() {
		return expire;
	}
	public void setExpire(Date expire) {
		this.expire = expire;
	}
	public Integer getCredit() {
		return credit;
	}
	public void setCredit(Integer credit) {
		this.credit = credit;
	}
	
}
