package ll.dao.impl;

import java.io.Serializable;
import java.util.List;

import ll.dao.IDao;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class DaoImpl<T> extends HibernateDaoSupport implements IDao<T> {

	@Override
	public T get(Class<T> clazz, Serializable id) {
		// TODO Auto-generated method stub
		return (T) getHibernateTemplate().get(clazz, id);
	}

	@Override
	public T get(String hql) {
		// TODO Auto-generated method stub
		return (T) createQuery(hql).setMaxResults(1).uniqueResult();
	}

	@Override
	public void save(T baseBean) {
		// TODO Auto-generated method stub
		
		getHibernateTemplate().save(baseBean);
		
	}

	@Override
	public void update(T baseBean) {
		// TODO Auto-generated method stub
		
		getHibernateTemplate().update(baseBean);
		
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(baseBean);
	}

	@Override
	public void delete(Class<T> clazz, Serializable id) {
		// TODO Auto-generated method stub
		getHibernateTemplate().delete(getHibernateTemplate().load(clazz, id));
		
	}

	@Override
	public List<T> list(String hql) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().find(hql);
	}

	@Override
	public int getTotalCount(String hql, Object... params) {
		// TODO Auto-generated method stub
		Query query = createQuery(hql);
		for (int i = 0; params != null && i < params.length; i++)
			query.setParameter(i + 1, params[i]);
		Object obj = createQuery(hql).uniqueResult();
		return ((Long) obj).intValue();
	}

	@Override
	public List<T> list(String hql, int firstResult, int maxSize,
			Object... params) {
		Query query = createQuery(hql);
		for (int i = 0; params != null && i < params.length; i++)
			query.setParameter(i + 1, params[i]);
		List<T> list = createQuery(hql).setFirstResult(firstResult)
				.setMaxResults(maxSize).list();
		return list;
	}

	@Override
	public Query createQuery(String hql) {
		// TODO Auto-generated method stub
		return getSession().createQuery(hql);
	}

}
