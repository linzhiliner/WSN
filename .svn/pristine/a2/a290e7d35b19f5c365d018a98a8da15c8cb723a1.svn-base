package com.huawei.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import com.huawei.domain.RegisterUser;
import com.huawei.domain.Tag;
import com.huawei.service.ITagService;
import com.huawei.utils.RandomArray;
import com.opensymphony.xwork2.ModelDriven;

import edu.emory.mathcs.backport.java.util.Arrays;

public class TagAction extends BaseAction implements ModelDriven<Tag> {
	private Tag tag = new Tag();
	private ITagService<Tag> tagService;
	int total;
	List<Tag> rowsList;
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(name="rows")
	public List<Tag> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<Tag> rowsList) {
		this.rowsList = rowsList;
	}

	@JSON(serialize=false)
	public Tag getModel() {
		// TODO Auto-generated method stub
		return tag;
	}
	
	public String list() throws Exception
	{
		if(tag.getRegisterUser().getId()==null){
			RegisterUser u = (RegisterUser)session.get("registerUserLogin");
			tag.setRegisterUser(u);
		}
		String hql = "from Tag t left join fetch t.registerUser r where r.id="+tag.getRegisterUser().getId()+" order by t.id desc";
		rowsList = tagService.list(hql, (page -1)*rows, rows, null);
		for(Tag t:rowsList){
			t.setRegisterUser(null);
		}
		hql = "select count(*) from Tag t where t.registerUser.id="+tag.getRegisterUser().getId()+"";
		total = tagService.getTotalCount(hql, null);
		return SUCCESS;
		
	}
	
	public String listRand(){
		RegisterUser u = (RegisterUser)session.get("registerUserLogin");
		long userTypeId = u.getUserType().getId();
		String hql = "from Tag t left join fetch t.registerUser u left join fetch t.registerUser.userType ut where ut.id="+userTypeId+" group by t.tagName  ";
		List<Tag> rowsTemp = tagService.list(hql);
		rowsList = new ArrayList<Tag>();
		int size = rowsTemp.size();
		if(size<=rows){
			total = size;
			rowsList = rowsTemp;
			rowsTemp=null;
		}else{
			int[] ids = RandomArray.getRandomArray(rows, size);
			
			for(int i=0;i<ids.length;i++){
				Tag tag = rowsTemp.get(ids[i]-1);
				rowsList.add(i, tag);
				
			}
			rowsTemp=null;
			total=rows;
		}
		for(int i=0;i<rowsList.size();i++){
			rowsList.get(i).setRegisterUser(null);
		}
		return SUCCESS;
	}
	
	public String add() throws Exception
	{
		
		RegisterUser u = (RegisterUser)session.get("registerUserLogin");
		tag.setRegisterUser(u);
		tag.setDateCreated(new Date());
		tagService.save(tag);
		return SUCCESS;
	}
	
	public String modify() throws Exception
	{
		Tag tagInDataBase = tagService.get(Tag.class, tag.getId());
		tag.setRegisterUser(tagInDataBase.getRegisterUser());
		BeanUtils.copyProperties(tag, tagInDataBase,new String[]{"dateCreated"});
		tagService.update(tagInDataBase);
		return SUCCESS;
	}
	
	public String delete() throws Exception
	{
		Tag tagInDataBase = tagService.get(Tag.class, tag.getId());
		tagService.delete(tagInDataBase);
		return SUCCESS;
	}

	public void setTagService(ITagService<Tag> tagService) {
		this.tagService = tagService;
	}

}
