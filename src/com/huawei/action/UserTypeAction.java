package com.huawei.action;

import java.util.Date;

import com.huawei.domain.Comment;
import com.huawei.domain.UserType;
import com.huawei.service.ICommentService;
import com.huawei.service.IUserTypeService;
import com.opensymphony.xwork2.ModelDriven;

public class UserTypeAction extends BaseAction implements ModelDriven<UserType>{
	
	private UserType userType=new UserType();
	
	private IUserTypeService<UserType> userTypeService;
	
	public UserType getModel(){
		return userType;
	}
	
	public String list() throws Exception{
		return SUCCESS;
	}
	
	public String add() throws Exception{
		userType.setDateCreated(new Date());
		userTypeService.save(userType);
		return SUCCESS;
	}
	
	public String modify() throws Exception{
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		userTypeService.delete(UserType.class, userType.getId());
		return SUCCESS;
	}
	
	public void setUserTypeService(IUserTypeService<UserType> userTypeService){
	    this.userTypeService=userTypeService;
	}
}