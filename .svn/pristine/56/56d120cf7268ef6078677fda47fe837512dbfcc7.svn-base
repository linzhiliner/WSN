package com.huawei.service.impl;

import com.huawei.domain.Comment;
import com.huawei.domain.Notification;
import com.huawei.domain.WeiBo;
import com.huawei.service.ICommentService;
import com.huawei.service.INotificationService;
import com.huawei.service.IWeiBoService;

public class CommentServiceImpl <T extends Comment> extends ServiceImpl<T> implements ICommentService<T>{
	private IWeiBoService<WeiBo> weiBoService;
	private INotificationService<Notification> notificationService;
	public void setNotificationService(
			INotificationService<Notification> notificationService) {
		this.notificationService = notificationService;
	}
	public void setWeiBoService(IWeiBoService<WeiBo> weiBoService) {
		this.weiBoService = weiBoService;
	}
	@Override
	public void addComment(Comment comment,long registerUserId) {
		dao.save((T) comment);                              //�������
		weiBoService.increaseComtimes(comment.getWeibo());  //���۴����1
		notificationService.addNotification(new long[]{registerUserId}, 2);
	}
	@Override
	public void addComment(Comment comment, long[] ids) {
		dao.save((T) comment);                              //�������
		weiBoService.increaseComtimes(comment.getWeibo());  //���۴����1
		if(ids!=null){
			notificationService.addNotification(ids, 2);
		}
		
		
	}
	
}