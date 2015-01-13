package com.huawei.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;

import javassist.expr.NewArray;

import com.huawei.action.domain.view.UnreadMessageQueryView;
import com.huawei.domain.GroupMember;
import com.huawei.domain.RegisterUser;
import com.huawei.domain.UnreadMessage;
import com.huawei.service.IGroupMemberService;
import com.huawei.service.IRegisterUserService;
import com.huawei.service.IUnreadMessageService;
import com.huawei.service.impl.RegisterUserServiceImpl;
import com.huawei.utils.DateTimeUtils;
import com.huawei.utils.Upload;
import com.opensymphony.xwork2.ModelDriven;

public class UnreadMessageAction extends BaseAction implements ModelDriven<UnreadMessage> {
	private IUnreadMessageService<UnreadMessage> unreadMessageService;
	private IRegisterUserService<RegisterUser>  registerUserService;
	private IGroupMemberService<GroupMember> groupMemberService;
	private UnreadMessage unreadMessage = new UnreadMessage();
	
	public void setGroupMemberService(
			IGroupMemberService<GroupMember> groupMemberService) {
		this.groupMemberService = groupMemberService;
	}

	int total;
	List<UnreadMessageQueryView> rowsListView = new ArrayList<UnreadMessageQueryView>();
	List<UnreadMessage> rowsList;

	public void setRegisterUserService(
			IRegisterUserService<RegisterUser> registerUserService) {
		this.registerUserService = registerUserService;
	}

	@JSON(serialize=false)
	public UnreadMessage getModel() {
		// TODO Auto-generated method stub
		return unreadMessage;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(name="rows")
	public List<UnreadMessage> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<UnreadMessage> rowsList) {
		this.rowsList = rowsList;
	}

	@JSON(name="rowsView")
	public List<UnreadMessageQueryView> getRowsListView() {
		return rowsListView;
	}

	public void setRowsListView(List<UnreadMessageQueryView> rowsListView) {
		this.rowsListView = rowsListView;
	}

	public String list() throws Exception
	{
		//1����ȡ��������msgId,�������û���������ԭ��û���յ����������ڸ����ֻ���ж�������ԭ�򴫵�Id��С�ڷ�������msgId,�Է�����Ϊ׼��
		String hql = "from RegisterUser r where r.id="+unreadMessage.getDesUser().getId()+"";
		RegisterUser registerUser = registerUserService.get(hql);
		long msgId = registerUser.getMsgId();
		if(msgId<unreadMessage.getId()){
			msgId = unreadMessage.getId();
			registerUser.setMsgId(msgId);
			registerUserService.update(registerUser);
		}
		//2�����Ѷ�����ͨ������Ϣ���������ñ����м��ɾ��
		hql ="select count(*) from UnreadMessage u where u.id<="+msgId+" and u.desUser.id="+unreadMessage.getDesUser().getId()+" and u.group is null ";
		total = unreadMessageService.getTotalCount(hql, null);
		if(total>0){
			 unreadMessageService.saveAndDelete(unreadMessage,msgId);   
		}
		//3����δ����Ϣ������ǰ��
		hql = "select count(*) from UnreadMessage u  where u.id>"+msgId+" and (u.desUser.id="+unreadMessage.getDesUser().getId()+" or u.desUser.id is null)";
		total = unreadMessageService.getTotalCount(hql, null);
		if(total>0){
		hql="from UnreadMessage u  left join fetch u.srcUser left join fetch u.group g  where u.id>"+msgId+" and (u.desUser.id="+unreadMessage.getDesUser().getId()+" or u.desUser.id is null)";
		rowsList = unreadMessageService.list(hql);
		for(int i=0;i<rowsList.size();i++){
			UnreadMessage u = (UnreadMessage)rowsList.get(i);
			UnreadMessageQueryView uv = new UnreadMessageQueryView();
			if(u.getGroup()==null){                                 //��ͨ����
				uv.setUserId(u.getSrcUser().getId());
				uv.setInfo(u.getInfo());
				uv.setMsgId(u.getId());
				uv.setSendTime(u.getDateCreated());
				uv.setUserName(u.getSrcUser().getUserName());	
				uv.setMinUrl(u.getMinUrl());
				uv.setMessageType(u.getMessageType());
			}else{													//Ⱥ������
				uv.setGroupId(u.getGroup().getId());
				
				hql = "from GroupMember gm where gm.user.id="+unreadMessage.getDesUser().getId()+" and gm.group.id="+u.getGroup().getId()+" and gm.authority=1";
				GroupMember groupMember = groupMemberService.get(hql);
				if(groupMember==null){
					total--;
					continue;
				}
				Date joinTime = groupMember.getDateCreated();   //����ʱ��>����ʱ��Ż����
				Date modifeTime = groupMember.getModifyTime();  //����ʱ��>�޸�ʱ��Ż���Ч
				if(joinTime.after(u.getDateCreated())||modifeTime.after(u.getDateCreated())||groupMember.getUser().getId()==u.getSrcUser().getId()) {
					total--;
					continue;	
				}
				uv.setUserId(u.getSrcUser().getId());
				uv.setUserName(u.getSrcUser().getUserName());
				uv.setInfo(u.getInfo());
				uv.setMsgId(u.getId());
				uv.setSendTime(u.getDateCreated());
				uv.setGroupName(u.getGroup().getGroupName());
				uv.setMinUrl(u.getMinUrl());
				uv.setMessageType(u.getMessageType());
				
			}
			rowsListView.add(uv);
		}
//		registerUser.setMsgId(rowsList.get(rowsList.size()-1).getId());
//		registerUserService.update(registerUser);
		}
		rowsList=null;
		
		return SUCCESS;
	}
	
	public String add() throws Exception{	
		if(unreadMessage.getGroup()!=null){
			String hql = "from GroupMember gm where gm.group.id="+unreadMessage.getGroup().getId()+" and gm.user.id="+unreadMessage.getSrcUser().getId()+"";
			GroupMember groupMember = groupMemberService.get(hql);
			if(groupMember==null)
				return "NotIn";
		}
		unreadMessage.setDateCreated(new Date());
		String url="";                                        //ͼƬ�������洢��ַ
		String urlMin="";									  //����ͼ�洢��ַ
		String fileName ="";								 //�ļ���=ʱ���+������id+�ļ�����
		
		if(unreadMessage.getMessageType()==1){
			fileName = DateTimeUtils.getTimeStamp() + unreadMessage.getSrcUser().getId()+getFileFileName().substring(getFileFileName().lastIndexOf("."));
			url = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("")+File.separator+Upload.IMAGE_URL+fileName;
			urlMin = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("")+File.separator+Upload.MINIMAGE_URL+fileName;
			unreadMessage.setInfo(Upload.IMAGE_URL+fileName);
			unreadMessage.setMinUrl(Upload.MINIMAGE_URL+fileName);
			Upload.save(url, getFile());
			Thumbnails.of(url).size(50,50).toFile(urlMin); 
		//	Thumbnails.of(url).size(50,50).keepAspectRatio(false).toFile(urlMin); 
		}else if(unreadMessage.getMessageType()==2){
			fileName = DateTimeUtils.getTimeStamp() + unreadMessage.getSrcUser().getId()+getFileFileName().substring(getFileFileName().lastIndexOf("."));
			url = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("")+File.separator+Upload.VOICE_URL+fileName;
			unreadMessage.setMinUrl(Upload.VOICE_URL+fileName);
			Upload.save(url, getFile());
		}else if(unreadMessage.getMessageType()==3){
			fileName = DateTimeUtils.getTimeStamp() + unreadMessage.getSrcUser().getId()+getFileFileName().substring(getFileFileName().lastIndexOf("."));
			urlMin = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("")+File.separator+Upload.MINIMAGE_URL+fileName;
			unreadMessage.setMinUrl(Upload.MINIMAGE_URL+fileName);
			Upload.save(urlMin, getFile());
			Thumbnails.of(urlMin).size(160,200).toFile(urlMin); 
		}
		unreadMessageService.save(unreadMessage);
		return SUCCESS;
	}
	
	public String modify() throws Exception{
		
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		return SUCCESS;
	}

	public void setUnreadMessageService(
			IUnreadMessageService<UnreadMessage> unreadMessageService) {
		this.unreadMessageService = unreadMessageService;
	}
	
	

}
