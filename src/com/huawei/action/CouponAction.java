package com.huawei.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.BeanUtils;

import com.huawei.domain.Coupon;
import com.huawei.service.ICouponService;
import com.opensymphony.xwork2.ModelDriven;

public class CouponAction extends BaseAction implements ModelDriven<Coupon> {
	private Coupon coupon = new Coupon();
	private ICouponService<Coupon> couponService;
	int total;
	List<Coupon> rowsList;
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	@JSON(name="rows")
	public List<Coupon> getRowsList() {
		return rowsList;
	}

	public void setRowsList(List<Coupon> rowsList) {
		this.rowsList = rowsList;
	}

	@JSON(serialize=false)
	public Coupon getModel() {
		// TODO Auto-generated method stub
		return coupon;
	}

	public void setCouponService(ICouponService<Coupon> couponService) {
		this.couponService = couponService;
	}

	public String list() throws Exception
	{
		String hql = "from Coupon c  left join fetch c.registerUser r left join fetch r.role left join fetch r.userType order by c.id desc";
		rowsList = couponService.list(hql, (page -1)*rows, rows, null);
		hql = "select count(*) from Coupon c ";
		total =couponService.getTotalCount(hql, null);
		return SUCCESS;
	}
	
	
	public String listSeller() throws Exception
	{
		String hql = "from Coupon c left join fetch c.registerUser r left join fetch r.role left join fetch r.userType where c.registerUser.id="+coupon.getRegisterUser().getId()+" order by c.id desc";
		rowsList = couponService.list(hql, (page -1)*rows, rows, null);
		hql = "select count(*) from Coupon c where c.registerUser.id="+coupon.getRegisterUser().getId()+"";
		total =couponService.getTotalCount(hql, null);
		return SUCCESS;
	}
	
	public String add() throws Exception
	{
		coupon.setDateCreated(new Date());  
		couponService.save(coupon);
		return SUCCESS;
	}
	
	public String modify() throws Exception
	{
		Coupon couponInDataBase = couponService.get(Coupon.class, coupon.getId());
		BeanUtils.copyProperties(coupon, couponInDataBase,new String[]{"dateCreated"});
		couponService.update(couponInDataBase);
		return SUCCESS;
	}
	
	public String delete() throws Exception
	{
		couponService.delete(Coupon.class, coupon.getId());
		return SUCCESS;
	}


}
