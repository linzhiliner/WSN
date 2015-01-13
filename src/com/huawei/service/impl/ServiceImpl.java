package com.huawei.service.impl;

import java.io.Serializable;
import java.util.List;

import com.huawei.dao.IDao;
import com.huawei.service.IService;

public class ServiceImpl<T> implements IService<T> {
	
	protected IDao<T> dao;

	public IDao<T> getDao() {
		return dao;
	}

	public void setDao(IDao<T> dao) {
		this.dao = dao;
	}
	@Override
	public T get(Class<T> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return dao.get(clazz, id);
	}

	@Override
	public T get(String hql) {
		// TODO Auto-generated method stub
		return dao.get(hql);
	}

	@Override
	public void save(T baseBean) {
		// TODO Auto-generated method stub
		dao.save(baseBean);
		
	}

	@Override
	public void update(T baseBean) {
		// TODO Auto-generated method stub
		dao.update(baseBean);
		
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		dao.delete(baseBean);
		
	}

	@Override
	public void delete(Class<T> clazz, Serializable id) {
		// TODO Auto-generated method stub
		dao.delete(clazz, id);
		
	}

	@Override
	public List<T> list(String hql) {
		// TODO Auto-generated method stub
		return dao.list(hql);
	}

	@Override
	public int getTotalCount(String hql, Object... params) {
		// TODO Auto-generated method stub
		return dao.getTotalCount(hql, params);
	}

	@Override
	public List<T> list(String hql, int firstResult, int maxSize,
			Object... params) {
		// TODO Auto-generated method stub
		return dao.list(hql, firstResult, maxSize, params);
	}

}
