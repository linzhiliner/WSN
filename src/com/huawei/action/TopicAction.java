package com.huawei.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import com.huawei.domain.Topic;
import com.huawei.service.ITopicService;
import com.opensymphony.xwork2.ModelDriven;

public class TopicAction extends BaseAction implements ModelDriven<Topic> {

	ITopicService<Topic> topicService;
	Topic topic = new Topic();
	
	int total;
	List<Topic> rowsList;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@JSON(name="rows")
	public List<Topic> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<Topic> rowsList) {
		this.rowsList = rowsList;
	}

	@JSON(serialize=false)
	public Topic getModel() {
		// TODO Auto-generated method stub
		return topic;
	}

	public String list() throws Exception
	{
		String hql = "from Topic t left join fetch t.user order by t.id desc";
		rowsList = topicService.list(hql,(page-1)*rows,rows,null);
		hql = "select count(*) from Topic t";
		total = topicService.getTotalCount(hql, null);
		return SUCCESS;
	}
	
	public String add() throws Exception{
		topic.setDateCreated(new Date());
		topicService.save(topic);
		return SUCCESS;
	}
	
	public String modify() throws Exception{
		Topic topicInDataBase = topicService.get(Topic.class, topic.getId());
		BeanUtils.copyProperties(topic,topicInDataBase,new String[]{"dateCreated"});
		topicService.update(topicInDataBase);
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		topicService.delete(topic);
		return SUCCESS;
	}

	public void setTopicService(ITopicService<Topic> topicService) {
		this.topicService = topicService;
	}
	
	

}
