package ll.service;

import java.io.Serializable;
import java.util.List;

public interface IService<T> {

	public   T get(Class<T> clazz, Serializable id);
	
	public  T get(String hql);

	public void save(T baseBean);
	
	public void update(T baseBean);

	public void delete(T baseBean);
	
	public void delete(Class<T> clazz, Serializable id);

	public List<T> list(String hql);

	public int getTotalCount(String hql, Object... params);

	public List<T> list(String hql, int firstResult, int maxSize,
			Object... params);
}
