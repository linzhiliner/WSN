package com.huawei.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.huawei.domain.Focus;
import com.huawei.domain.RegisterUser;
import com.huawei.domain.ShopInfo;
import com.huawei.domain.Tag;
import com.huawei.service.IFocusService;
import com.huawei.service.IRegisterUserService;
import com.huawei.service.IShopInfoService;
import com.huawei.service.ITagService;
import com.opensymphony.xwork2.ModelDriven;

public class ShopInfoAction extends BaseAction implements ModelDriven<ShopInfo> {
	private IShopInfoService<ShopInfo> shopInfoService;
	private ITagService<Tag> tagService;
	private IFocusService<Focus> focusService;
	private String tagName;
	@JSON(serialize=false)
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public void setTagService(ITagService<Tag> tagService) {
		this.tagService = tagService;
	}

	private IRegisterUserService<RegisterUser> registerUserService;
	private ShopInfo shopInfo = new ShopInfo();
	int total;
	List<ShopInfo> rowsList;
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(name="rows")
	public List<ShopInfo> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<ShopInfo> rowsList) {
		this.rowsList = rowsList;
	}
	
	@JSON(serialize=false)
	public ShopInfo getModel() {
		// TODO Auto-generated method stub
		return shopInfo;
	}

	public String list() throws Exception
	{	
		String hql = "from ShopInfo s left join fetch s.registerUser order by s.id desc";
		rowsList = shopInfoService.list(hql, (page -1)*rows, rows, null);
		for(int i=0;i<rowsList.size();i++){
			rowsList.get(i).setRegisterUser(null);
		}
		hql = "select count(*) from ShopInfo";
		total = shopInfoService.getTotalCount(hql, null);
		return SUCCESS;
		
	}
	
	 public String listShopAround(){
		 String hql="from ShopInfo s left join fetch s.registerUser where sqrt( ( (("+shopInfo.getLongitude()+"-longitude)*PI()*12656*cos((("+shopInfo.getLatitude()+"+latitude)/2)*PI()/180)/180) * (("+shopInfo.getLongitude()+"-longitude)*PI()*12656*cos((("+shopInfo.getLatitude()+"+latitude)/2)*PI()/180)/180) ) + ( (("+shopInfo.getLatitude()+"-latitude)*PI()*12656/180) * (("+shopInfo.getLatitude()+"-latitude)*PI()*12656/180) ) ) <2 order by sqrt( ( (("+shopInfo.getLongitude()+"-longitude)*PI()*12656*cos((("+shopInfo.getLatitude()+"+latitude)/2)*PI()/180)/180) * (("+shopInfo.getLongitude()+"-longitude)*PI()*12656*cos((("+shopInfo.getLatitude()+"+latitude)/2)*PI()/180)/180) ) + ( (("+shopInfo.getLatitude()+"-latitude)*PI()*12656/180) * (("+shopInfo.getLatitude()+"-latitude)*PI()*12656/180) ) ) asc";
		 rowsList = shopInfoService.list(hql, (page -1)*rows, rows, null);
		 for(int i=0;i<rowsList.size();i++){
				rowsList.get(i).setImg(rowsList.get(i).getRegisterUser().getImgUrl());
				rowsList.get(i).setMiniImg(rowsList.get(i).getRegisterUser().getMiniImgUrl());
				rowsList.get(i).getRegisterUser().setUserType(null);
				rowsList.get(i).getRegisterUser().setRole(null);
			}
		 hql="select count(*) from ShopInfo s where sqrt( ( (("+shopInfo.getLongitude()+"-longitude)*PI()*12656*cos((("+shopInfo.getLatitude()+"+latitude)/2)*PI()/180)/180) * (("+shopInfo.getLongitude()+"-longitude)*PI()*12656*cos((("+shopInfo.getLatitude()+"+latitude)/2)*PI()/180)/180) ) + ( (("+shopInfo.getLatitude()+"-latitude)*PI()*12656/180) * (("+shopInfo.getLatitude()+"-latitude)*PI()*12656/180) ) )<2";
		 total=shopInfoService.getTotalCount(hql, null);
		 return SUCCESS;
	 }
	
	/** 显示商铺详情
	 * 2013-12-2
	 * @return
	 * @throws Exception
	 */
	public String showShopInfoDetail() throws Exception{
		if(shopInfo!=null){
			String hql="";
			if(shopInfo.getId()!=0){
				 hql = "from ShopInfo si left join fetch si.registerUser where si.id="+shopInfo.getId();
			}else{
				 RegisterUser registerUserLogin = (RegisterUser) session.get("registerUserLogin");
				 hql="from ShopInfo si left join fetch si.registerUser where si.registerUser.id="+registerUserLogin.getId();
			}
			rowsList=shopInfoService.list(hql);
			for(int i=0;i<rowsList.size();i++){
				rowsList.get(i).setImg(rowsList.get(i).getRegisterUser().getImgUrl());
				rowsList.get(i).setMiniImg(rowsList.get(i).getRegisterUser().getMiniImgUrl());
				rowsList.get(i).getRegisterUser().setUserType(null);
				rowsList.get(i).getRegisterUser().setRole(null);
			}
			total=1;
		}
		return SUCCESS;
	}
	
	/** 统计商铺粉丝数
	 * 2013-12-2
	 * @return
	 * @throws Exception
	 */
	public String fansCount() throws Exception{
		if(shopInfo!=null){
			String hql="select count(*) from Focus f left join f.owner r where r.id=";
			if(shopInfo.getId()!=0){
				RegisterUser registerUserInDataBase=null;
				//取得商铺对应的注册者的Id
				String searchShopOwnerHQL="from ShopInfo si where si.id="+shopInfo.getId();
				ShopInfo si=shopInfoService.get(searchShopOwnerHQL);
				if(si!=null){
					registerUserInDataBase=si.getRegisterUser();
				}
				hql+=registerUserInDataBase.getId();
			}else{
				//shopId=0查看自己的粉丝
				RegisterUser registerUserLogin = (RegisterUser) session.get("registerUserLogin");
				hql+=registerUserLogin.getId();
			}
			total=focusService.getTotalCount(hql, null);
		}
		return SUCCESS;
	}
	
	public String groupShop(){
		String hql = "from Tag t left join fetch t.registerUser r left join fetch r.userType u where u.id=2 and t.tagName='"+getTagName()+"'";
		List<Tag> rowstemp = tagService.list(hql);
		StringBuffer sb = new StringBuffer("(");
		if(rowstemp.size()>0){
			for(int i=0;i<rowstemp.size()-1;i++){
			sb.append(rowstemp.get(i).getRegisterUser().getId());
			sb.append(",");
			}
			sb.append(rowstemp.get(rowstemp.size()-1).getRegisterUser().getId());
			sb.append(")");
			hql = "from ShopInfo s left join fetch s.registerUser r where sqrt( ( (("+shopInfo.getLongitude()+"-longitude)*PI()*12656*cos((("+shopInfo.getLatitude()+"+latitude)/2)*PI()/180)/180) * (("+shopInfo.getLongitude()+"-longitude)*PI()*12656*cos((("+shopInfo.getLatitude()+"+latitude)/2)*PI()/180)/180) ) + ( (("+shopInfo.getLatitude()+"-latitude)*PI()*12656/180) * (("+shopInfo.getLatitude()+"-latitude)*PI()*12656/180) ) ) <2 and r.id in"+sb;
			rowsList = shopInfoService.list(hql, (page -1)*rows, rows, null);
			total = rowsList.size();
			for(int i=0;i<rowsList.size();i++){
			rowsList.get(i).setImg(rowsList.get(i).getRegisterUser().getImgUrl());
			rowsList.get(i).setMiniImg(rowsList.get(i).getRegisterUser().getMiniImgUrl());
			rowsList.get(i).getRegisterUser().setUserType(null);
			rowsList.get(i).getRegisterUser().setRole(null);
			}
		}
		return SUCCESS;
	}
	
	

   
	public String add() throws Exception
	{
		shopInfo.setDateCreated(new Date());
		shopInfoService.save(shopInfo);
		return SUCCESS;
	}
	
	
	/**
	 * 商铺信息更新
	 *  2013-12-2
	 * @return
	 * @throws Exception
	 * @author zzq 
	 *        
	 */
	public String modify() throws Exception{	
		RegisterUser u = (RegisterUser)session.get("registerUserLogin");
		String searchShopInfoIdHQL="from ShopInfo si left join fetch si.registerUser where si.registerUser="+u.getId();
		
		ShopInfo shopInfoInDataBase = shopInfoService.get(searchShopInfoIdHQL);
		
		if(shopInfoInDataBase!=null){
			
			String shopName = shopInfo.getShopName();
			if (shopName != null && !"".equals(shopName) && !shopName.equals(shopInfoInDataBase.getShopName())) {
				String hql = "select count(*) from ShopInfo si where si.shopName='"+ shopName + "'";
				int t = shopInfoService.getTotalCount(hql, null);
				if (t == 0) {
					shopInfoInDataBase.setShopName(shopName);
					
				}else{
					return "modusernamefail";
				}
			}
			
			if(shopInfo.getAddress()!=null&&!"".equals(shopInfo.getAddress())){
				shopInfoInDataBase.setAddress(shopInfo.getAddress());
			}
			if(shopInfo.getTel()!=null&&!"".equals(shopInfo.getTel())){
				shopInfoInDataBase.setTel(shopInfo.getTel());
			}
			if(shopInfo.getIntroduction()!=null&&!"".equals(shopInfo.getIntroduction())){
				shopInfoInDataBase.setIntroduction(shopInfo.getIntroduction());
			}
			if(shopInfo.getLatitude()!=null&&!"".equals(shopInfo.getLatitude())){
				shopInfoInDataBase.setLatitude(shopInfo.getLatitude());
			}
			if(shopInfo.getLongitude()!=null&&!"".equals(shopInfo.getLongitude())){
				shopInfoInDataBase.setLongitude(shopInfo.getLongitude());
			}
			shopInfoService.update(shopInfoInDataBase);
		} 
		
		return SUCCESS;

	}
	
	public String delete() throws Exception
	{	
		shopInfoService.delete(ShopInfo.class, shopInfo.getId());
		return SUCCESS;
	}

	public void setShopInfoService(IShopInfoService<ShopInfo> shopInfoService) {
		this.shopInfoService = shopInfoService;
	}

	public void setFocusService(IFocusService<Focus> focusService) {
		this.focusService = focusService;
	}

	public void setRegisterUserService(IRegisterUserService<RegisterUser> registerUserService) {
		this.registerUserService = registerUserService;
	}


	

}
