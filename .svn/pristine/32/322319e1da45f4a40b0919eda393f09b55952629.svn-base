package com.huawei.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.huawei.domain.Notification;
import com.huawei.domain.RegisterUser;
import com.huawei.domain.ShopComment;
import com.huawei.domain.ShopInfo;
import com.huawei.domain.UserGroupTag;
import com.huawei.service.INotificationService;
import com.huawei.service.IShopCommentService;
import com.huawei.service.IShopInfoService;
import com.opensymphony.xwork2.ModelDriven;


public class ShopCommentAction extends BaseAction implements ModelDriven<ShopComment>{
	private ShopComment shopComment=new ShopComment();
	private IShopCommentService<ShopComment> shopCommentService;
	private IShopInfoService<ShopInfo> shopInfoService;
	private INotificationService<Notification> notificationService;
	public void setNotificationService(
			INotificationService<Notification> notificationService) {
		this.notificationService = notificationService;
	}
	public void setShopInfoService(IShopInfoService<ShopInfo> shopInfoService) {
		this.shopInfoService = shopInfoService;
	}

	int total;
	List<ShopComment> rowsList;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@JSON(name="rows")
	public List<ShopComment> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<ShopComment> rowsList) {
		this.rowsList = rowsList;
	}

	@JSON(serialize=false)
	public ShopComment getModel(){
		return shopComment;
	}
	
	public String list() throws Exception{
		String hql = "";
	
		if(shopComment.getShopInfo().getId()!=0){
		 hql = "from ShopComment sc left join fetch sc.registerUser  where sc.shopInfo.id="+shopComment.getShopInfo().getId()+" order by sc.id desc";
		 rowsList = shopCommentService.list(hql, (page -1)*rows, rows, null);
		 for(int i=0;i<rowsList.size();i++){
			rowsList.get(i).getRegisterUser().setUserType(null);
			rowsList.get(i).getRegisterUser().setRole(null);
			rowsList.get(i).setShopInfo(null);
			}
		 hql = "select count(*) from ShopComment sc where sc.shopInfo.id="+shopComment.getShopInfo().getId()+"";
		 total = shopCommentService.getTotalCount(hql, null);
		}else{
			RegisterUser u=(RegisterUser) session.get("registerUserLogin");
			hql = "from ShopInfo si where si.registerUser.id="+u.getId()+"";
			ShopInfo shopInfo = shopInfoService.get(hql);
			hql = "from ShopComment sc left join fetch sc.registerUser where sc.shopInfo.id="+shopInfo.getId()+" order by sc.id desc";
			rowsList = shopCommentService.list(hql, (page -1)*rows, rows, null);
			for(int i=0;i<rowsList.size();i++){
				rowsList.get(i).getRegisterUser().setUserType(null);
				rowsList.get(i).getRegisterUser().setRole(null);
				rowsList.get(i).setShopInfo(null);
			}
			 hql = "select count(*) from ShopComment sc where sc.shopInfo.id="+shopInfo.getId()+"";
			 total = shopCommentService.getTotalCount(hql, null);
			 notificationService.clearNotification(u.getId() ,6);
		}

		return SUCCESS;
	}
	
	public String add() throws Exception{
		shopComment.setDateCreated(new Date());
		RegisterUser u=(RegisterUser) session.get("registerUserLogin");
		shopComment.setRegisterUser(u);
		String hql = "from ShopInfo si where si.id="+shopComment.getShopInfo().getId()+"";
		ShopInfo shopInfo = shopInfoService.get(hql);
		long sellerId = shopInfo.getRegisterUser().getId();
		shopCommentService.add(shopComment,sellerId);
		return SUCCESS;
	}
	
	public String modify() throws Exception{
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		shopCommentService.delete(ShopComment.class, shopComment.getId());
		return SUCCESS;
	}
	
	public void setShopCommentService(IShopCommentService<ShopComment> shopCommentSerive){
	    this.shopCommentService=shopCommentSerive;
	}
}