package com.huawei.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.struts2.json.annotations.JSON;
import org.hibernate.Query;
import org.springframework.beans.BeanUtils;

import com.huawei.domain.Coupon;
import com.huawei.domain.ExchangedCou;
import com.huawei.domain.RegisterUser;
import com.huawei.service.ICouponService;
import com.huawei.service.IExchangedCouService;
import com.opensymphony.xwork2.ModelDriven;

public class ExchangedCouAction extends BaseAction implements ModelDriven<ExchangedCou> {
	private ExchangedCou exchangedCou = new ExchangedCou();
	private IExchangedCouService<ExchangedCou> exchangedCouService;
	int total;
	List<ExchangedCou> rowsList;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	@JSON(name="rows")
	public List<ExchangedCou> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<ExchangedCou> rowsList) {
		this.rowsList = rowsList;
	}

	@JSON(serialize=false)
	public ExchangedCou getModel() {
		// TODO Auto-generated method stub
		return exchangedCou;
	}


	public void setExchangedCouService(
			IExchangedCouService<ExchangedCou> exchangedCouService) {
		this.exchangedCouService = exchangedCouService;
	}


	public String list() throws Exception
	{
		RegisterUser u = (RegisterUser)session.get("registerUserLogin");
		long id = u.getId();
		
		String hql = "from ExchangedCou e left join fetch e.coupon c left join fetch e.registerUser r where c.id ="+exchangedCou.getCoupon().getId()+" and r.id="+id+" order by e.id desc";
		rowsList = exchangedCouService.list(hql, (page -1)*rows, rows, null);
		for(int i=0;i<rowsList.size();i++){
			ExchangedCou ec = (ExchangedCou)rowsList.get(i);
			ec.getCoupon().getRegisterUser().setRole(null);
			ec.getCoupon().getRegisterUser().setUserType(null);
			ec.setRegisterUser(null);
		}
		hql = "select count(*) from ExchangedCou e where e.coupon.id="+exchangedCou.getCoupon().getId()+" and e.registerUser.id="+id+"";
		total = exchangedCouService.getTotalCount(hql, null);
		return SUCCESS;
	}
	
	
	
	public String add() throws Exception
	{
		RegisterUser u = (RegisterUser)session.get("registerUserLogin");
		exchangedCou.setRegisterUser(u);
		exchangedCou.setDateCreated(new Date());
		exchangedCouService.exchanged(exchangedCou);
		return SUCCESS;
	}
	
	public String modify() throws Exception
	{
		ExchangedCou exchangedCouInDataBase = exchangedCouService.get(ExchangedCou.class, exchangedCou.getId());
		BeanUtils.copyProperties(exchangedCou, exchangedCouInDataBase,new String[]{"dateCreated"});
		exchangedCouService.update(exchangedCouInDataBase);
		return SUCCESS;
	}
	
	public String delete() throws Exception
	{	
		RegisterUser u = (RegisterUser)session.get("registerUserLogin");
		exchangedCou.setRegisterUser(u);
		exchangedCou.setRegisterUser(u);
		exchangedCouService.useCoupon(exchangedCou);
		return SUCCESS;
	}
	
	public String listMyExchangedCou() throws Exception{
		RegisterUser u = (RegisterUser)session.get("registerUserLogin");
		long id = u.getId();
		
		String hql = "from ExchangedCou e left join fetch e.coupon c left join fetch e.registerUser r where e.registerUser.id="+id+" order by e.id desc";
		rowsList = exchangedCouService.list(hql, (page -1)*rows, rows, null);
		for(int i=0;i<rowsList.size();i++){
			ExchangedCou ec = (ExchangedCou)rowsList.get(i);
			ec.getCoupon().getRegisterUser().setRole(null);
			ec.getCoupon().getRegisterUser().setUserType(null);
			ec.setRegisterUser(null);
		}
		hql = "select count(*) from ExchangedCou e where e.registerUser.id="+id+"";
		total = exchangedCouService.getTotalCount(hql, null);
		return SUCCESS;
	}
}
