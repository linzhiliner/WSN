package com.huawei.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;

import javassist.expr.NewArray;

import com.huawei.domain.GroupInvatation;
import com.huawei.domain.GroupMember;
import com.huawei.domain.RegisterUser;
import com.huawei.service.IGroupInvatationService;
import com.huawei.service.IGroupMemberService;
import com.opensymphony.xwork2.ModelDriven;

public class GroupInvatationAction extends BaseAction implements ModelDriven<GroupInvatation> {

	private IGroupInvatationService<GroupInvatation> groupInvatationService;
	private GroupInvatation groupInvatation = new GroupInvatation();
	private IGroupMemberService<GroupMember> groupMemberService;
	
	
	public void setGroupMemberService(
			IGroupMemberService<GroupMember> groupMemberService) {
		this.groupMemberService = groupMemberService;
	}

	int total;
	List<GroupInvatation> rowsList;
	
	private int userId;
	
	@JSON(serialize=false)
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@JSON(name="rows")
	public List<GroupInvatation> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<GroupInvatation> rowsList) {
		this.rowsList = rowsList;
	}

	@JSON(serialize=false)
	public GroupInvatation getModel() {
		// TODO Auto-generated method stub
		return groupInvatation;
	}

	public String list() throws Exception
	{
		String hql = "from GroupInvatation gi left join fetch gi.user1 left join fetch gi.user12 where gi.user12.id ="+userId+" order by gi.id desc";
		rowsList = groupInvatationService.list(hql,(page-1)*rows,rows,null);
		hql = "select count(*) from GroupInvatation gi where gi.user12.id = "+userId;
		total = groupInvatationService.getTotalCount(hql, null);
		return SUCCESS;
	}
	
	public String add() throws Exception{
		//ÑûÇë»¹Î´ÊµÏÖ
		groupInvatation.setDateCreated(new Date());
		groupInvatation.setUser12(new RegisterUser());
		Long user12 = getAuthorityOwner(groupInvatation.getCircle().getId());
		groupInvatation.getUser12().setId(user12);
		groupInvatationService.save(groupInvatation);
		return SUCCESS;
	}
	
	public Long getAuthorityOwner(long groupId){
		String hql = "from GroupMember gm where gm.group.id="+groupId+" and gm.authority=0";
		List<GroupMember> gMembers;
		gMembers = groupMemberService.list(hql,(page-1)*rows,rows,null);
		long ownerId = 0;
		for(GroupMember groupMember : gMembers){
			ownerId = groupMember.getUser().getId();
		}
		return ownerId;
	}
	
	public String modify() throws Exception{
		
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		GroupInvatation g = groupInvatationService.get(GroupInvatation.class, groupInvatation.getId());
		groupInvatationService.delete(g);
		return SUCCESS;
	}

	public void setGroupInvatationService(
			IGroupInvatationService<GroupInvatation> groupInvatationService) {
		this.groupInvatationService = groupInvatationService;
	}

	
	
}
