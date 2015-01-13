package com.huawei.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import com.huawei.domain.Notification;
import com.huawei.domain.RegisterUser;
import com.huawei.service.INotificationService;
import com.opensymphony.xwork2.ModelDriven;

public class NotificationAction extends BaseAction implements ModelDriven<Notification> {
	private Notification notification = new Notification();
	private INotificationService<Notification> notificationService;
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
	@JSON(name="rows")
	public List<Notification> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<Notification> rowsList) {
		this.rowsList = rowsList;
	}

	public void setNotificationService(
			INotificationService<Notification> notificationService) {
		this.notificationService = notificationService;
	}

	int total;
	List<Notification> rowsList;

	@JSON(serialize=false)
	public Notification getModel() {
		// TODO Auto-generated method stub
		return notification;
	}
	
	public String list(){
		RegisterUser u=(RegisterUser) session.get("registerUserLogin");
		String hql = "from Notification nt where nt.registerUser.id="+u.getId()+"";
		rowsList = notificationService.list(hql);
		Notification notification = rowsList.get(0);
		total = notification.getComments()+notification.getNewFriends()+notification.getShopComments()+notification.getShops()+notification.getTransmits()+notification.getWeiBos();
		notification.setRegisterUser(null);

		return SUCCESS;
	}
	

	
	public String delete(){
		RegisterUser u=(RegisterUser) session.get("registerUserLogin");
		String hql = "from Notification nt where nt.registerUser.id="+u.getId()+"";
		Notification nt = notificationService.get(hql);
		if(notification.getComments()!=null){
			nt.setComments(0);
		}
		if(notification.getNewFriends()!=null){
			nt.setNewFriends(0);
		}
		if(notification.getShops()!=null){
			nt.setShops(0);
		}
		if(notification.getTransmits()!=null){
			nt.setTransmits(0);
		}
		if(notification.getWeiBos()!=null){
			nt.setWeiBos(0);
		}
		if(notification.getShopComments()!=null){
			nt.setShopComments(0);
		}
		notificationService.update(nt);
		return SUCCESS;
	}

	
}
