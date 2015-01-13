package com.huawei.action;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import com.huawei.action.domain.view.GroupMemberQueryView;
import com.huawei.action.domain.view.GroupQueryView;
import com.huawei.domain.Group;
import com.huawei.domain.GroupInvatation;
import com.huawei.domain.GroupMember;
import com.huawei.domain.RegisterUser;
import com.huawei.service.IGroupInvatationService;
import com.huawei.service.IGroupMemberService;
import com.huawei.service.IGroupService;
import com.huawei.service.IRegisterUserService;
import com.opensymphony.xwork2.ModelDriven;

public class GroupAction extends BaseAction implements ModelDriven<Group>{
	private Group group = new Group();
	private IGroupService<Group> groupService;
	private IRegisterUserService<RegisterUser> registerUserService;
	private IGroupMemberService<GroupMember> groupMemberService;
	private IGroupInvatationService<GroupInvatation> groupInvatationService;
	int total;
	List<Group> rowsList;
	private long userId;
	private String groupMemberId;
	//List<GroupInvatation> invatations;
	List<GroupQueryView>  rowsListMember=new ArrayList<GroupQueryView>();

	
	
		
	public void setGroupInvatationService(
			IGroupInvatationService<GroupInvatation> groupInvatationService) {
		this.groupInvatationService = groupInvatationService;
	}

	
	public void setRegisterUserService(
			IRegisterUserService<RegisterUser> registerUserService) {
		this.registerUserService = registerUserService;
	}

	@JSON(name="rowsMember")

	public List<GroupQueryView> getRowsListMember() {
		return rowsListMember;
	}
	
	public void setRowsListMember(List<GroupQueryView> rowsListMember) {
		this.rowsListMember = rowsListMember;
	}






	@JSON(serialize=false)
	public IGroupMemberService<GroupMember> getGroupMemberService() {
		return groupMemberService;
	}


/*	public List<GroupInvatation> getInvatations() {
		return invatations;
	}

	public void setInvatations(List<GroupInvatation> invatations) {
		this.invatations = invatations;
	}*/

	@JSON(serialize=false)
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@JSON(serialize=false)
	public String getGroupMemberId() {
		return groupMemberId;
	}

	public void setGroupMemberId(String groupMemberId) {
		this.groupMemberId = groupMemberId;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@JSON(name="rows")
	public List<Group> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<Group> rowsList) {
		this.rowsList = rowsList;
	}

	@JSON(serialize=false)
	public Group getModel() {
		return group;
	}

	public void setGroupService(IGroupService<Group>  groupService) {
		this.groupService = groupService;
	}

	
	
	public void setGroupMemberService(
			IGroupMemberService<GroupMember> groupMemberService) {
		this.groupMemberService = groupMemberService;
	}
	public String detail() throws Exception{
		String hql = "from Group g where g.id = "+group.getId();
		rowsList = groupService.list(hql,(page-1)*rows,rows,null);
		total = 1;
		return SUCCESS;
	}

	public String list() throws Exception
	{
		
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		String hql = "from GroupMember gm  where gm.user.id = "+registerUserLogin.getId()+" ";
		List<GroupMember> result= groupMemberService.list(hql,(page-1)*rows,rows,null);
		int count=0;
		for(GroupMember gm:result){
		String  hql1="from Group g where g.id="+gm.getGroup().getId()+"";
		Group g =groupService.get(hql1);
		GroupQueryView groupQueryView = new GroupQueryView(g.getId(), g.getGroupName(),gm.getAuthority(),registerUserLogin.getMiniImgUrl());
		rowsListMember.add(groupQueryView);
		count++;		
	}
	rowsList = null;
	total = count;
		//hql = "select count(*) from GroupMember gm where gm.group.id = "+groupId;
		//total = groupMemberService.getTotalCount(hql, null);
		return SUCCESS;
	}
	
	/*public String search() throws Exception{
		 List<GroupInvatation> groupInvatations;
		
		String hql = "from Group g where g.intro like '%"+group.getIntro()+"%' order by g.id desc";
		rowsList = groupService.list(hql,(page-1)*rows,rows,null);
		hql = "select count(*) from Group g where g.intro like '%"+group.getIntro()+"%'";
		total = groupService.getTotalCount(hql, null);
		invatations = new ArrayList<GroupInvatation>();
		for(Group group:rowsList){
			hql = "from GroupInvatation gi where gi.user12.id = "+ userId +" and gi.circle.id = " +group.getId()+" and gi.type="+1;
			groupInvatations = groupInvatationService.list(hql,(page-1)*rows,rows,null);
			for(GroupInvatation groupInvatation:groupInvatations){
				invatations.add(groupInvatation);
			}
			hql = "from GroupInvatation gi where gi.user1.id = "+ userId +" and gi.circle.id = " +group.getId()+" and gi.type="+0;
			groupInvatations = groupInvatationService.list(hql,(page-1)*rows,rows,null);
			for(GroupInvatation groupInvatation:groupInvatations){
				invatations.add(groupInvatation);
			}
		}
		
		return SUCCESS;
	}
	*/
	public String add() throws Exception
	{
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		group.setOwnerId(registerUserLogin);
	    group.setGroupName(group.getOwnerId().getUserName()+"µƒ¡ƒÃÏ “");
		group.setDateCreated(new Date());
		groupService.save(group);
		session.put("Id", group.getId());
		session.put("groupName", group.getGroupName());
		String[] groupMember1=null;
		if(groupMemberId!=null&&groupMemberId!=""){
			groupMember1=groupMemberId.split(" ");
		}
		
		GroupMember groupMember = new GroupMember();		
		groupMember.setUser(registerUserLogin);
     	//groupMember.getMembers().add(registerUserLogin);
		groupMember.setAuthority(1);
		groupMember.setDateCreated(group.getDateCreated());
		groupMember.setModifyTime(new Date());
		groupMember.setGroup(group);
		groupMemberService.save(groupMember);
		for(int i=0;i<groupMember1.length;i++){
			RegisterUser ru=registerUserService.get(RegisterUser.class, Long.parseLong(groupMember1[i]));
			groupMember.setUser(ru);
			groupMember.setModifyTime(new Date());
//			groupMember.getMembers().add(ru);
			groupMember.setAuthority(1);
			groupMember.setGroup(group);
			groupMemberService.save(groupMember);
		}
//		groupMemberService.save(groupMember);
		return SUCCESS;
	}
	
	public String modify() throws Exception
	{	
		Group groupDataBase = groupService.get(Group.class, group.getId());
		BeanUtils.copyProperties(group, groupDataBase,new String[]{"dateCreated","ownerId"});
		groupService.update(groupDataBase);
		return SUCCESS;
	}
	
	public String delete() throws Exception
	{	
		groupService.delete(Group.class, group.getId());
		return SUCCESS;
	}


}
