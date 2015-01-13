package com.huawei.service.impl;

import org.hibernate.Query;

import com.huawei.domain.Coupon;
import com.huawei.domain.ExchangedCou;
import com.huawei.service.ICouponService;

public class CouponServiceImpl<T extends Coupon> extends ServiceImpl<T> implements
		ICouponService<T> {

	@Override
	public void exchanged(ExchangedCou exchangedCou) {
		// TODO Auto-generated method stub
		String hql = "update Coupon c set c.rest=c.rest-"+exchangedCou.getCount()+" where c.id="+exchangedCou.getCoupon().getId()+"";
		Query query = dao.createQuery(hql);
		query.executeUpdate();
	}

}
