package com.huawei.service.impl;

import java.util.List;

import org.hibernate.Query;

import com.huawei.domain.Notification;
import com.huawei.domain.WeiBo;
import com.huawei.service.INotificationService;
import com.huawei.service.IWeiBoService;

public class WeiBoServiceImpl<T extends WeiBo> extends ServiceImpl<T> implements IWeiBoService<T> {
	private INotificationService<Notification> notificationService;
	public void setNotificationService(
			INotificationService<Notification> notificationService) {
		this.notificationService = notificationService;
	}
	@Override
	public void increaseComtimes(WeiBo weibo) {
		// TODO Auto-generated method stub
		String hql = "update WeiBo w set w.comtimes=w.comtimes+1 where w.id="+weibo.getId()+"";
		Query query = dao.createQuery(hql);
		query.executeUpdate();
	}

	@Override
	public void increaseResendTimes(WeiBo weibo) {
		String hql = "update WeiBo w set w.resendtimes=w.resendtimes+1 where w.id="+weibo.getId()+"";
		Query query = dao.createQuery(hql);
		query.executeUpdate();		
	}
	@Override
	public void add(WeiBo weiBo,WeiBo oldWeiBo, WeiBo originalWeiBo,long[] friendIds,int type) {
		if(type==4||type==5){
	        dao.save((T) weiBo);
	        notificationService.addNotification(friendIds, type);
		}
		if(type==3){
			dao.update((T) weiBo);
			increaseResendTimes(oldWeiBo);
			if(originalWeiBo!=null){
			  increaseResendTimes(originalWeiBo);
			}
			notificationService.addNotification(friendIds, type);
		}
		
	}




}
