package com.shop.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.Criterion;

import com.shop.Filter;
import com.shop.Order;
import com.shop.Page;
import com.shop.exception.DBException;

/**
 * <b>一句话描述该类</b>
 * <p>
 * 描述:<br>
 * 
 * @author 威 
 * <br>2019年10月26日 下午6:04:32 
 * @see
 * @since 1.0
 * @param <T>
 */
public interface BaseService<T>{

	/**
	 * 保存对象
	 * 
	 * @param entity
	 */
	public Serializable save(T entity) throws DBException;

	/**
	 * 保存对象
	 * 
	 * @param entity
	 * @param session
	 * @return
	 */
	public Serializable save(T entity, Session session) throws DBException;

	/**
	 * 更新对象
	 * 
	 * @param entity
	 * @throws DBException
	 */
	public void update(T entity) throws DBException;

	/**
	 * 更新对象
	 * 
	 * @param entity
	 * @throws DBException
	 */
	public void update(T entity, Session session) throws DBException;

	/**
	 * 更新对象
	 * 
	 * @param entity
	 * @throws DBException
	 */
	public void merge(T entity) throws DBException;

	/**
	 * 删除对象
	 * 
	 * @param entity
	 * @throws DBException
	 */
	public void delete(T entity) throws DBException;

	/**
	 * 批量删除
	 * 
	 * @param ids
	 * @throws DBException
	 */
	public void delBatch(Serializable[] ids) throws DBException;

	/**
	 * 通过sql获取对象
	 * 
	 * @param hql
	 * @return
	 */
	public T find(String hql) throws DBException;

	/**
	 * 根据序列化load对象 
	 * 
	 * @param id
	 * @return
	 * @throws DBException
	 */
	public T load(Serializable id) throws DBException;

	/**
	 * 根据序列化id获取对象
	 * 
	 * @param id
	 * @return
	 * @throws DBException
	 */
	public T get(Serializable id) throws DBException;

	/**
	 * 根据id获取对象
	 * 
	 * @param c
	 * @param id
	 * @return
	 * @throws DBException
	 */
	public T get(Class<T> c, Serializable id) throws DBException;

	/**
	 * 获取对象，根据多参数
	 * 
	 * @param hql
	 * @param values
	 * @return
	 * @throws DBException
	 */
	public T find(String hql, String... values) throws DBException;

	/**
	 * 通过参数获取对象
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public T find(String hql, Map<String, Object> params) throws DBException;

	/**
	 * 通过参数获取对象
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public T find(String hql, Map<String, Object> params, Session session) throws DBException;

	/**
	 * 根据条件获取对象
	 * 
	 * @param filters
	 * @return
	 * @throws DBException
	 */
	public T findUniqueByFilter(List<Filter> filters) throws DBException;

	/**
	 * 根据条件获取列表
	 * 
	 * @param first
	 * @param count
	 * @param filters
	 * @param orders
	 * @return
	 * @throws DBException
	 */
	public List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders) throws DBException;
	
	/**
	 * 根据条件获取列表
	 * @param first
	 * @param count
	 * @param filters
	 * @param orders
	 * @param specialCondetions 特殊条件
	 * @return
	 * @throws DBException
	 */
	public List<T> findList(Integer first,Integer count,List<Filter> filters,List<Order> orders,String specialCondetions)  throws DBException;


	/**
	 * 通过hql和条件获取对象列表
	 * 
	 * @param hql
	 * @param values
	 * @return
	 * @throws DBException
	 */
	public List<T> findList(String hql, Object... values) throws DBException;

	/**
	 * 通过hql和条件获取对象列表
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @throws DBException
	 */
	public List<T> findList(String hql, Map<String, Object> params) throws DBException;

	/**
	 * 根据对象获取所有列表
	 * 
	 * @return
	 * @throws DBException
	 */
	public List<T> findAll() throws DBException;

	//老建峰指导写出来的
	public void updateWithTransction(T entity) throws DBException;
	/**
	 * 根据对象获取所有列表
	 * 
	 * @param c
	 * @return
	 * @throws DBException
	 */
	public List<T> findAll(Class<T> c) throws DBException;

	/**
	 * 根据属性值获取对象列表
	 * 
	 * @param proerty
	 * @param value
	 * @return
	 * @throws DBException
	 */
	public List<T> findByProperty(String property, Object value) throws DBException;

	/**
	 * 根据属性判断是否存在
	 * 
	 * @param property
	 * @param value
	 * @return
	 * @throws DBException
	 */
	public boolean propertyExists(String property, Object value) throws DBException;

	/**
	 * 根据属性值获取对象列表
	 * 
	 * @param c
	 * @param proerty
	 * @param value
	 * @return
	 * @throws DBException
	 */
	public List<T> findByProperty(Class<T> c, String property, Object value) throws DBException;

	/**
	 * 根据属性值获取对象
	 * 
	 * @param proerty
	 * @param value
	 * @return
	 * @throws DBException
	 */
	public T findUniqueByProperty(String property, Object value) throws DBException;

	/**
	 * 根据属性值获取对象
	 * 
	 * @param c
	 * @param proerty
	 * @param value
	 * @return
	 * @throws DBException
	 */
	public T findUniqueByProperty(Class<T> c, String property, Object value) throws DBException;

	/**
	 * 根据条件查询对象
	 * 
	 * @param criterion
	 * @return
	 * @throws DBException
	 */
	public List<T> findByCriteria(Criterion... criterions) throws DBException;

	/**
	 * 根据属性值获取统计条数
	 * 
	 * @param property
	 * @param value
	 * @return
	 * @throws DBException
	 */
	public long countByProperty(String property, Object value) throws DBException;

	/**
	 * 获取统计个数
	 * 
	 * @param hql
	 * @param values
	 * @return
	 * @throws DBException
	 */
	public long count(String hql, String... values) throws DBException;

	/**
	 * 获取统计个数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws DBException
	 */
	public long countSql(String sql, Map<String, Object> params) throws DBException;

	/**
	 * 获取统计个数
	 * 
	 * @param sql
	 * @param values
	 * @return
	 * @throws DBException
	 */
	public long countSql(String sql, String... values) throws DBException;

	/**
	 * 获取统计个数
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @throws DBException
	 */
	public long count(String hql, Map<String, Object> params) throws DBException;

	/**
	 * 根据条件获取分页对象 只针对单个实体
	 * 
	 * @param page
	 * @param hql
	 * @param values
	 * @return
	 * @throws DBException
	 */
	public Page<T> findPage(Page page) throws DBException;

	/**
	 * 根据条件获取分页对象-可查多对象关联
	 * 
	 * @param page
	 * @param selectConditions
	 * @param tables
	 *            表关联 例如UserInfo,Role
	 * @return
	 * @throws DBException
	 */
	public Page<T> findPage(Page page, String selectConditions, String tables) throws DBException;

	/**
	 * 根据条件获取分页对象-可查多对象关联
	 * 
	 * @param page
	 * @param selectConditions
	 * @param tables
	 *            表关联 例如UserInfo,Role
	 * @param specialCondetions
	 *            特殊条件 例如or
	 * @return
	 * @throws DBException
	 */
	public Page<T> findPage(Page page, String selectConditions, String tables, String specialCondetions) throws DBException;

	/**
	 * 根据条件获取分页对象
	 * 
	 * @param page
	 * @param selectConditions
	 *            查询获取对象 默认为空（查询所有字段）
	 * @param hql
	 * @param values
	 * @return
	 * @throws DBException
	 */
	public Page<T> findPage(Page page, String selectConditions, String hql, String... values) throws DBException;

	/**
	 * 根据条件获取分页对象
	 * 
	 * @param page
	 * @param selectConditions
	 *            查询获取对象 默认为空（查询所有字段）
	 * @param hql
	 * @param params
	 * @return
	 * @throws DBException
	 */
	public Page<T> findPage(Page page, String selectConditions, String hql, Map<String, Object> params) throws DBException;

	/**
	 * 根据条件获取分页对象
	 * 
	 * @param page
	 * @param selectConditions
	 *            查询获取对象 默认为空（查询所有字段）
	 * @param hql
	 * @param values
	 * @return
	 * @throws DBException
	 */
	public Page<T> findPageBySql(Page page, String selectConditions, String sql, String... values) throws DBException;

	/**
	 * 根据条件获取分页对象
	 * 
	 * @param page
	 * @param selectConditions
	 *            查询获取对象 默认为空（查询所有字段）
	 * @param hql
	 * @param values
	 * @param specialCondetions
	 *            为查询条件
	 * @return
	 * @throws DBException
	 */
	public Page<Object> findPageBySql(Page page, String selectConditions, String sql, String specialCondetions) throws DBException;

	/**
	 * 根据条件获取分页对象
	 * 
	 * @param page
	 * @param selectConditions
	 *            查询获取对象 默认为空（查询所有字段）
	 * @param hql
	 * @param params
	 * @return
	 * @throws DBException
	 */
	public Page<T> findPageBySql(Page page, String selectConditions, String sql, Map<String, Object> params) throws DBException;

	/**
	 * 更新hql
	 * 
	 * @param hql
	 * @param values
	 * @return
	 * @throws DBException
	 */
	public long update(String hql, String... values) throws DBException;

	/**
	 * sql更新
	 * 
	 * @param sql
	 * @param values
	 * @return
	 * @throws DBException
	 */
	public long updateBySql(String sql, String... values) throws DBException;

	/**
	 * 更新hql
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @throws DBException
	 */
	public long update(String hql, Map<String, Object> params) throws DBException;

	/**
	 * 更新hql
	 * 
	 * @param hql
	 * @param params
	 * @param session
	 * @return
	 * @throws DBException
	 */
	public long update(String hql, Map<String, Object> params, Session session) throws DBException;

	/**
	 * sql更新
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws DBException
	 */
	public long updateBySql(String sql, Map<String, Object> params) throws DBException;

	/**
	 * sql更新
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws DBException
	 */
	public long updateBySql(String sql, Map<String, Object> params, Session session) throws DBException;

	/**
	 * 更新hql
	 * 
	 * @param hql
	 * @param params
	 * @return
	 * @throws DBException
	 */
	public long updateByIn(String hql, Map<String, Object> params) throws DBException;

	/**
	 * 更新hql
	 * 
	 * @param hql
	 * @param params
	 * @param session
	 * @return
	 * @throws DBException
	 */
	public long updateByIn(String hql, Map<String, Object> params, Session session) throws DBException;

	/**
	 * 根据sql获取对象列表
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws DBException
	 */
	public List<Object[]> findObjectListBySql(String sql, Map<String, Object> params) throws DBException;

	/**
	 * 根据sql获取对象
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws DBException
	 */
	public Object[] findObjectBySql(String sql, Map<String, Object> params) throws DBException;

	/**
	 * 根据sql获取对象分页列表
	 * 
	 * @param sql
	 *            查询语句（不含查询内容）
	 * @param selectConditions
	 *            查询内容
	 * @param orderString
	 *            排序字符串
	 * @param params
	 *            查询参数值
	 * @param page
	 *            当前页,不传默认1
	 * @param pageSize
	 *            每页记录数,不传默认10
	 * @return total记录总数，content记录列表（List<Object[]>形式），page 当前页，pageSize 每页记录数
	 * @throws DBException
	 */
	public Map<String, Object> findObjPageBySql(String sql, String selectConditions, String orderString, Map<String, Object> params, Integer page,
			Integer pageSize) throws DBException;

	/**
	 * 刷新实体对象
	 * 
	 * @param entity
	 *            实体对象
	 */
	void refresh(T entity);

	/**
	 * 清除缓存
	 */
	void clear();

	/**
	 * 同步数据
	 */
	void flush();

	/**
	 * 清除缓存
	 */
	void evict(T entity);

	/**
	 * 根据条件获取分页对象 只针对单个实体 -- 查询时间类型转换
	 * 
	 * @param page
	 * @param hql
	 * @param values
	 * @return
	 * @throws DBException
	 */
	public Page<T> findPageByDateType(Page page, String selectConditions, String tables, String specialCondetions, String dateType) throws DBException;

	public List<T> findListByIn(String hql, Map<String, Object> params) throws DBException;
}