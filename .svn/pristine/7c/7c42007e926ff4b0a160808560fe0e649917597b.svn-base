package com.huawei.service.impl;

import com.huawei.domain.Notification;
import com.huawei.domain.ShopComment;
import com.huawei.domain.ShopInfo;
import com.huawei.service.INotificationService;
import com.huawei.service.IShopCommentService;

public class ShopCommentServiceImpl <T extends ShopComment> extends ServiceImpl<T> implements IShopCommentService<T>{
	private INotificationService<Notification> notificationService;
	public void setNotificationService(
			INotificationService<Notification> notificationService) {
		this.notificationService = notificationService;
	}
	@Override
	public void add(ShopComment shopComment,long sellerId) {
		// TODO Auto-generated method stub
		dao.save((T) shopComment);
		notificationService.addNotification( new long[]{sellerId}, 6);
	}
	
}