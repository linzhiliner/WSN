package com.huawei.action;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import com.huawei.action.domain.view.WeiBoQueryView;
import com.huawei.domain.Focus;
import com.huawei.domain.Friend;
import com.huawei.domain.Notification;
import com.huawei.domain.RegisterUser;
import com.huawei.domain.ShopInfo;
import com.huawei.domain.WeiBo;
import com.huawei.service.IFocusService;
import com.huawei.service.IFriendService;
import com.huawei.service.INotificationService;
import com.huawei.service.IShopInfoService;
import com.huawei.service.IWeiBoService;
import com.huawei.utils.DateTimeUtils;
import com.huawei.utils.Upload;
import com.opensymphony.xwork2.ModelDriven;


public class WeiBoAction extends BaseAction implements ModelDriven<WeiBo> {
	private WeiBo weiBo = new WeiBo();
	private IWeiBoService<WeiBo> weiBoService;
	public final double PI =3.1415926;
	private INotificationService<Notification> notificationService;
	public void setNotificationService(
			INotificationService<Notification> notificationService) {
		this.notificationService = notificationService;
	}
	private IFriendService<Friend> friendService;
	public void setFriendService(IFriendService<Friend> friendService) {
		this.friendService = friendService;
	}
	private IFocusService<Focus> focusService;
	
	public void setFocusService(IFocusService<Focus> focusService) {
		this.focusService = focusService;
	}
	
	private IShopInfoService<ShopInfo> shopInfoService;
	
	public void setShopInfoService(IShopInfoService<ShopInfo> shopInfoService) {
		this.shopInfoService = shopInfoService;
	}
	int total;
	List<WeiBo> rowsList;
	//add by zzq 2013-12-2
    private int listType;	
    private List<WeiBoQueryView> rowsWeiBo; 

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	
	public List<WeiBoQueryView> getRowsWeiBo() {
		return rowsWeiBo;
	}

	public void setRowsWeiBo(List<WeiBoQueryView> rowsWeiBo) {
		this.rowsWeiBo = rowsWeiBo;
	}
	
	@JSON(serialize=false)
	public List<WeiBo> getRowsList() {
		return rowsList;
	}


	public void setRowsList(List<WeiBo> rowsList) {
		this.rowsList = rowsList;
	}

	@JSON(serialize=false)
	public WeiBo getModel() {
		return weiBo;
	}
	@JSON(serialize=false)
	public int getListType() {
		return listType;
	}

	public void setListType(int listType) {
		this.listType = listType;
	}

	public void setWeiBoService(IWeiBoService<WeiBo>  weiBoService) {
		this.weiBoService = weiBoService;
	}

	public String listDetail(){
		String hql="from WeiBo w left join fetch w.registerUser r left join fetch r.userType where w.id="+weiBo.getId()+"";
		WeiBo wb=weiBoService.get(hql);
		if(wb!=null){
			rowsWeiBo=new ArrayList<WeiBoQueryView>();
			WeiBoQueryView wbqv=new WeiBoQueryView();
			BeanUtils.copyProperties(wb, wbqv);
			wbqv.setUserId(wb.getRegisterUser().getId());
			wbqv.setUserName(wb.getRegisterUser().getUserName());
			//如果用户是商铺用户，则要传一个商铺shopId,如果是普通用户，则shopId=0
			if(wb.getRegisterUser().getUserType().getId()==2L){
				String searchShopHQL="from ShopInfo si where  si.registerUser.id="+wb.getRegisterUser().getId(); 
				ShopInfo s=shopInfoService.get(searchShopHQL);
				if(s!=null){
					wbqv.setShopId(s.getId());
					wbqv.setUserName(s.getShopName());
				}
			}else{
				
				wbqv.setShopId(0L);
			}
			wbqv.setUserType(wb.getRegisterUser().getUserType().getId());
			wbqv.setSendTime(wb.getDateCreated());
			wbqv.setUserHeadPortrait(wb.getRegisterUser().getImgUrl());
			wbqv.setUserMiniHeadPortrait(wb.getRegisterUser().getMiniImgUrl());
			
			//如果wb的originalWeiboId!=0,则查出原始微博
			WeiBo originalWeiBo=null;
			if(wb.getOriginalWeiboId()!=0){
				String searchOriginalWeiBoHQL="from WeiBo wb left join fetch wb.registerUser r left join fetch r.role left join fetch r.userType where wb.id="+wb.getOriginalWeiboId();
				originalWeiBo=weiBoService.get(searchOriginalWeiBoHQL);
				//初始微博已被删除
				if(originalWeiBo==null){
					originalWeiBo=new WeiBo();
					originalWeiBo.setId(-1L);
					//originalWeiBo.setComment("抱歉，原微博已被删除");
				}else{
					originalWeiBo.getRegisterUser().setRole(null);
//					originalWeiBo.getRegisterUser().setUserType(null);
					//如果用户是商铺用户，则要传一个商铺shopId,如果是普通用户，则shopId=0
					if(originalWeiBo.getRegisterUser().getUserType().getId()==2L){
						String searchShopInfoHQL="from ShopInfo si where  si.registerUser.id="+originalWeiBo.getRegisterUser().getId(); 
						ShopInfo si=shopInfoService.get(searchShopInfoHQL);
						if(si!=null){
							originalWeiBo.setShopId(si.getId());
							originalWeiBo.getRegisterUser().setUserName(si.getShopName());
						}
					}else{
						originalWeiBo.setShopId(0L);
					}
				}
				
			}else{
				originalWeiBo=new WeiBo();
				originalWeiBo.setId(0L);
			}
			wbqv.setOriginalWeiBo(originalWeiBo);
			rowsWeiBo.add(wbqv);
			total=1;			
		}

		return SUCCESS;
	}
	
	// modify by zzq 2013-12-2
	public String list() throws Exception
	{		
		String listHQL="";
		String countHQL="";
		RegisterUser u=(RegisterUser) session.get("registerUserLogin");
			//获取响应信任度的好友微博
			if(listType==1){                                                    
				listHQL = "from WeiBo wb left join fetch wb.registerUser r left join fetch r.userType  where r.id in (select f.user1.id from Friend f where f.user12.id="+u.getId()+" and wb.accessathl<=f.user12Level)  order by sendTime desc";
				countHQL = "select count(*) from WeiBo wb  where wb.registerUser.id in (select f.user1.id from Friend f where f.user12.id="+u.getId()+" and wb.accessathl<=f.user12Level)";
				listWeiBoByType(listHQL,countHQL);
				notificationService.clearNotification(u.getId(), 4);
			}
		    //商家微博
			if(listType==2){
				listHQL="from WeiBo wb left join fetch wb.registerUser r left join fetch r.userType  where r.id in (select f.owner.id from Focus f where f.idol.id="+u.getId()+")  order by sendTime desc";
				countHQL="select count(*) from WeiBo wb  where wb.registerUser.id in (select f.owner.id from Focus f where f.idol.id="+u.getId()+")";
                listWeiBoByType(listHQL,countHQL);
				notificationService.clearNotification(u.getId(), 5);
			}
		    //周边微博
			if(listType==3){
				listHQL="from WeiBo wb left join fetch wb.registerUser r left join fetch r.userType where sqrt( ( (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) * (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) ) + ( (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) * (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) ) ) <2 and dateCreated<current_timestamp order by sqrt( ( (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) * (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) ) + ( (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) * (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180))) asc";
				countHQL="select count(*) from WeiBo wb  where sqrt( ( (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) * (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) ) + ( (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) * (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) ) )<2";		
				listWeiBoByType(listHQL,countHQL);
				
			}
			//自己的微博
			if(listType==4){                             
				listHQL = "from WeiBo wb left join fetch wb.registerUser r left join fetch r.userType  where wb.registerUser.id="+u.getId() +" order by sendtime desc";
				countHQL = "select count(*) from WeiBo wb where wb.registerUser.id="+u.getId() +"";
				listWeiBoByType(listHQL,countHQL);
			}			
           //被转发的微博
			if(listType==5){
				listHQL="from WeiBo wb left join fetch wb.registerUser r left join fetch r.userType   where  r.id="+u.getId() +"   and wb.resendtimes>0  order by sendtime desc"; 
				countHQL = "select count(*) from WeiBo wb  where wb.resendtimes>0 and wb.registerUser.id="+u.getId() +"";
				listWeiBoByType(listHQL,countHQL);
				notificationService.clearNotification(u.getId(), 3);
			}
			//某商铺的微博
			if(listType==6){                             
				listHQL = "from WeiBo wb left join fetch wb.registerUser r left join fetch r.userType   where r.id="+weiBo.getAccessathl() +" order by sendtime desc";
				countHQL = "select count(*) from WeiBo wb where wb.registerUser.id="+weiBo.getAccessathl();
				listWeiBoByType(listHQL,countHQL);
			}
		    //周边商铺微博
			if(listType==7){
				listHQL="from WeiBo wb left join fetch wb.registerUser r left join fetch r.userType u where u.id=2 and sqrt( ( (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) * (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) ) + ( (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) * (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) ) ) <2 and dateCreated<current_timestamp order by sqrt( ( (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) * (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) ) + ( (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) * (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180))) asc";
				countHQL="select count(*) from WeiBo wb  left join  wb.registerUser r left join  r.userType u  where u.id=2 and sqrt( ( (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) * (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) ) + ( (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) * (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) ) )<2";		
				listWeiBoByType(listHQL,countHQL);
				
			}
		    //周边普通用户微博
			if(listType==8){
				listHQL="from WeiBo wb left join fetch wb.registerUser r left join fetch r.userType u where u.id=1 and sqrt( ( (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) * (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) ) + ( (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) * (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) ) ) <2 and dateCreated<current_timestamp order by sqrt( ( (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) * (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) ) + ( (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) * (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180))) asc";
				countHQL="select count(*) from WeiBo wb  left join  wb.registerUser r left join  r.userType u  where u.id=1 and sqrt( ( (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) * (("+weiBo.getLongitude()+"-longitude)*PI()*12656*cos((("+weiBo.getLatitude()+"+latitude)/2)*PI()/180)/180) ) + ( (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) * (("+weiBo.getLatitude()+"-latitude)*PI()*12656/180) ) )<2";
				listWeiBoByType(listHQL,countHQL);
				
			}
	
		return SUCCESS;
	}

	private void listWeiBoByType(String listHQL,String countHQL) {
		rowsList = weiBoService.list(listHQL, (page -1)*rows, rows, null);
		domainToView(rowsList,listType);
		total = weiBoService.getTotalCount(countHQL, null);
	}

	
	private void domainToView(List<WeiBo> list,int listType){
		if(list.size()>0){
			rowsWeiBo=new ArrayList<WeiBoQueryView>();
			for(WeiBo wb:list){	
				WeiBoQueryView wbqv=new WeiBoQueryView();
				BeanUtils.copyProperties(wb, wbqv);
				wbqv.setUserId(wb.getRegisterUser().getId());
				wbqv.setUserName(wb.getRegisterUser().getUserName());
				wbqv.setUserType(wb.getRegisterUser().getUserType().getId());
				//如果用户是商铺用户，则要传一个商铺shopId,如果是普通用户，则shopId=0
				if(wb.getRegisterUser().getUserType().getId()==2L){
					String hql="from ShopInfo si where  si.registerUser.id="+wb.getRegisterUser().getId(); 
					ShopInfo s=shopInfoService.get(hql);
					if(s!=null){
						wbqv.setShopId(s.getId());
						wbqv.setUserName(s.getShopName());
					}
				}else{
					wbqv.setShopId(0L);
				}
				wbqv.setUserType(wb.getRegisterUser().getUserType().getId());
				wbqv.setSendTime(wb.getDateCreated());
				wbqv.setUserHeadPortrait(wb.getRegisterUser().getImgUrl());
				wbqv.setUserMiniHeadPortrait(wb.getRegisterUser().getMiniImgUrl());
				//如果wb的originalWeiboId!=0,则查出原始微博
				WeiBo originalWeiBo=null;
				if(wb.getOriginalWeiboId()!=0){
					String hql="from WeiBo wb left join fetch wb.registerUser r left join fetch r.role left join fetch r.userType where wb.id="+wb.getOriginalWeiboId();
					originalWeiBo=weiBoService.get(hql);
					//初始微博已被删除
					if(originalWeiBo==null){
						originalWeiBo=new WeiBo();
						originalWeiBo.setId(-1L);
						//originalWeiBo.setComment("抱歉，原微博已被删除");
					}else{
						originalWeiBo.getRegisterUser().setRole(null);
						//originalWeiBo.getRegisterUser().setUserType(null);
						//如果用户是商铺用户，则要传一个商铺shopId,如果是普通用户，则shopId=0
						if(originalWeiBo.getRegisterUser().getUserType().getId()==2L){
							String searchShopInfoHQL="from ShopInfo si where  si.registerUser.id="+originalWeiBo.getRegisterUser().getId(); 
							ShopInfo si=shopInfoService.get(searchShopInfoHQL);
							if(si!=null){
								originalWeiBo.setShopId(si.getId());
								originalWeiBo.getRegisterUser().setUserName(si.getShopName());
							}
						}else{
							originalWeiBo.setShopId(0L);
						}
						
					}
					
				}else{
					originalWeiBo=new WeiBo();
					originalWeiBo.setId(0L);
				}
				wbqv.setOriginalWeiBo(originalWeiBo);
				//周边微博
				if(listType==3||listType==7||listType==8){
			      double longitude = wb.getLongitude();
			      double latitude = wb.getLatitude();
			      double distanceTemp = Math.sqrt((((weiBo.getLongitude()-longitude)*PI*12656*Math.cos(((weiBo.getLatitude()+latitude)/2)*PI/180)/180) * ((weiBo.getLongitude()-longitude)*PI*12656*Math.cos(((weiBo.getLatitude()+latitude)/2)*PI/180)/180) ) + ( ((weiBo.getLatitude()-latitude)*PI*12656/180) * ((weiBo.getLatitude()-latitude)*PI*12656/180) ) );
		          DecimalFormat format = new DecimalFormat("0.00");
		          String distance = format.format(distanceTemp); //转换成字符串
		          wbqv.setDistance(distance+"km");
				}
                 rowsWeiBo.add(wbqv);
	}
		}
		}
	
	
	//ADD BY JH
	private long weiboId;
	
	@JSON(serialize=false)
	public long getWeiboId() {
		return weiboId;
	}

	public void setWeiboId(long weiboId) {
		this.weiboId = weiboId;
	}

	//ADD BY JH END
	
	
	public String add() throws Exception
	{  

		
		RegisterUser u=(RegisterUser) session.get("registerUserLogin");
		weiBo.setDateCreated(new Date());
		weiBo.setRegisterUser(u);
		weiBo.setOriginalWeiboId(0L);
		
		//modify by zzq 2013-12-7

		
		//查询好友权限大于等于微博权限的好友
		if(u.getUserType().getId()==1){
			String searchFriendsHQL="from Friend f left join fetch f.user12 where f.user12Level>="+weiBo.getAccessathl()+" and f.user1.id="+u.getId();
			ArrayList<Friend> friendList=(ArrayList<Friend>) friendService.list(searchFriendsHQL);
			int length=0;
			if((length=friendList.size())>0){
				long[] friendIds=new long[length];
				for(int i=0;i<friendList.size();i++){
					friendIds[i]=friendList.get(i).getUser12().getId();
				}
				sendWeiBoImg();
				weiBoService.add(weiBo,null,null,friendIds,4);
			}else{
				sendWeiBoImg();
				weiBoService.save(weiBo);
			}
			
		}
		if(u.getUserType().getId()==2){
			//查出商铺微博的粉丝
			String searchFansHQL="from Focus f left join fetch f.idol left join fetch f.owner r where r.id="+u.getId();
			ArrayList<Focus> fansList=(ArrayList<Focus>) focusService.list(searchFansHQL);
			int length=0;
			if(fansList!=null&&(length=fansList.size())>0){
				long[] fansIds=new long[length];
				for(int i=0;i<fansList.size();i++){
					fansIds[i]=fansList.get(i).getIdol().getId();
				}
				sendWeiBoImg();
				weiBoService.add(weiBo,null,null,fansIds,5);
			}else{
				sendWeiBoImg();
				weiBoService.save(weiBo);
			}
			
		}

		
		//EDIT BY JH 2013-11-12 23:52
		//转发
		if(weiboId!=0){
			String hql = "from WeiBo wb left join fetch wb.registerUser where wb.id="+weiboId;
			WeiBo oldWeiBo = weiBoService.get(hql);
			oldWeiBo.setResendtimes(oldWeiBo.getResendtimes()+1);
			long transmits[]=new long[]{oldWeiBo.getRegisterUser().getId()};
			
			long originalWeiBoId=oldWeiBo.getOriginalWeiboId();
			WeiBo originalWeiBo=null;
			if(originalWeiBoId!=0){
				weiBo.setOriginalWeiboId(originalWeiBoId);
				hql="from WeiBo wb left join fetch wb.registerUser where wb.id="+originalWeiBoId;
				originalWeiBo=weiBoService.get(hql);
			}else{
				weiBo.setOriginalWeiboId(oldWeiBo.getId());
			}
			weiBoService.add(weiBo,oldWeiBo,originalWeiBo,transmits,3);
		}
		
		return SUCCESS;
	}
	
	private void sendWeiBoImg() throws Exception{
		//发送图片微博
		if(getFile()!=null){
			String url="";  
			String urlMin="";
			String fileName ="";
			fileName = DateTimeUtils.getTimeStamp() + weiBo.getRegisterUser().getId()+getFileFileName().substring(getFileFileName().lastIndexOf("."));
			url = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("")+File.separator+Upload.WEIBO_IMAGE_URL+fileName;
			urlMin = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("")+File.separator+Upload.WEIBO_MINIMAGE_URL+fileName;
			weiBo.setPicture(Upload.WEIBO_IMAGE_URL+fileName);
			weiBo.setMiniImgUrl(Upload.WEIBO_MINIMAGE_URL+fileName);
			Upload.save(url, getFile());
			Thumbnails.of(url).size(120,200).keepAspectRatio(true).toFile(urlMin);
		}
	}
	public String modify() throws Exception
	{	
		
		RegisterUser u=(RegisterUser) session.get("registerUserLogin");
		weiBo.setRegisterUser(u);
		WeiBo weiBoDataBase = weiBoService.get(WeiBo.class, weiBo.getId());
		BeanUtils.copyProperties(weiBo, weiBoDataBase,new String[]{"dateCreated"});
		try {
			weiBoService.update(weiBoDataBase);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String delete() throws Exception{	
		
		RegisterUser u=(RegisterUser) session.get("registerUserLogin");
		weiBo.setRegisterUser(u);
		weiBoService.delete(WeiBo.class, weiBo.getId());
		return SUCCESS;
	}

}
