package com.huawei.action.domain.view;

import java.util.ArrayList;
import java.util.List;

import oracle.net.aso.l;

import org.apache.struts2.json.annotations.JSON;

import com.huawei.domain.Tag;

public class SearchConnectionsView {
	
	
	public SearchConnectionsView(long userId,String userName,String miniImgUrl){
		super();
		members = new ArrayList<Person>();
		num = 0;
		person = new Person(userId,userName,miniImgUrl);
	}

	public class Person{
		private String userName;
		private Long userId;
		private String miniImgUrl;
		
		public Person(){
			super();
		}
		
		public Person(long userId,String userName,String miniImgUrl){
			super();
			this.userName = userName;
			this.userId = userId;
			this.miniImgUrl = miniImgUrl;
		}
		

		

		public String getMiniImgUrl() {
			return miniImgUrl;
		}

		public String getUserName() {
			return userName;
		}
//		public void setUserName(String userName) {
//			this.userName = userName;
//		}
		public Long getUserId() {
			return userId;
		}
//		public void setUserId(Long userId) {
//			this.userId = userId;
//		}
		
	}
	
	public class TagsFromPeople{
		private String tagName;
		private Long tagId;
		
		public String getTagName() {
			return tagName;
		}
		public Long getTagId() {
			return tagId;
		}
		
		
	}
	

	private List<Person> members;
	private int num;
	private List<TagsFromPeople> tags;
	private Person person;
	
	
	

	public List<TagsFromPeople> getTags() {
		return tags;
	}
	
	@JSON(serialize=false)
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<Person> getMembers() {
		return members;
	}
	public void setMembers(List<Person> members) {
		this.members = members;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
//	public void add(Person person){
//		members.add(person);
//		num ++;		
//	}
	
	public void add(SearchConnectionsView searchConnectionsView){
		members.addAll(searchConnectionsView.members);
		num=searchConnectionsView.num;
		members.add(person);
		num++;
	}
	
	public void setTags(List<Tag> tagFromDb) {
		tags = new ArrayList<TagsFromPeople>();
		TagsFromPeople tagsFromPeople;
		
		for(Tag tag : tagFromDb){
			tagsFromPeople = new TagsFromPeople();
			tagsFromPeople.tagId = tag.getId();
			tagsFromPeople.tagName = tag.getTagName();
			tags.add(tagsFromPeople);
		}
	}
	
	public void clearTags(){
		tags = null;
	}
}
