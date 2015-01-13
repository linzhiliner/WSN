package com.huawei.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class Topic extends BaseDomainObject implements Serializable {
	private static final long serialVersionUID = 48L;
	private RegisterUser user;
	private String topic;
	
	private Set<TopicInfo> topicInfos = new HashSet<TopicInfo>();
	
	
	@JSON(serialize=false)
	public Set<TopicInfo> getTopicInfos() {
		return topicInfos;
	}
	public void setTopicInfos(Set<TopicInfo> topicInfos) {
		this.topicInfos = topicInfos;
	}
	public RegisterUser getUser() {
		return user;
	}
	public void setUser(RegisterUser user) {
		this.user = user;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	
}
