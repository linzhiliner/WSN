package com.huawei.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import oracle.net.aso.f;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import com.huawei.action.domain.view.Friend1QueryView;
import com.huawei.action.domain.view.FriendDetailQueryView;
import com.huawei.action.domain.view.FriendQueryView;
import com.huawei.action.domain.view.PhoneFriend;
import com.huawei.action.domain.view.SearchConnectionsView;
import com.huawei.action.domain.view.SearchConnectionsView.Person;
import com.huawei.domain.Friend;
import com.huawei.domain.FriendApply;
import com.huawei.domain.RegisterUser;
import com.huawei.domain.Tag;
import com.huawei.service.IFriendApplyService;
import com.huawei.service.IFriendService;
import com.huawei.service.IRegisterUserService;
import com.huawei.service.ITagService;
import com.opensymphony.xwork2.ModelDriven;

public class FriendAction extends BaseAction implements ModelDriven<Friend>{
	private Friend friend = new Friend();
	private FriendApply friendApply = new FriendApply();
	private IFriendService<Friend> friendService;
	private IFriendApplyService<FriendApply> friendApplyService;
	private IRegisterUserService<RegisterUser> registerUserService;
	int total;

	List<Friend> rowsList;
	List<FriendQueryView>  rowsListView = new ArrayList<FriendQueryView>();
	List<Friend1QueryView>  rowsListView1 = new ArrayList<Friend1QueryView>();
	
	
	//add by zzq start
	private String phone;
	List<PhoneFriend> phoneList=new ArrayList<PhoneFriend>();
	
	
	@JSON(serialize=false)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	@JSON(name="rowsPhone")
	public List<PhoneFriend> getPhoneList() {
		return phoneList;
	}


	public void setPhoneList(List<PhoneFriend> phoneList) {
		this.phoneList = phoneList;
	}
	

	//add by zzq end
	@JSON(name="rowsView")
	public List<Friend1QueryView> getRowsListView1() {
		return rowsListView1;
	}

	public void setRowsListView1(List<Friend1QueryView> rowsListView1) {
		this.rowsListView1 = rowsListView1;
	}


	//ADD BY JH
	public String tagName;
	public String authority;
	public String layer;
	public List<SearchConnectionsView> rowsLayer;
	private ITagService<Tag> tagService;
	
	
	public void setTagService(ITagService<Tag> tagService) {
		this.tagService = tagService;
	}


	public List<SearchConnectionsView> getRowsLayer() {
		return rowsLayer;
	}


	public void setRowsLayer(List<SearchConnectionsView> rowsLayer) {
		this.rowsLayer = rowsLayer;
	}


	@JSON(serialize=false)
	public String getTagName() {
		return tagName;
	}


	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	@JSON(serialize=false)
	public String getAuthority() {
		return authority;
	}


	public void setAuthority(String authority) {
		this.authority = authority;
	}

	@JSON(serialize=false)
	public String getLayer() {
		return layer;
	}


	public void setLayer(String layer) {
		this.layer = layer;
	}
	//ADD BY JH END
	
	
	
	@JSON(serialize=false)
	public Friend getModel() {
		return friend;
	}
	
	
	public void setFriendService(IFriendService<Friend> friendService) {
		this.friendService = friendService;
	}

	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public void setFriendApplyService(
			IFriendApplyService<FriendApply> friendApplyService) {
		this.friendApplyService = friendApplyService;
	}



	@JSON(name="rows")
	public List<Friend> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<Friend> rowsList) {
		this.rowsList = rowsList;
	}

	
	public String searchByUserName() throws Exception{
		//RegisterUser re = null;
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		String hql = "from RegisterUser  r  where r.userName = '" +friend.getUser12().getUserName()+ "'  or r.tel ='" +friend.getUser12().getUserName()+"'  order by r.id ";		
		RegisterUser user=registerUserService.get(hql);
		int count=0;
		List<RegisterUser> result = registerUserService.list(hql, (page -1)*rows, rows, null);
		for (RegisterUser registerUser : result)
		{	
			if(user.getId()==registerUserLogin.getId() || user.getUserType().getId()==2){
				continue;}
			hql = "from Friend f where f.user1.id = " + registerUserLogin.getId() + " and f.user12.id = " + registerUser.getId();
			Friend friendInDataBase = friendService.get(hql);
			String isFriends;
			if(friendInDataBase  != null){isFriends="true";}else{isFriends="false";}
			//String isExistedFriend = friendInDataBase  != null ? true : false;
			FriendQueryView friendQueryView = new FriendQueryView( registerUser.getUserName(), registerUser.getTel(),  isFriends,registerUser.getId(),registerUser.getImgUrl(),registerUser.getMiniImgUrl());
			rowsListView.add(friendQueryView);
			count++;
			}
		
		//hql = "select count(*) from Friend f where   f.user12.userName = '" + friend.getUser12().getUserName()+ "'";
		//hql = "select count(*) from RegisterUser  r  where r.userName like '%" +friend.getUser12().getUserName()+ "%'  or r.tel like '%" +friend.getUser12().getUserName()+ "%' order by r.id ";
		result=null;
		total =count;		
		
		return SUCCESS;	
	}
	
	public String listAllFriend() throws Exception{
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		//String hql = "from RegisterUser r left join fetch r.friends1 left join fetch r.friends2 where r.id = " + registerUserLogin.getId() +" order by r.id desc";
		//List<RegisterUser> result = registerUserService.list(hql, (page -1)*rows, rows, null);
		String hql = "from Friend f left join fetch f.user12 where f.user1.id = " + registerUserLogin.getId();
		rowsList = friendService.list(hql, (page -1)*rows, rows, null);
		int count = 0;
		//hql= "select count(*) from Friend f  where f.user1.id = " + registerUserLogin.getId();
		//total =friendService.getTotalCount(hql, null);
		for (Friend f : rowsList)
		{
			Friend1QueryView friendQueryView1 = new Friend1QueryView(f.getUser12().getId(),f.getUser12Level(),f.getUser12().getUserName(),f.getUser12().getMiniImgUrl());
			rowsListView1.add(friendQueryView1);
			count ++;
		}
		rowsList = null;
		total = count;
		
/*		for (RegisterUser registerUser: result)
		{
			FriendQueryView friendQueryView = new FriendQueryView(null,registerUser.getFriends2(),friend.getUser12Group(),  friend.getUser12Level(), null);
			rowsListView.add(friendQueryView);
			
		}
		*/

 		return SUCCESS;
	}
	
	
	/*public String listExsitedFriendByUserName() throws Exception {
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		String hql = "from RegisterUser  r  where r.userName like '%" + friend.getUser12().getUserName() + "%' order by r.id desc";		
		List<RegisterUser> result = registerUserService.list(hql, (page -1)*rows, rows, null);
		int count = 0;
		for (RegisterUser registerUser : result)
		{
			hql = "from Friend f where f.user1.id = " + registerUserLogin.getId() + " and f.user12.id = " + registerUser.getId();
			Friend friendInDataBase = friendService.get(hql);
			//String isExistedFriend;
			//if(friendInDataBase  != null){isExistedFriend="true";}else{isExistedFriend="false";}
			//String isExistedFriend = friendInDataBase  != null ? true : false;
			if (friendInDataBase == null)
				continue;
			FriendQueryView friendQueryView = new FriendQueryView(null, registerUser.getId(), registerUser.getUserName(), null, null , "true");
			rowsListView.add(friendQueryView);
			count ++;
		}
		//hql = "select count(*) from Friend f where   f.user12.userName = '" + friend.getUser12().getUserName()+ "'";
		//hql = "select count(*) from RegisterUser  r where r.userName like '%" + friend.getUser12().getUserName() + "%'";
		//total =friendService.getTotalCount(hql, null);		
		total = count;
		return SUCCESS;
	}*/
	
	
	public String add() throws Exception
	{
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		String hql = "from RegisterUser r where r.userName = '" + friend.getUser12().getUserName() + "'	";
		String user12Name =  friend.getUser12().getUserName();
		RegisterUser user12 = registerUserService.get(hql);
		friend.setUser12(user12);
		friend.setUser1(registerUserLogin);
		String hql1="from Friend f where f.user1.id="+friend.getUser12().getId()+" and f.user12.id="+registerUserLogin.getId();
		Friend flag=friendService.get(hql1);
		friend.setDateCreated(new Date());
		if(flag==null){
		friendService.save(friend);
		}else{return "existed";}
		
		Friend friend2 = new Friend();
		friend2.setUser12(registerUserLogin);
		friend2.setUser1(user12);
		String hql2="from FriendApply fa where fa.user1.id="+friend.getUser12().getId()+" and fa.user12.id="+registerUserLogin.getId();
		FriendApply flag1=friendApplyService.get(hql2);
		friend2.setUser12Level(flag1.getAuthority());
		friend2.setDateCreated(new Date());
		if(flag==null){
		friendService.save(friend2);
		}else{return "existed";}
		hql = " from FriendApply f where f.user1.userName = '" + user12Name + "' and f.user12.userName = '" +registerUserLogin.getUserName() +  "'";
		//FriendApply  fa = friendApplyService.get(FriendApply.class, friendApply.getId());
		//friendApplyService.get(hql)
		List<FriendApply> result = friendApplyService.list(hql);
		for (FriendApply fa : result)
		{
			friendApplyService.delete(fa);
		}
		return SUCCESS;
	}
	
	public String rejectApply() throws Exception 
	{
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		String user12Name =  friend.getUser12().getUserName();
		String hql = " from FriendApply f where f.user1.userName = '" + user12Name + "' and f.user12.userName = '" +registerUserLogin.getUserName() +  "'";
		//FriendApply  fa = friendApplyService.get(FriendApply.class, friendApply.getId());
		//friendApplyService.get(hql)
		List<FriendApply> result = friendApplyService.list(hql);
		for (FriendApply fa : result)
		{
			friendApplyService.delete(fa);
		}
		return SUCCESS;
	}
	
	

	//ADD BY JH
	public String searchConnections() throws Exception
	{
		List<Long> friendsList = new ArrayList<Long>();		//所有ID
		List<SearchConnectionsView> friendsInUse;			//本轮要用的ID
		List<SearchConnectionsView> friendsNext = 
				new ArrayList<SearchConnectionsView>();	//下轮加入列表的ID
		List<Friend> friends;
		SearchConnectionsView searchConnectionsView;		
		int layerInt = Integer.parseInt(layer);
		int authorityInt = Integer.parseInt(authority);
		int count=0;
		
		rowsLayer = new ArrayList<SearchConnectionsView>();
		
		
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		String hql="from Friend f left join fetch f.user12 where f.user1.id="+
				registerUserLogin.getId()+" order by f.user12Level desc";
		friends = friendService.list(hql);
		
		for(Friend friend:friends){
			friendsList.add(friend.getUser12().getId());
			if(friend.getUser12Level()==null){
				continue;
			}
			
			if(friend.getUser12Level()<authorityInt){
				continue;
			}
			
			searchConnectionsView = 
					new SearchConnectionsView(friend.getUser12().getId(),
							friend.getUser12().getUserName(),friend.getUser12().getMiniImgUrl());
			searchConnectionsView.add(searchConnectionsView);
			friendsNext.add(searchConnectionsView);
			
			hql = "select count (*) from Tag t where t.registerUser.id="+
					searchConnectionsView.getPerson().getUserId()+" and t.tagName like '%"+tagName+"%'";

			if(tagService.getTotalCount(hql, null)==0){
				continue;
			}			
			
			hql = "from Tag t where t.registerUser.id="+
					searchConnectionsView.getPerson().getUserId();
			searchConnectionsView.setTags(tagService.list(hql));
			
			rowsLayer.add(searchConnectionsView);
	//		searchConnectionsView.clearTags();
			count++;
			if(count==30){
				break;
			}
		}
		
		
		if(layerInt==1||count==30){
			total=count;
			return SUCCESS;
		}
				
		
		for(int i=1;i<layerInt;i++){
			
			friendsInUse = new ArrayList<SearchConnectionsView>();
			friendsInUse.addAll(friendsNext);
			friendsNext.removeAll(friendsInUse);
			
			for(SearchConnectionsView s:friendsInUse){
				hql="from Friend f left join fetch f.user12 where f.user1.id="+
						s.getPerson().getUserId()+" order by f.user12Level desc";
				friends = friendService.list(hql);
				
				for(Friend friend:friends){
					if(friend.getUser12().getId()==registerUserLogin.getId()){//本人ID则跳过
						continue;
					}
					if(friendsList.contains(friend.getUser12().getId())){//如果已在列表中
						continue;
					}
					
					friendsList.add(friend.getUser12().getId());
					
					if(friend.getUser12Level()==null){
						continue;
					}
					
					if(friend.getUser12Level()<authorityInt){
						continue;
					}
					
					
					searchConnectionsView = 
							new SearchConnectionsView(friend.getUser12().getId(),
									friend.getUser12().getUserName(), friend.getUser12().getMiniImgUrl());
					searchConnectionsView.add(s);
					friendsNext.add(searchConnectionsView);
					
					hql="select count(*) from Tag t where t.registerUser.id="+
							searchConnectionsView.getPerson().getUserId()+" and t.tagName like '%"+tagName+"%'";
					if(tagService.getTotalCount(hql, null)==0){
						continue;
					}
					
					hql = "from Tag t where t.registerUser.id="+
							searchConnectionsView.getPerson().getUserId();
					searchConnectionsView.setTags(tagService.list(hql));
					
					rowsLayer.add(searchConnectionsView);
			//		searchConnectionsView.clearTags();
					
					count++;
					if(count>=30){
						break;
					}
				}
			}
		}
		
		total=count;
		return SUCCESS;
	}
	//ADD BY JH END
	
	//add by zzq
	public String searchPhone(){
		//分割数组
		String[] phones=null;
		if(phone!=null&&phone!=""){
			phones=phone.split(",");
		}
		//所有注册用户
		List<RegisterUser> rList=new ArrayList<RegisterUser>();
		//所有朋友
		List<Friend> friendList=new ArrayList<Friend>();
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		String hql1 = "from Friend f left join fetch f.user12 where f.user1.id = " + registerUserLogin.getId();
		//String hql1 = "from Friend f left join fetch f.user12 where f.user1.id =1";
		
		if(phones!=null&&phones.length>0){
			try {
				//查询所有电话号码中已经注册的用户
			     String hql2="from RegisterUser r left join fetch r.role left join fetch r.userType "
			     		+ " where r.userType=1 and r.tel in (";
					for(int i=0;i<phones.length;i++){
						if(i>0){
							hql2+=",";
						}
						hql2+="'"+phones[i]+"'";
					}
					hql2+=")";
					rList=registerUserService.list(hql2);	
				//查询出当前用户所有好友
				friendList = friendService.list(hql1);
				//判断注册用户中用户中哪些已经是好友
		        for(RegisterUser u:rList){
                	PhoneFriend ph=new PhoneFriend();
                	ph.setUserName(u.getUserName());
                	ph.setTel(u.getTel());
		        	for(Friend f:friendList){
		        		if(u.getTel().equals(f.getUser12().getTel())){
		                	ph.setIsFriends(true);
		        		}
		        	}
		        	phoneList.add(ph);
		        }
	        total=phoneList.size();       
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return SUCCESS;
		
	}
	// add by zzq end	
	public String modify() throws Exception
	{			
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
//		Friend friendDataBase = friendService.get(Friend.class,friend.getId());
		String hql="from Friend f where f.user12.id="+friend.getUser12().getId()+" and f.user1.id="+registerUserLogin.getId()+"";
		Friend friendDataBase = friendService.get(hql);
		//friend.setUser12(friendDataBase.getUser12());
		//friend.setUser12Level(friendDataBase.getUser12Level());
		BeanUtils.copyProperties(friend, friendDataBase,new String[]{"id","dateCreated","user1Group","user1","user1Level","user12Group"});
		friendService.update(friendDataBase);
		return SUCCESS;
	}
	
	public String delete() throws Exception
	{		
		RegisterUser registerUserLogin = (RegisterUser)session.get("registerUserLogin");
		//String user12 =  friend.getUser12().getId();
		String hql = " from Friend f where f.user1.id = '" + friend.getUser12().getId() + "' and f.user12.id = '" +registerUserLogin.getId() +  "'";
		String hql1 = " from Friend f where f.user1.id = '" + registerUserLogin.getId() + "' and f.user12.id = '" +friend.getUser12().getId() +  "'";
		//FriendApply  fa = friendApplyService.get(FriendApply.class, friendApply.getId());
		//friendApplyService.get(hql)
		List<Friend> result = friendService.list(hql);
		List<Friend> result1 = friendService.list(hql1);
		for (Friend f : result)
		{
			friendService.delete(f);
		}
		for (Friend f1 : result1)
		{
			friendService.delete(f1);
		}
			return SUCCESS;
	}
	

	@JSON(name="rowsListView")
	public List<FriendQueryView> getRowsListView() {
		return rowsListView;
	}


	public void setRegisterUserService(IRegisterUserService<RegisterUser> registerUserService) {
		this.registerUserService = registerUserService;
	}


}
