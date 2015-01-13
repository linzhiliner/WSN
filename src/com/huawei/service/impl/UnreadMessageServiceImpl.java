package com.huawei.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.huawei.domain.ReadMessage;
import com.huawei.domain.UnreadMessage;
import com.huawei.service.IReadMessageService;
import com.huawei.service.IUnreadMessageService;

public class UnreadMessageServiceImpl<T extends UnreadMessage> extends ServiceImpl<T> implements
		IUnreadMessageService<T> {

	private IReadMessageService<ReadMessage> readMessageService;
	public void setReadMessageService(
			IReadMessageService<ReadMessage> readMessageService) {
		this.readMessageService = readMessageService;
	}
	@Override
	public void  saveAndDelete(UnreadMessage unreadMessage,long msgId) {
	String 	hql ="from UnreadMessage u where u.id<="+msgId+" and u.desUser.id="+unreadMessage.getDesUser().getId()+" and u.group is null";
	List<UnreadMessage> rowsList = (List<UnreadMessage>) list(hql);
		for(int i=0;i<rowsList.size();i++){
			UnreadMessage um = (UnreadMessage)rowsList.get(i);
			delete((T) um);
		}	
		for(int i=0;i<rowsList.size();i++){
			UnreadMessage um = (UnreadMessage)rowsList.get(i);
			ReadMessage  r = new ReadMessage();
			BeanUtils.copyProperties(um, r);
			readMessageService.save(r);
		}
	}

}
