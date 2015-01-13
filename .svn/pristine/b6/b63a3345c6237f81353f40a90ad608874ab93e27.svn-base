package com.huawei.service.impl;

import java.util.List;

import org.hibernate.Query;

import com.huawei.domain.Coupon;
import com.huawei.domain.ExchangedCou;
import com.huawei.service.ICouponService;
import com.huawei.service.IExchangedCouService;

public class ExchangedCouServiceImpl<T extends ExchangedCou> extends ServiceImpl<T> implements IExchangedCouService<T> {

	private ICouponService<Coupon> couponService;
	
	public void setCouponService(ICouponService<Coupon> couponService) {
		this.couponService = couponService;
	}

	@Override
	public void exchanged(ExchangedCou exchangedCou) {
		String hql = "from ExchangedCou e where e.coupon.id="+exchangedCou.getCoupon().getId()+" and e.registerUser.id="+exchangedCou.getRegisterUser().getId()+"";
		List<ExchangedCou> list = dao.createQuery(hql).list();
		if(list.size()==0){
		dao.save((T) exchangedCou);
		}else{
			String hql2 ="update ExchangedCou e set e.count=e.count+"+exchangedCou.getCount()+" where e.registerUser.id="+exchangedCou.getRegisterUser().getId()+" and e.coupon.id="+exchangedCou.getCoupon().getId()+"";
			dao.createQuery(hql2).executeUpdate();
		}
		couponService.exchanged(exchangedCou);
	}

	@Override
	public void useCoupon(ExchangedCou exchangedCou) {
		// TODO Auto-generated method stub
		String hql = "update ExchangedCou e set e.count=e.count-"+exchangedCou.getCount()+" where e.registerUser.id="+exchangedCou.getRegisterUser().getId()+" and e.coupon.id="+exchangedCou.getCoupon().getId()+"";
		Query query = dao.createQuery(hql);
		query.executeUpdate();
	}
	
}
