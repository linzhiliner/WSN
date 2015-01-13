package com.huawei.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import java.util.Map;

import com.huawei.action.domain.view.FriendApplyQueryView;
import com.huawei.action.domain.view.FriendQueryView;
import com.huawei.domain.Friend;
import com.huawei.domain.FriendApply;
import com.huawei.domain.Notification;
import com.huawei.domain.RegisterUser;
import com.huawei.service.IFriendApplyService;
import com.huawei.service.INotificationService;
import com.huawei.service.IRegisterUserService;
import com.opensymphony.xwork2.ModelDriven;

public class FriendApplyAction extends BaseAction implements ModelDriven<FriendApply>{
	private FriendApply friendApply = new FriendApply();
	private IFriendApplyService<FriendApply> friendApplyService;
	private INotificationService<Notification> notificationService;
	public void setNotificationService(
			INotificationService<Notification> notificationService) {
		this.notificationService = notificationService;
	}


	private IRegisterUserService<RegisterUser> registerUserService;
	
	int total;
	
	private String  name;
	List<FriendApply> rowsList;
	List<FriendApplyQueryView>  rowsListView = new ArrayList<FriendApplyQueryView>();
	@JSON(serialize=false)
	public FriendApply getModel() {
		return friendApply;
	}


	public void setFriendApplyService(IFriendApplyService<FriendApply> friendApplyService) {
		this.friendApplyService = friendApplyService;
	}

	@JSON(serialize=false)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}

	@JSON(name="rows")
	public List<FriendApply> getRowsList() {
		return rowsList;
	}


	public void setRowsList(List<FriendApply> rowsList) {
		this.rowsList = rowsList;
	}


	public String list() throws Exception
	{
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		String hql = "from FriendApply fa left join fetch fa.user1 where fa.user12.id = " + registerUserLogin.getId();
		rowsList = friendApplyService.list(hql, (page -1)*rows, rows, null);
		int count = 0;
		for (FriendApply fa: rowsList)
		{
			FriendApplyQueryView friendApplyQueryView = new FriendApplyQueryView(fa.getUser1().getUserName(), fa.getMessage());
			rowsListView.add(friendApplyQueryView);
			count ++;
		}
		rowsList = null;
		total = count;
		notificationService.clearNotification(registerUserLogin.getId(),1);
		return SUCCESS;
	}
	
	public String add() throws Exception
	{
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		friendApply.setUser1(registerUserLogin);
		String hql = "from RegisterUser r where r.userName = '" + friendApply.getUser12().getUserName() + "'";
		RegisterUser user12 = registerUserService.get(hql);
		//RegisterUser 
		friendApply.setUser12(user12);
		friendApply.setDateCreated(new Date());
		friendApplyService.add(friendApply, user12.getId());	
		return SUCCESS;
	}
	
	public String modify() throws Exception
	{	
		
		return SUCCESS;
	}
	
	public String delete() throws Exception
	{	
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		friendApply.setUser1(registerUserLogin);
		String hql = "from RegisterUser r where r.userName = '" + friendApply.getUser12().getUserName() + "'";
		RegisterUser user12 = registerUserService.get(hql);
		//RegisterUser 
		FriendApply  fa = friendApplyService.get(FriendApply.class,user12);
		friendApplyService.delete(fa);	
		return SUCCESS;
		/*FriendApply  fa = friendApplyService.get(FriendApply.class, friendApply.getUser12().getUserName());
		friendApplyService.delete(fa);
		return SUCCESS;*/
	}


	public void setRegisterUserService(
			IRegisterUserService<RegisterUser> registerUserService) {
		this.registerUserService = registerUserService;
	}

	@JSON(name="rowsView")
	public List<FriendApplyQueryView> getRowsListView() {
		return rowsListView;
	}


	public void setRowsListView(List<FriendApplyQueryView> rowsListView) {
		this.rowsListView = rowsListView;
	}


}
