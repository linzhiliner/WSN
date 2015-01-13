package com.huawei.service.impl;

import com.huawei.domain.Notification;
import com.huawei.service.INotificationService;

public class NotificationServiceImpl<T extends Notification> extends ServiceImpl<T> implements INotificationService<T> {

	@Override
	public void addNotification(long[] ids, int noticeType) {
		// TODO Auto-generated method stub
		for(int i=0;i<ids.length;i++){
		String hql = "from Notification nt where nt.registerUser.id="+ids[i]+"";
		Notification nt = dao.get(hql);
		switch(noticeType){
			case 1:
				nt.setNewFriends(nt.getNewFriends()+1);
				break;
			case 2:
				nt.setComments(nt.getComments()+1);
				break;
			case 3:
				nt.setTransmits(nt.getTransmits()+1);
				break;
			case 4:
				nt.setWeiBos(nt.getWeiBos()+1);
				break;
			case 5:
				nt.setShops(nt.getShops()+1);
				break;
			case 6:
				nt.setShopComments(nt.getShopComments()+1);
				break;
			default:
				break;
			
		}
		dao.update((T) nt);
		}
		
	}

	@Override
	public void clearNotification(long registerUserId, int noticeType) {
		String hql = "from Notification nt where nt.registerUser.id="+registerUserId+"";
		Notification nt = dao.get(hql);
		switch(noticeType){
			case 1:
				nt.setNewFriends(0);
				break;
			case 2:
				nt.setComments(0);
				break;
			case 3:
				nt.setTransmits(0);
				break;
			case 4:
				nt.setWeiBos(0);
				break;
			case 5:
				nt.setShops(0);
				break;
			case 6:
				nt.setShopComments(0);
				break;
			default:
				break;
			
		}
		dao.update((T) nt);
		
	}

}
