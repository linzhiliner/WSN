package com.huawei.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import com.huawei.action.domain.view.GroupMemberQueryView;
import com.huawei.domain.Group;
import com.huawei.domain.GroupInvatation;
import com.huawei.domain.GroupMember;
import com.huawei.domain.RegisterUser;
import com.huawei.service.IGroupInvatationService;
import com.huawei.service.IGroupService;
import com.huawei.service.IGroupMemberService;
import com.huawei.service.IRegisterUserService;
import com.opensymphony.xwork2.ModelDriven;

public class GroupMemberAction extends BaseAction implements ModelDriven<GroupMember>{

	private GroupMember groupMember = new GroupMember();
	private IGroupMemberService<GroupMember> groupMemberService;
	private IGroupService<Group> groupService;
	private IRegisterUserService<RegisterUser> registerUserService;
	private IGroupInvatationService<GroupInvatation> groupInvatationService;
	private int groupId;

	private String groupMemberId;
	int total;
	private String groupName;
	private int authority;
	private String  isOwner;
	List<GroupMember> rowsList;
	List<GroupMemberQueryView>  rowsListMember=new ArrayList<GroupMemberQueryView>();
	
	
	@JSON(serialize=false)
	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public void setRegisterUserService(
			IRegisterUserService<RegisterUser> registerUserService) {
		this.registerUserService = registerUserService;
	}

	public void setGroupInvatationService(
			IGroupInvatationService<GroupInvatation> groupInvatationService) {
		this.groupInvatationService = groupInvatationService;
	}

	
	public void setGroupService(IGroupService<Group> groupService) {
		this.groupService = groupService;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}



	public String getIsOwner() {
		return isOwner;
	}

	public void setIsOwner(String isOwner) {
		this.isOwner = isOwner;
	}

	@JSON(serialize=false)
	public String getGroupMemberId() {
		return groupMemberId;
	}

	public void setGroupMemberId(String groupMemberId) {
		this.groupMemberId = groupMemberId;
	}

	@JSON(name="rows")
	public List<GroupMember> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<GroupMember> rowsList) {
		this.rowsList = rowsList;
	}
	@JSON(serialize=false)
	public GroupMember getModel() {
		return groupMember;
	}


	public void setGroupMemberService(IGroupMemberService<GroupMember>  groupMemberService) {
		this.groupMemberService = groupMemberService;
	}

/*	public String list() throws Exception
	{
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		String hql = "from GroupMember gm left join fetch gm.user where gm.group.id = "+groupMember.getGroup().getId()+" ";
		rowsList= groupMemberService.list(hql,(page-1)*rows,rows,null);
		int count=0;
		for(GroupMember gm:rowsList){
		GroupMemberQueryView groupMemberQueryView = new GroupMemberQueryView(gm.getId(), gm.getUser().getId(), gm.getUser().getUserName(),gm.getUser().getMiniImgUrl());
		rowsListMember.add(groupMemberQueryView);
		count++;		
	}
		String hql1="from GroupMember gm  where gm.user.id= " + registerUserLogin.getId() + "and gm.group.id = "+groupMember.getGroup().getId()+"";
		GroupMember gm=groupMemberService.get(hql1);
	rowsList = null;
	total = count;

		return SUCCESS;
	}*/
	
	public String list() throws Exception
	{
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		String hql = "from GroupMember gm left join fetch gm.user where gm.group.id = "+groupMember.getGroup().getId()+" ";
		rowsList= groupMemberService.list(hql,(page-1)*rows,rows,null);
		int count=0;
		for(GroupMember gm:rowsList){
		GroupMemberQueryView groupMemberQueryView = new GroupMemberQueryView(gm.getUser().getId(), gm.getUser().getUserName(),gm.getUser().getMiniImgUrl());
		rowsListMember.add(groupMemberQueryView);
		count++;		
	    }
		String hql1="from Group g  where g.id = "+groupMember.getGroup().getId()+"";
		Group g=groupService.get(hql1);
		String hql2="from GroupMember gm  where gm.user.id = "+registerUserLogin.getId()+"";
		GroupMember gm=groupMemberService.get(hql2);
		String owner;
		if(registerUserLogin.getId()==g.getOwnerId().getId()){owner="true";}else {owner="false";}
	rowsList = null;
	total = count;
	authority=gm.getAuthority();
	groupName=g.getGroupName();
	isOwner=owner;
	//ownerId=g.getOwnerId().getId();
		return SUCCESS;
	}
	
	
	
	public String add() throws Exception
	{			
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		
		String[] groupMember1=null;
		if(groupMemberId!=null&&groupMemberId!=""){
			groupMember1=groupMemberId.split(" ");
		}
		if(groupMember1!=null&&groupMember1.length>0){
			for(int i=0;i<groupMember1.length;i++){
				String hql="select count(*) from GroupMember gm where gm.user.id="+groupMember1[i]+" and gm.group.id="+groupMember.getGroup().getId();
				total=groupMemberService.getTotalCount(hql, null);
				//System.out.print(total);
				if(total==0){
					RegisterUser ru=registerUserService.get(RegisterUser.class, Long.parseLong(groupMember1[i]));
					groupMember.setUser(ru);
					groupMember.setAuthority(1);
					groupMember.setDateCreated(new Date());
					groupMember.setModifyTime(new Date());
					groupMemberService.save(groupMember);
				}else{return "existed";}
			}}

		return SUCCESS;
	}
	
	public String modify() throws Exception
	{	
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		String hql="from GroupMember gm  where gm.user.id= " + registerUserLogin.getId() + "and gm.group.id = "+groupMember.getGroup().getId()+"";
		GroupMember gm=groupMemberService.get(hql);
		GroupMember groupMemberDataBase = groupMemberService.get(GroupMember.class, gm.getId());
    	//BeanUtils.copyProperties(groupMember, groupMemberDataBase,new String[]{"dateCreated"});
		if (groupMember.getAuthority()==1 ||groupMember.getAuthority()==2 ){
		groupMemberDataBase.setAuthority(groupMember.getAuthority());
		}else{return "error";}
		groupMemberDataBase.setModifyTime(new Date());  
		groupMemberService.update(groupMemberDataBase);
		return SUCCESS;
	}
	
	public String delete() throws Exception
	{	
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		String hql="from GroupMember gm  where gm.user.id= " + groupMember.getUser().getId()+ " and gm.group.id = "+groupMember.getGroup().getId()+"";
		GroupMember gm=groupMemberService.get(hql);
		
		groupMemberService.delete(GroupMember.class,gm.getId());
		
		return SUCCESS;
	}
	
	
	@JSON(name="rowsMember")
	public List<GroupMemberQueryView> getRowsListMember() {
		return rowsListMember;
	}

	public void setRowsListMember(List<GroupMemberQueryView> rowsListMember) {
		this.rowsListMember = rowsListMember;
	}
	
	

}
