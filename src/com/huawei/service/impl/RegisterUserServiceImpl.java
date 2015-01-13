package com.huawei.service.impl;

import java.util.Date;

import com.huawei.domain.Notification;
import com.huawei.domain.RegisterUser;
import com.huawei.domain.ShopInfo;
import com.huawei.service.INotificationService;
import com.huawei.service.IRegisterUserService;
import com.huawei.service.IShopInfoService;

public class RegisterUserServiceImpl <T extends RegisterUser> extends ServiceImpl<T> implements
		IRegisterUserService<T> {
	private INotificationService<Notification> notificationService;
	private IShopInfoService<ShopInfo> shopInfoService;
	
	public void setShopInfoService(IShopInfoService<ShopInfo> shopInfoService) {
		this.shopInfoService = shopInfoService;
	}
	
	@Override
	public void addUser(RegisterUser u) {
		// TODO Auto-generated method stub
		notificationService.save(createNotification(u));
		dao.save((T) u);
		
	}
	public void setNotificationService(
			INotificationService<Notification> notificationService) {
		this.notificationService = notificationService;
	}
	@Override
	public void addShop(RegisterUser u) {
		// TODO Auto-generated method stub
		ShopInfo shopInfo = new ShopInfo();
		shopInfo.setRegisterUser(u);
		shopInfoService.save(shopInfo); 	//增加商铺
		notificationService.save(createNotification(u));
		dao.save((T) u);
	}
	
	Notification createNotification(RegisterUser u){
		Notification ntCreate = new Notification();    //创建提醒
		ntCreate.setComments(0);
		ntCreate.setShopComments(0);
		ntCreate.setDateCreated(new Date());
		ntCreate.setNewFriends(0);
		ntCreate.setRegisterUser(u);
		ntCreate.setShops(0);
		ntCreate.setTransmits(0);
		ntCreate.setWeiBos(0);
		return ntCreate;
		
	}
	
	

}
