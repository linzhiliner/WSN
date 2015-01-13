package com.huawei.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import javassist.expr.NewArray;


import com.huawei.domain.TopicInfo;
import com.huawei.service.ITopicInfoService;
import com.opensymphony.xwork2.ModelDriven;

public class TopicInfoAction extends BaseAction implements ModelDriven<TopicInfo> {

	ITopicInfoService<TopicInfo> topicInfoService;
	TopicInfo topicInfo = new TopicInfo();
	
	int total;
	List<TopicInfo> rowsList;
	
	
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@JSON(name="rows")
	public List<TopicInfo> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<TopicInfo> rowsList) {
		this.rowsList = rowsList;
	}

	@JSON(serialize=false)
	public TopicInfo getModel() {
		// TODO Auto-generated method stub
		return topicInfo;
	}
	
	public String list() throws Exception
	{
		String hql = "from TopicInfo ti left join fetch ti.user order by ti.id desc";
		rowsList = topicInfoService.list(hql,(page-1)*rows,rows,null);;
		hql = "select count(*) from TopicInfo t";
		total = topicInfoService.getTotalCount(hql, null);
		return SUCCESS;
	}
	
	public String add() throws Exception{
		topicInfo.setDateCreated(new Date());
		topicInfoService.save(topicInfo);
		return SUCCESS;
	}
	
	public String modify() throws Exception{
		TopicInfo topicInfoInDataBase = topicInfoService.get(TopicInfo.class, topicInfo.getId());
		BeanUtils.copyProperties(topicInfo,topicInfoInDataBase,new String[]{"dateCreated"});
		topicInfoService.update(topicInfoInDataBase);
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		topicInfoService.delete(topicInfo);
		return SUCCESS;
	}

	
	public void setTopicInfoService(ITopicInfoService<TopicInfo> topicInfoService) {
		this.topicInfoService = topicInfoService;
	}

	

	

}
