package com.huawei.service.impl;

import com.huawei.domain.FriendApply;
import com.huawei.domain.Notification;
import com.huawei.service.IFriendApplyService;
import com.huawei.service.INotificationService;

public class FriendApplyServiceImpl <T extends FriendApply> extends ServiceImpl<T> implements IFriendApplyService<T> {
	private INotificationService<Notification> notificationService;
	public void setNotificationService(
			INotificationService<Notification> notificationService) {
		this.notificationService = notificationService;
	}
	@Override
	public void add(FriendApply friendApply, long registerUserId) {
		// TODO Auto-generated method stub
		dao.save((T) friendApply);
		notificationService.addNotification(new long[]{registerUserId}, 1);
	}

}
