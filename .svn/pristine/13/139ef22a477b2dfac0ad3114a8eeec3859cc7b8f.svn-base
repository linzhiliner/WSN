package com.huawei.service.impl;

import org.hibernate.Query;

import com.huawei.domain.Faq;
import com.huawei.service.IFaqService;

public class FaqServiceImpl<T extends Faq> extends ServiceImpl<T> implements IFaqService<T>{

	@Override
	public void modifyHandle(String ids) {
		// TODO Auto-generated method stub
		String hql="update Faq set handle=1 where id in ("+ids+")";
		Query query = dao.createQuery(hql);
		query.executeUpdate();
	}

}
