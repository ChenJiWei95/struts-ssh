package com.shop.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.shop.Filter;
import com.shop.Order;
import com.shop.Page;
import com.shop.annotation.RCacheEvict;
import com.shop.annotation.RCacheable;
import com.shop.annotation.RParamer;
import com.shop.dao.BaseDao;
import com.shop.exception.DBException;
import com.shop.service.BaseService;
/**
 * <b>一句话描述该类</b>
 * <p>
 * 描述:<br>
 * 
 * @author 威 
 * <br>2019年10月26日 下午6:04:13 
 * @see 
 * @since 1.0
 * @param <T>
 */
public class BaseServiceImpl<T> implements BaseService<T> {

	/** baseDao */
	private BaseDao<T> baseDao;

	public void setBaseDao(BaseDao<T> baseDao) {
		this.baseDao = baseDao;
	} 

	@Transactional(readOnly = true)
	public long count(String hql, String... values) throws DBException {
		
		return baseDao.count(hql, values);
	}

	@Transactional(readOnly = true)
	public long count(String hql, Map<String, Object> params) throws DBException {
		
		return baseDao.count(hql, params);
	}

	@Transactional(readOnly = true)
	public long countByProperty(String property, Object value) throws DBException {
		
		return baseDao.countByProperty(property, value);
	}
   
	@Transactional(readOnly = true) 
	public long countSql(String sql, Map<String, Object> params) throws DBException {
		
		return baseDao.countSql(sql, params);
	}

	@Transactional(readOnly = true)
	public long countSql(String sql, String... values) throws DBException {
		
		return baseDao.countSql(sql, values);
	}

	@Transactional
	public void delete(T entity) throws DBException {
		
		baseDao.delete(entity);
	}

	@Transactional(readOnly = true)
	public T find(String hql) throws DBException {
		
		return baseDao.find(hql);
	}

	@Transactional(readOnly = true)
	public T get(String id) throws DBException {
		
		return baseDao.get(id);
	}

	@Transactional(readOnly = true)
	public T get(Class<T> c, Serializable id) throws DBException {
		
		return baseDao.get(c, id);
	}
	
	@Transactional(readOnly = true)
	public T find(String hql, String... values) throws DBException {
		return baseDao.find(hql, values);
	}

	@Transactional(readOnly = true)
	public T find(String hql, Map<String, Object> params) throws DBException {
		
		return baseDao.find(hql, params);
	}

	/**
	 * 通过参数获取对象
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public T find(String hql, Map<String, Object> params, Session session) throws DBException {
		return baseDao.find(hql, params, session);
	}

	@Transactional(readOnly = true)
	public List<T> findAll() throws DBException {
		
		return baseDao.findAll();
	}

	@Transactional(readOnly = true) 
	public List<T> findAll(Class<T> c) throws DBException {
		
		return baseDao.findAll(c);
	}

	@Transactional(readOnly = true)
	public List<T> findByCriteria(Criterion... criterions) throws DBException {
		
		return baseDao.findByCriteria(criterions); 
	}

	@Transactional(readOnly = true)
	public List<T> findByProperty(String property, Object value) throws DBException {  
		
		return baseDao.findByProperty(property, value);
	}

	@Transactional(readOnly = true)
	public List<T> findByProperty(Class<T> c, String property, Object value) throws DBException {
		
		return baseDao.findByProperty(c, property, value);
	}

	@Transactional(readOnly = true)
	public List<T> findList(String hql, Object... values) throws DBException {
		
		return baseDao.findList(hql, values);
	}

	@Transactional(readOnly = true)
	public List<T> findList(String hql, Map<String, Object> params) throws DBException {
		
		return baseDao.findList(hql, params);
	}

	@Transactional(readOnly = true)
	public Page<T> findPage(Page page, String selectConditions, String hql, String... values) throws DBException {
		
		return baseDao.findPage(page, selectConditions, hql, values);
	}

	@Transactional(readOnly = true)
	public Page<T> findPage(Page page, String selectConditions, String hql, Map<String, Object> params) throws DBException {
		
		return baseDao.findPage(page, selectConditions, hql, params);
	}

	@Transactional(readOnly = true)
	public Page<T> findPageBySql(Page page, String selectConditions, String sql, String... values) throws DBException {
		
		return baseDao.findPageBySql(page, selectConditions, sql, values);
	}

	@Transactional(readOnly = true)
	public Page<T> findPageBySql(Page page, String selectConditions, String sql, Map<String, Object> params) throws DBException {
		
		return baseDao.findPageBySql(page, selectConditions, sql, params);
	}

	@Transactional(readOnly = true)
	public Page<Object> findPageBySql(Page page, String selectConditions, String sql, String specialCondetions) throws DBException {
		
		return baseDao.findPageBySql(page, selectConditions, sql, specialCondetions);
	}

	@Transactional(readOnly = true)
	public T findUniqueByProperty(String property, Object value) throws DBException {
		
		return baseDao.findUniqueByProperty(property, value);
	}

	@Transactional(readOnly = true)
	public T findUniqueByProperty(Class<T> c, String property, Object value) throws DBException {
		
		return baseDao.findUniqueByProperty(c, property, value);
	}

	@Transactional
	public Serializable save(T entity) throws DBException {
		
		return baseDao.save(entity);
	}

	/**
	 * 保存对象
	 * 
	 * @param entity
	 * @param session
	 * @return
	 */
	@Transactional
	public Serializable save(T entity, Session session) throws DBException {
		return baseDao.save(entity, session);
	}

	@Transactional
//	@RCacheEvict(key="#user.id")
	public void update(T entity) throws DBException {
		
		baseDao.update(entity);

	}
	 
	@Transactional(propagation=Propagation.REQUIRED)
	public void updateWithTransction(T entity) throws DBException {
		
		baseDao.update(entity);

	}
	public void update(T entity, Session session) throws DBException {
		
		baseDao.update(entity, session);

	}

	@Transactional
	public void merge(T entity) throws DBException {
		
		baseDao.merge(entity);

	}

	@Transactional
	public long update(String hql, String... values) throws DBException {
		
		return baseDao.update(hql, values);
	}

	@Transactional
	public long update(String hql, Map<String, Object> params) throws DBException {
		
		return baseDao.update(hql, params);
	}

	@Transactional
	public long update(String hql, Map<String, Object> params, Session session) throws DBException {
		
		return baseDao.update(hql, params, session);
	}

	@Transactional
	public long updateBySql(String sql, String... values) throws DBException {
		return baseDao.updateBySql(sql, values);
	}

	@Transactional
	public long updateBySql(String sql, Map<String, Object> params) throws DBException {
		
		return baseDao.updateBySql(sql, params);
	}

	public long updateBySql(String sql, Map<String, Object> params, Session session) throws DBException {
		return baseDao.updateBySql(sql, params, session);
	}

	@Transactional
	public long updateByIn(String hql, Map<String, Object> params) throws DBException {
		
		return baseDao.updateByIn(hql, params);
	}
	public List<T> findListByIn(String hql, Map<String, Object> params) throws DBException{
		return baseDao.findListByIn(hql, params);
	}
	public long updateByIn(String hql, Map<String, Object> params, Session session) throws DBException {
		
		return baseDao.updateByIn(hql, params);
	}

	public Page<T> findPage(Page page) throws DBException {
		
		return baseDao.findPage(page);
	}

	public Page<T> findPage(Page page, String selectConditions, String tables) throws DBException {
		
		return baseDao.findPage(page, selectConditions, tables);
	}

	public Page<T> findPage(Page page, String selectConditions, String tables, String specialCondetions) throws DBException {
		
		return baseDao.findPage(page, selectConditions, tables, specialCondetions);
	}

	public T load(Serializable id) throws DBException {
		
		return baseDao.load(id);
	}

	public boolean propertyExists(String property, Object value) throws DBException {
		
		return baseDao.propertyExists(property, value);
	}

	@Transactional
	public void delBatch(Serializable[] ids) throws DBException {
		
		baseDao.delBatch(ids);
	}

	public T findUniqueByFilter(List<Filter> filters) throws DBException {
		
		return baseDao.findUniqueByFilter(filters);
	}

	public List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders) throws DBException {
		
		return baseDao.findList(first, count, filters, orders);
	}
	
	@Override
	public List<T> findList(Integer first, Integer count, List<Filter> filters,List<Order> orders, String specialCondetions) throws DBException {
		return baseDao.findList(first, count, filters, orders,specialCondetions);
	}

	@Transactional(readOnly = true)
	public List<Object[]> findObjectListBySql(String sql, Map<String, Object> params) throws DBException {
		return baseDao.findObjectListBySql(sql, params);
	}

	public Object[] findObjectBySql(String sql, Map<String, Object> params) throws DBException {
		return baseDao.findObjectBySql(sql, params);
	}

	public Map<String, Object> findObjPageBySql(String sql, String selectConditions, String orderString, Map<String, Object> params, Integer page,
			Integer pageSize) throws DBException {
		return baseDao.findObjPageBySql(sql, selectConditions, orderString, params, page, pageSize);
	}

	public void clear() {
		baseDao.clear();

	}

	public void flush() {
		baseDao.flush();

	}

	public void refresh(T entity) {
		baseDao.refresh(entity);

	}

	public void evict(T entity) {
		baseDao.evict(entity);

	}

	public Page<T> findPageByDateType(Page page, String selectConditions, String tables, String specialCondetions, String dateType) throws DBException {
		
		return baseDao.findPageByDateType(page, selectConditions, tables, specialCondetions, dateType);
	}

	@Override
	@Transactional(readOnly=true)
	public List<T> findByPage(String hql, int page, int size, Object...values) throws DBException {
		// TODO Auto-generated method stub
		return baseDao.findByPage(hql, page, size, values);
	}

}
