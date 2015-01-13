package com.huawei.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import com.huawei.action.domain.view.CommentQueryView;
import com.huawei.action.domain.view.WeiBoQueryView;
import com.huawei.domain.Comment;
import com.huawei.domain.FriendApply;
import com.huawei.domain.Notification;
import com.huawei.domain.RegisterUser;
import com.huawei.domain.ShopInfo;
import com.huawei.domain.WeiBo;
import com.huawei.service.ICommentService;
import com.huawei.service.IFriendApplyService;
import com.huawei.service.INotificationService;
import com.huawei.service.IShopInfoService;
import com.huawei.service.IWeiBoService;
import com.opensymphony.xwork2.ModelDriven;

public class CommentAction extends BaseAction implements ModelDriven<Comment>{
	
	private Comment comment=new Comment();
	private ICommentService<Comment> commentService;
	private IWeiBoService<WeiBo> weiBoService;
	private IFriendApplyService<FriendApply> friendApplyService;
	private INotificationService<Notification> notificationService;
	public void setNotificationService(
			INotificationService<Notification> notificationService) {
		this.notificationService = notificationService;
	}
	public void setWeiBoService(IWeiBoService<WeiBo> weiBoService) {
		this.weiBoService = weiBoService;
	}
	private IShopInfoService<ShopInfo> shopInfoService;
	
	public void setShopInfoService(IShopInfoService<ShopInfo> shopInfoService) {
		this.shopInfoService = shopInfoService;
	}
	
	int total;
	List<CommentQueryView> rowsList;
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@JSON(name="rows")
	public List<CommentQueryView> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<CommentQueryView> rowsList) {
		this.rowsList = rowsList;
	}

	@JSON(serialize=false)
	public Comment getModel(){
		return comment;
	}
	
	/**
	 * 2013-12-2
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception{
		if(comment!=null){
			String searchCommentHQL="";
			String countCommentHQL="";
			//查询具体微博的评论
			if(comment.getWeibo()!=null&&comment.getWeibo().getId()!=0){
				searchCommentHQL="from Comment c left join fetch c.registerUser r left join fetch r.userType left join fetch c.weibo left join fetch c.receiver cr left join fetch cr.userType left join fetch c.relatedComment left join fetch c.weibo where c.weibo.id="+comment.getWeibo().getId()+" order by c.dateCreated desc";
				countCommentHQL="select count(*) from Comment c where c.weibo.id="+comment.getWeibo().getId()+"";
			
			//查询关于我的所有评论
			}else if(comment.getWeibo()!=null){
				RegisterUser u=(RegisterUser) session.get("registerUserLogin");
				//searchCommentHQL="from Comment c left join fetch c.receiver r left join fetch c.registerUser cr left join fetch c.relatedComment left join fetch c.weibo  w  where r.id="+u.getId()+" order by c.dateCreated desc";
				
				searchCommentHQL="from Comment c left join fetch c.receiver r left join fetch r.userType left join fetch c.registerUser cr left join fetch cr.userType left join fetch c.relatedComment left join fetch c.weibo  w "
						        +" where  (r.id="+u.getId()+" and cr.id!="+u.getId()+") "
						        +" or  ((r.id!="+u.getId()+" and cr.id!="+u.getId()+") and w.id in (";
				countCommentHQL="select count(*) from Comment c left join  c.receiver r left join  c.registerUser cr left join  c.weibo  w "
						        +" where  (r.id="+u.getId()+" and cr.id!="+u.getId()+") "
						        +" or  ((r.id!="+u.getId()+" and cr.id!="+u.getId()+") and w.id in (";
				
				String myAllWeiBoHQL="from WeiBo w left join fetch w.registerUser r where r.id="+u.getId();
				List<WeiBo> l=weiBoService.list(myAllWeiBoHQL);
				if(l.size()>0){
					for(int i=0;i<l.size();i++){
						if(i>0){
							searchCommentHQL+=",";
							countCommentHQL+=",";
						}
						searchCommentHQL+="'"+l.get(i).getId()+"'";
						countCommentHQL+="'"+l.get(i).getId()+"'";
					}
					searchCommentHQL+=")) order by c.dateCreated desc";
					countCommentHQL+=")) ";
				}
		
				
				notificationService.clearNotification(u.getId(), 2);
			}
			searchCommentByHQL(searchCommentHQL,countCommentHQL);	
		}
		
		return SUCCESS;
	}
	
	/**
	 * 2013-12-2
	 * @param listhql
	 * @param totalhql
	 */
	private void searchCommentByHQL(String listhql,String totalhql){
		List<Comment> list=null;
		try {
			list=commentService.list(listhql, (page -1)*rows, rows, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(list.size()>0){
			rowsList=new ArrayList<CommentQueryView>();
			for(Comment c: list){
				CommentQueryView cqu=new CommentQueryView();
				cqu.setId(c.getId());
				
				CommentQueryView relatedCom=null;
                if(c.getRelatedComment()!=null){//对评论的回复
                  Comment com=c.getRelatedComment();
                  if(com!=null){
                	  relatedCom=new CommentQueryView();
                	  if(com.getRelatedComment()==null){
                        relatedCom.setReceiverId(0L);
                        relatedCom.setId(com.getId()); 
                        relatedCom.setComment(com.getComment());
                       
                	  }else{
                          relatedCom.setReceiverId(com.getReceiver().getId());
                          relatedCom.setComment(com.getComment());
                          relatedCom.setReceiverName(com.getReceiver().getUserName());
                          relatedCom.setId(com.getId()); 
                          
                	  }
                	  relatedCom.setRegisterUserId(com.getRegisterUser().getId());
                	  relatedCom.setRegisterUserName(com.getRegisterUser().getUserName());
                	  relatedCom.setRegisterUserImg(com.getRegisterUser().getImgUrl());
                	  relatedCom.setRegisterUserMiniImg(com.getRegisterUser().getMiniImgUrl());
      				
                  }
   				//相关联的评论中评论接收者为商铺
				if(com.getReceiver().getUserType().getId()==2L){
					String  searchShopInfoHQL="from ShopInfo si where si.registerUser.id="+com.getReceiver().getId();
					ShopInfo si=shopInfoService.get(searchShopInfoHQL);
					if(si!=null){
						//商铺id
						relatedCom.setReceiverShopId(si.getId());
						//商铺名
						relatedCom.setReceiverName(si.getShopName());
					}
				}else{
					//商铺id
					relatedCom.setReceiverShopId(0L);
				}
				//相关评论中被评论者的类型
				relatedCom.setReceiverType(com.getReceiver().getUserType().getId());
                  
    				//评论者为商铺用户
    				if(com.getRegisterUser().getUserType().getId()==2L){
    					String  searchShopInfoHQL="from ShopInfo si where si.registerUser.id="+com.getRegisterUser().getId();
    					ShopInfo si=shopInfoService.get(searchShopInfoHQL);
    					if(si!=null){
    						//商铺id
    						relatedCom.setRegisterUserShopId(si.getId());
    						//商铺名
    						relatedCom.setRegisterUserName(si.getShopName());
    					}
    				}else{
						//商铺id
						relatedCom.setRegisterUserShopId(0L);
					}
   
    				//相关评论中评论者的类型
    				relatedCom.setRegisterUserType(c.getRegisterUser().getUserType().getId());
                                   

                  c.setRelatedComment(null);
                }
                cqu.setRelatedComment(relatedCom);
                //微博相关信息
                WeiBo w=c.getWeibo();
                if(w!=null){
        			WeiBoQueryView wbqv=new WeiBoQueryView();
        			BeanUtils.copyProperties(w, wbqv);
        			wbqv.setUserId(w.getRegisterUser().getId());
        			wbqv.setUserName(w.getRegisterUser().getUserName());
        			wbqv.setSendTime(w.getDateCreated()); 
        			cqu.setWeibo(wbqv);
                }
                
                //评论者
            	RegisterUser r=c.getRegisterUser();
            	cqu.setRegisterUserId(r.getId());
            	cqu.setRegisterUserName(r.getUserName());
            	cqu.setRegisterUserImg(r.getImgUrl());
            	cqu.setRegisterUserMiniImg(r.getMiniImgUrl());
            	
				//评论者为商铺用户
				if(c.getRegisterUser().getUserType().getId()==2L){
					String  searchShopInfoHQL="from ShopInfo si where si.registerUser.id="+c.getRegisterUser().getId();
					ShopInfo si=shopInfoService.get(searchShopInfoHQL);
					if(si!=null){
						//商铺id
						cqu.setRegisterUserShopId(si.getId());
						//商铺名
						cqu.setRegisterUserName(si.getShopName());
					}
				}else{
					//商铺id
					cqu.setRegisterUserShopId(0L);
				}
            	
				//评论者用户类型
				cqu.setRegisterUserType(c.getRegisterUser().getUserType().getId());
            	//评论对象
            	RegisterUser u=c.getReceiver();
            	cqu.setReceiverId(u.getId());
            	cqu.setReceiverName(u.getUserName());
            	
				//被评论者为商铺用户
				if(c.getReceiver().getUserType().getId()==2L){
					String  searchShopInfoHQL="from ShopInfo si where si.registerUser.id="+c.getReceiver().getId();
					ShopInfo si=shopInfoService.get(searchShopInfoHQL);
					if(si!=null){
						//商铺id
						cqu.setReceiverShopId(si.getId());
						//商铺名
						cqu.setReceiverName(si.getShopName());
					}
				}else{
					//商铺id
					cqu.setReceiverShopId(0L);
				}
				//被评论者用户类型
				cqu.setReceiverType(c.getReceiver().getUserType().getId());
            	
				cqu.setComment(c.getComment());
				cqu.setSendTime(c.getDateCreated());
				cqu.setWeiboId(comment.getWeibo().getId());
				rowsList.add(cqu);
			}
		}
		total = commentService.getTotalCount(totalhql, null);
	}
	
	
	
	public String add() throws Exception{
		comment.setDateCreated(new Date());
		RegisterUser u=(RegisterUser) session.get("registerUserLogin");
		String hql ="from WeiBo wb left join fetch wb.registerUser where  wb.id="+comment.getWeibo().getId()+"";
		RegisterUser r = weiBoService.get(hql).getRegisterUser();
		comment.setRegisterUser(u);
		//如果relatedComment.id=0，则是对微博的评论
		if(comment.getRelatedComment().getId()==0){
			comment.setRelatedComment(null);
		}
		if(comment.getReceiver().getId()!=r.getId()&&(r.getId()!=u.getId())){//评论对象不是发微博者且评论的微博不是当前用户所发
			commentService.addComment(comment, new long[]{comment.getReceiver().getId(),r.getId()});
			
		}else if((r.getId()!=u.getId())||(comment.getReceiver().getId()!=r.getId())){//评论对象不是发微博者或者评论的微博不是当前用户所发
			commentService.addComment(comment, comment.getReceiver().getId());
		}
		else{//评论对象是当前用户
			commentService.addComment(comment,null);
		}
		
		return SUCCESS;
	}
	
	public String modify() throws Exception{
		return SUCCESS;
	}
	
	public String delete() throws Exception{
		commentService.delete(Comment.class, comment.getId());
		return SUCCESS;
	}

	public void setCommentService(ICommentService<Comment> commentService) {
		this.commentService = commentService;
	}
	
	
}