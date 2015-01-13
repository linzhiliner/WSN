package com.huawei.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import com.huawei.action.domain.view.FriendDetailQueryView;
import com.huawei.domain.Friend;
import com.huawei.domain.RegisterUser;
import com.huawei.domain.Role;
import com.huawei.service.IFriendService;
import com.huawei.service.IRegisterUserService;
import com.huawei.utils.DateTimeUtils;
import com.huawei.utils.Upload;
import com.opensymphony.xwork2.ModelDriven;

public class RegisterUserAction extends BaseAction implements ModelDriven<RegisterUser>{
	private RegisterUser registerUser = new RegisterUser();
	private IRegisterUserService<RegisterUser> registerUserService;
	private IFriendService<Friend> friendService;
	
	int total;
	List<RegisterUser> rowsList;
	List<FriendDetailQueryView> friendDetailList=new ArrayList<FriendDetailQueryView>();
	
	@JSON(name="rowsDetail")
	public List<FriendDetailQueryView> getFriendDetailList() {
		return friendDetailList;
	}

	public void setFriendDetailList(List<FriendDetailQueryView> friendDetailList) {
		this.friendDetailList = friendDetailList;
	}

	@JSON(serialize=false)
	public RegisterUser getModel()
	{
		return registerUser;
	}
	
	public String list() throws Exception
	{
		
		String hql = "from RegisterUser r left join fetch r.role left join fetch r.userType order by r.id desc";
		
		rowsList = registerUserService.list(hql, (page -1)*rows, rows, null);
		hql = "select count(*) from RegisterUser r";

		total = registerUserService.getTotalCount(hql, null);
		return SUCCESS;
	}


	public String add() throws Exception
	{
		String hql = "";
		RegisterUser registerUserAdd = null;
		
		//普通用户注册时默认昵称==手机号
		if(registerUser.getUserType().getId()==1){
			hql = "from RegisterUser r where r.tel= '" + registerUser.getTel() + "'";
			 registerUserAdd = registerUserService.get(hql);
			if(registerUserAdd!=null){    //判断是否已有该手机号是否已经存在
				return "existed";
			}
			//registerUser.setUserName(registerUser.getTel());
			registerUser.setDateCreated(new Date());
			registerUser.setMsgId(0L);
			registerUserService.addUser(registerUser);
		}else{
			hql = "from RegisterUser r where r.userName= '" + registerUser.getUserName() + "'";
			registerUserAdd = registerUserService.get(hql);
			if(registerUserAdd!=null){    //判断是否已有该商品用户名是否已经存在
				return "existed";
			}
			registerUser.setDateCreated(new Date());
			registerUser.setMsgId(0L);
			registerUserService.addShop(registerUser);
		}
		RegisterUser u=registerUserService.get(hql);
		if(u!=null){
			session.put("registerUserLogin", u);
		}
		return SUCCESS;
	}
	
	public String modify() throws Exception
	{
		RegisterUser registerUserInDataBase = registerUserService.get(RegisterUser.class, registerUser.getId());
		//registerUserInDataBas
		
		return SUCCESS;
	}
	
	public String delete() throws Exception
	{
		registerUserService.delete(RegisterUser.class, registerUser.getId());
		return SUCCESS;
	}
	
	public String login() throws Exception
	{
		String hql = "from RegisterUser r where (r.tel = '" + registerUser.getTel() + "' or r.userName ='"+registerUser.getTel()+"' ) and r.password = '" + registerUser.getPassword() + "'";
		RegisterUser registerUserLogin = registerUserService.get(hql);
		
		if (registerUserLogin != null)
			session.put("registerUserLogin", registerUserLogin);
		else
			return "login";
		session.put("loginId", registerUserLogin.getId());
		//add by zzq 2013-12-2
		session.put("userType", registerUserLogin.getUserType().getId());
		RegisterUser registerUserInDate=registerUserService.get(RegisterUser.class, registerUserLogin.getId());
		
		session.put("imgPath", registerUserInDate.getMiniImgUrl());
		return SUCCESS;
	}


	//add by zzq start
	public String showFriendDetail() throws Exception
	{		
		
		List<RegisterUser> list=new ArrayList<RegisterUser>();
		RegisterUser registerUserInDataBase = (RegisterUser) registerUserService.get(RegisterUser.class, registerUser.getId());
		FriendDetailQueryView fdqv=new FriendDetailQueryView();
		BeanUtils.copyProperties(registerUserInDataBase, fdqv);
		//查询好友表查出该好友的信任等级
		RegisterUser registerUserLogin = (RegisterUser) session.get("registerUserLogin");
		String hql = " from Friend f where f.user12.id="+registerUser.getId()+" and f.user1.id="+registerUserLogin.getId();
		Friend f=friendService.get(hql);
		//EDIT BY JH
		if(f!=null){
			fdqv.setAuthority(f.getUser12Level());
		}else{
			fdqv.setAuthority(0);
		}
		friendDetailList.add(fdqv);
		total=1;
		return SUCCESS;
	}
	
	//ADD BY JH
	String oldPassword;
	@JSON(serialize=false)
	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String modifyUserPwd() throws Exception{
		RegisterUser registerUserLogin = (RegisterUser) session.get("registerUserLogin");
		RegisterUser registerUserInDataBase = (RegisterUser) registerUserService.get(RegisterUser.class, registerUserLogin.getId());
		if(!oldPassword.equals(registerUserInDataBase.getPassword())){
			return "errorPwd";
		}
		registerUserInDataBase.setPassword(registerUser.getPassword());
		registerUserService.update(registerUserInDataBase);
		return SUCCESS;
	}
	//ADD BY JH END
	
	
	public String modifyBasicInfo() throws Exception {
		
		RegisterUser registerUserLogin = (RegisterUser) session.get("registerUserLogin");
		RegisterUser registerUserInDataBase = (RegisterUser) registerUserService.get(RegisterUser.class, registerUserLogin.getId());
	
		String userName = registerUser.getUserName();
		if (userName != null && !"".equals(userName) && !userName.equals(registerUserInDataBase.getUserName())) {
			String hql = "select count(*) from RegisterUser u where u.userName='"+ userName + "'";
			int t = registerUserService.getTotalCount(hql, null);
			if (t == 0) {
				registerUserInDataBase.setUserName(userName);
				
			}else{
				return "modusernamefail";
			}
		}
		if (registerUser.getEmail() != null && !"".equals(registerUser.getEmail())) {
			registerUserInDataBase.setEmail(registerUser.getEmail());
		}
		if (registerUser.getSex() != null) {
			registerUserInDataBase.setSex(registerUser.getSex());
		}
		if (registerUser.getBirthDay() != null) {
			registerUserInDataBase.setBirthDay(registerUser.getBirthDay());
		}

		if (registerUser.getBloodGroup() != null && !"".equals(registerUser.getBloodGroup())) {
			registerUserInDataBase.setBloodGroup(registerUser.getBloodGroup());
		}
		if (registerUser.getIntroduction() != null && !"".equals(registerUser.getIntroduction())) {
			registerUserInDataBase.setIntroduction(registerUser.getIntroduction());
		}
		if (registerUser.getQq() != null &&!"".equals(registerUser.getQq())) {
			registerUserInDataBase.setQq(registerUser.getQq());
		}

		if (registerUser.getAddress() != null && !"".equals(registerUser.getAddress())) {
			registerUserInDataBase.setAddress(registerUser.getAddress());
		}
		if (registerUser.getJob() != null && !"".equals(registerUser.getJob())) {
			registerUserInDataBase.setJob(registerUser.getJob());
		}
        if(registerUser.getRole()!=null){
        	Role role= registerUser.getRole();
        	registerUserInDataBase.setRole(role);
        }
        if(registerUser.getUserRealName()!=null&& !"".equals(registerUser.getUserRealName())){
        	registerUserInDataBase.setUserRealName(registerUser.getUserRealName());
        }
        if(registerUser.getUserName()!=null&&!"".equals(registerUser.getUserName())){
        	registerUserInDataBase.setUserName(registerUser.getUserName());
        }
        

        
        
        registerUserService.update(registerUserInDataBase);
		return SUCCESS;

	}
	
	public String uploadHeadPortrait() throws Exception{
		String url="";  
		String urlMin="";
		String fileName ="";
        //上传，修改头像
		RegisterUser registerUserLogin = (RegisterUser) session.get("registerUserLogin");
		RegisterUser registerUserInDataBase = (RegisterUser) registerUserService.get(RegisterUser.class, registerUserLogin.getId());
        if(getFile()!=null){


    		fileName = DateTimeUtils.getTimeStamp() + registerUserInDataBase.getId()+getFileFileName().substring(getFileFileName().lastIndexOf("."));
    		url = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("")+File.separator+Upload.HEADPORTRAIT_URL+fileName;
    		urlMin = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("")+File.separator+Upload.MINIHEADPORTRAIT_URL+fileName;
    		registerUserInDataBase.setImgUrl(Upload.HEADPORTRAIT_URL+fileName);
    		registerUserInDataBase.setMiniImgUrl(Upload.MINIHEADPORTRAIT_URL+fileName);
    		Upload.save(url, getFile());
    		Thumbnails.of(url).size(50,50).keepAspectRatio(true).toFile(urlMin); 	        	
        }
        registerUserService.update(registerUserInDataBase);
		return SUCCESS;
	}
	
	
	
	
	
	//add by zzq end
	
	public void setRegisterUserService(
			IRegisterUserService<RegisterUser> registerUserService) {
		this.registerUserService = registerUserService;
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

	@JSON(name="rows")
	public List<RegisterUser> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<RegisterUser> rowsList) {
		this.rowsList = rowsList;
	}

}
