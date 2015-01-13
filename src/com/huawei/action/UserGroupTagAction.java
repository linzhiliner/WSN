package com.huawei.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import com.huawei.domain.Coupon;
import com.huawei.domain.Tag;
import com.huawei.domain.UserGroupTag;
import com.huawei.service.IUserGroupTagService;
import com.opensymphony.xwork2.ModelDriven;

public class UserGroupTagAction  extends BaseAction implements ModelDriven<UserGroupTag>{
	private UserGroupTag userGroupTag;
	private IUserGroupTagService<UserGroupTag> userGroupTagService;
	int total;
	List<UserGroupTag> rowsList;
	@JSON(serialize=false)
	public UserGroupTag getModel() {
		// TODO Auto-generated method stub
		return userGroupTag;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(name="rows")
	public List<UserGroupTag> getRowsList() {
		return rowsList;
	}
	public void setRowsList(List<UserGroupTag> rowsList) {
		this.rowsList = rowsList;
	}
	public void setUserGroupTagService(
			IUserGroupTagService<UserGroupTag> userGroupTagService) {
		this.userGroupTagService = userGroupTagService;
	}
	public String list() throws Exception
	{
		System.out.println(userGroupTag.getRegisterUser().getId());
		String hql = "from UserGroupTag u where u.registerUser.id="+userGroupTag.getRegisterUser().getId()+"order by u.id desc";
		rowsList = userGroupTagService.list(hql, (page -1)*rows, rows, null);
		hql = "select count(*) from UserGroupTag u";
		total = userGroupTagService.getTotalCount(hql, null);
		return SUCCESS;
	}
	
	public String add() throws Exception
	{
		userGroupTag.setDateCreated(new Date());
		userGroupTagService.save(userGroupTag);
		return SUCCESS;
	}
	
	public String modify() throws Exception
	{
		UserGroupTag userGroupTagInDataBase = userGroupTagService.get(UserGroupTag.class, userGroupTag.getId());
		BeanUtils.copyProperties(userGroupTag, userGroupTagInDataBase,new String[]{"dateCreated"});
		userGroupTagService.update(userGroupTagInDataBase);
		return SUCCESS;
	}
	
	public String delete() throws Exception
	{
		userGroupTagService.delete(UserGroupTag.class, userGroupTag.getId());
		return SUCCESS;
	}
}
