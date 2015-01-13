package ll.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;

public interface IDao<T> {
	
	//public T load(Class<T> clazz, Serializable id);
	
	public   T get(Class<T> clazz, Serializable id);
	
	/** 加载满足条件的持久化对象 */
	public  T get(String hql);

	public void save(T baseBean);
	
	public void update(T baseBean);

	public void delete(T baseBean);
	
	/** 删除指定ID的持久化对象 */
	public void delete(Class<T> clazz, Serializable id);
	
	public List<T> list(String hql);

	public int getTotalCount(String hql, Object... params);

	public List<T> list(String hql, int firstResult, int maxSize,
			Object... params);
	
	public Query createQuery(String hql);

}
