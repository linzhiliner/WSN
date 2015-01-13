package com.huawei.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import com.huawei.domain.ReadMessage;
import com.huawei.domain.RegisterUser;
import com.huawei.domain.UnreadMessage;
import com.huawei.service.IReadMessageService;
import com.huawei.service.IRegisterUserService;
import com.huawei.service.IUnreadMessageService;
import com.huawei.service.impl.RegisterUserServiceImpl;
import com.opensymphony.xwork2.ModelDriven;

public class ReadMessageAction extends BaseAction implements ModelDriven<ReadMessage> {
	private IReadMessageService<ReadMessage> readMessageService;
	private IUnreadMessageService<UnreadMessage> unreadMessageService;
	private ReadMessage readMessage = new ReadMessage();
	int total;
	List<ReadMessage> rowsList;
	@JSON(serialize=false)
	public ReadMessage getModel() {
		// TODO Auto-generated method stub
		return readMessage;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(name="rows")
	public List<ReadMessage> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<ReadMessage> rowsList) {
		this.rowsList = rowsList;
	}

	public String list() throws Exception
	{
		String hql = "from ReadMessage rm order by rm.id desc";
		rowsList =readMessageService.list(hql, (page -1)*rows, rows, null);
		hql = "select count(*) from ReadMessage rm";
		total =readMessageService.getTotalCount(hql, null);
		return SUCCESS;
	}
	
	public String add() throws Exception{
		//等待list功能完成后添加，批量查询tb_unreadMessage,存入set，转移到tb_readMessage,同时批量删除tb_unreadMessage
		readMessage.setDateCreated(new Date());
		readMessageService.save(readMessage);
		return SUCCESS;
	}
	
	public String modify() throws Exception{
		
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		readMessageService.delete(readMessage);
		return SUCCESS;
	}

	public void setReadMessageService(
			IReadMessageService<ReadMessage> readMessageService) {
		this.readMessageService = readMessageService;
	}

	public void setUnreadMessageService(
			IUnreadMessageService<UnreadMessage> unreadMessageService) {
		this.unreadMessageService = unreadMessageService;
	}
	

}
