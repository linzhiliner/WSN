package com.huawei.service;

import com.huawei.domain.Notification;

public interface INotificationService<T extends Notification> extends IService<T> {
	void addNotification(long[] ids,int noticeType);
	
	void clearNotification(long registerUserId,int noticeType);
}
