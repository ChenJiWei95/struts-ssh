package com.shop.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.shop.Filter;
import com.shop.Order;
import com.shop.Page;
import com.shop.QueryHelper;
import com.shop.SysConstant;
import com.shop.dao.BaseDao;
import com.shop.exception.DBException;

/**
 * <b></b>
 * <p>
 * 描述:<br>
 * 
 * @author 威 
 * <br>2019年10月18日 下午11:09:04 
 * @see
 * @since 1.0
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements BaseDao<T> {

	/** 实体类类型 */
	private Class<T> entityClass;

	@Autowired
	private SessionFactory sessionFactory;

	public BaseDaoImpl() {
		Type type = getClass().getGenericSuperclass();
		Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
		entityClass = (Class<T>) parameterizedType[0];
	}
 
	/**
	 * 获得当前事物的session
	 * 
	 * @return org.hibernate.Session
	 */
	
	public Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	public void delete(T entity) throws DBException {
		Assert.notNull(entity);
		this.getCurrentSession().delete(entity);
	}

	public T find(String hql) throws DBException {
		return (T) this.getCurrentSession().createQuery(hql).uniqueResult();
	}

	public T get(Serializable id) throws DBException {
		return (T) this.getCurrentSession().get(entityClass, id);
	}

	public T get(Class<T> c, Serializable id) throws DBException {
		return (T) this.getCurrentSession().get(c, id);
	}

	public T find(String hql, String... values) throws DBException {

		Query query = this.getCurrentSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return (T) query.uniqueResult();
	}

	public T find(String hql, Map<String, Object> params) throws DBException {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return (T) query.uniqueResult();
	}

	/**
	 * 通过参数获取对象
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public T find(String hql, Map<String, Object> params, Session session) throws DBException {
		if (null == session) {
			session = this.getCurrentSession();
		}

		Query query = session.createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return (T) query.uniqueResult();
	}

	public Serializable save(T entity) throws DBException {
		Assert.notNull(entity);
		return this.getCurrentSession().save(entity);
	}

	public Serializable save(T entity, Session session) throws DBException {
		Assert.notNull(entity);

		if (null == session) {
			session = this.getCurrentSession();
		}

		return session.save(entity);
	}

	public void merge(T entity) throws DBException {
		Assert.notNull(entity);
		this.getCurrentSession().merge(entity);
	}

	public void update(T entity) throws DBException {
		Assert.notNull(entity);
		this.getCurrentSession().update(entity);
	}

	public void update(T entity, Session session) throws DBException {
		Assert.notNull(entity);
		if (null == session) {
			session = this.getCurrentSession();
		}
		session.update(entity);
	}

	public List<T> findList(String hql, Object... values) throws DBException {
		Query query = this.getCurrentSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return (List<T>) query.list();
	}

	public List<T> findList(String hql, Map<String, Object> params) throws DBException {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return (List<T>) query.list();
	}

	public List<T> findAll() throws DBException {
		return this.getCurrentSession().createCriteria(entityClass).list();
	}

	public List<T> findAll(Class<T> c) throws DBException {
		return this.getCurrentSession().createCriteria(c).list();
	}

	public List<T> findByProperty(String property, Object value) throws DBException {

		Assert.hasText(property, "property must not be empty");
		Assert.notNull(value, "value is required");
		Session currentSession = this.getCurrentSession();
		Criteria createCriteria = currentSession.createCriteria(entityClass);
		//createCriteria.addOrder(Order.asc("name"));////根据名字升序排列
		//Hibernate中共提供了三种检索方式：HQL(Hibernate Query Language)、QBC(Query By Criteria)、QBE(Query By Example)。而今天的这个查询方法属于QBC，牢记牢记。
		SimpleExpression eq = Restrictions.eq(property, value);
//		System.out.println(eq);
		String propertyName = eq.getPropertyName();
//		System.out.println(propertyName);
		List<Object> list = createCriteria.add(Restrictions.eq(property, value)).list();
		for (Object object : list) {
//			System.out.println(object.toString());
		}
		
		return this.getCurrentSession().createCriteria(entityClass).add(Restrictions.eq(property, value)).list();
	}

	public List<T> findByProperty(Class<T> c, String property, Object value) throws DBException {
		Assert.hasText(property, "property must not be empty");
		Assert.notNull(value, "value is required");
		return this.getCurrentSession().createCriteria(c).add(Restrictions.eq(property, value)).list();
	}

	public T findUniqueByProperty(String property, Object value) throws DBException {
		Assert.hasText(property, "property must not be empty");
		Assert.notNull(value, "value is required");
		return (T) this.getCurrentSession().createCriteria(entityClass).add(Restrictions.eq(property, value)).uniqueResult();
	}

	public T findUniqueByProperty(Class<T> c, String property, Object value) throws DBException {
		Assert.hasText(property, "property must not be empty");
		Assert.notNull(value, "value is required");
		return (T) this.getCurrentSession().createCriteria(c).add(Restrictions.eq(property, value)).uniqueResult();
	}

	public List<T> findByCriteria(Criterion... criterions) throws DBException {
		Criteria criteria = this.getCurrentSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria.list();
	}

	public long countByProperty(String property, Object value) throws DBException {

		Assert.hasText(property, "property must not be empty");
		Assert.notNull(value, "value is required");

		return ((Number) (this.getCurrentSession().createCriteria(entityClass).add(Restrictions.eq(property, value)).setProjection(Projections.rowCount())
				.uniqueResult())).longValue();

	}

	public long count(String hql, String... values) throws DBException {

		Query query = this.getCurrentSession().createQuery(SysConstant.ROW_COUNT_PRE + hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return (Long) query.uniqueResult();
	}

	public long count(String hql, Map<String, Object> params) throws DBException {

		if (hql.contains("fetch")) {
			hql = hql.replaceAll("fetch", "");
		}

		/*Query query = this.getCurrentSession().createQuery(SysConstant.ROW_COUNT_PRE + hql);
		System.out.println(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				System.out.println("开始拼接占位符参数...key="+key+"   params.get(key)="+params.get(key));
				query.setParameter(key, params.get(key));
				System.out.println(hql);
			}
			System.out.println(hql);
		}*/
		
		
		Query query = this.getCurrentSession().createQuery(SysConstant.ROW_COUNT_PRE + hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
//				System.out.println(key);
				// query.setParameter(key, params.get(key));
				Object obj = params.get(key);
//				System.err.println(obj.toString());
				// 根据参数类型进行设置，用于支持in查询
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(key, (Object[]) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
		

		//所有占位符拼接完成后进行数据库查询条数
		Object count = query.uniqueResult();

		if (null == count)
			return 0;

		return (Long) count;
	}

	public Integer countSql(String sql, Map<String, Object> params) throws DBException {

		Query query = this.getCurrentSession().createSQLQuery(SysConstant.ROW_COUNT_PRE + sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	public Integer countSql(String sql, String specialCondetions) throws DBException {

		Query query = this.getCurrentSession().createSQLQuery(SysConstant.ROW_COUNT_PRE + sql + " where 1=1 " + specialCondetions);
		// if(params != null && !params.isEmpty()){
		// for(String key : params.keySet()){
		// query.setParameter(key, params.get(key));
		// }
		// }
		return Integer.valueOf(query.uniqueResult().toString());
	}

	public Integer countSql(String sql, String... values) throws DBException {

		Query query = this.getCurrentSession().createSQLQuery(SysConstant.ROW_COUNT_PRE + sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return Integer.valueOf(query.uniqueResult().toString());
	}

	public Page<T> findPage(Page page, String selectConditions, String hql, String... values) throws DBException {
		long total = this.count(hql, values);

		if (page == null) {
			page = new Page();
		}

		Query query = this.getCurrentSession().createQuery(selectConditions + hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}

		query.setFirstResult((page.getPage() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());

		page.setContent(query.list());
		page.setTotal(total);

		return page;
	}

	public Page<T> findPage(Page page, String selectConditions, String hql, Map<String, Object> params) throws DBException {
		// TODO Auto-generated method stub
		long total = this.count(hql, params);

		if (page == null) {
			page = new Page();
		}

		Query query = this.getCurrentSession().createQuery(selectConditions + hql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}

		query.setFirstResult((page.getPage() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());

		page.setContent(query.list());
		page.setTotal(total);

		return page;
	}

	public Page<T> findPageBySql(Page page, String selectConditions, String sql, String... values) throws DBException {
		long total = this.countSql(sql, values);

		if (page == null) {
			page = new Page();
		}

		Query query = this.getCurrentSession().createSQLQuery(selectConditions + sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}

		query.setFirstResult((page.getPage() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());

		page.setContent(query.list());
		page.setTotal(total);

		return page;
	}

	public Page<Object> findPageBySql(Page page, String selectConditions, String sql, String specialCondetions) throws DBException {
		
		if (page == null) {
			page = new Page();
		}
		
		QueryHelper queryHelper = new QueryHelper();
		String paramSql = queryHelper.buildQuery(page.getFilters());
		Map<String, Object> params = queryHelper.getParams();

		if (specialCondetions != null && !"".equals(specialCondetions)) {
			paramSql += specialCondetions;
		}
		
		sql = sql + paramSql;
		
//		System.out.println(sql);
		
		long total = this.countSql(sql, params);
		
		sql += queryHelper.buildOrder(page.getOrders(), page.getAlias());
		
//		System.out.println(sql);

		Query query = this.getCurrentSession().createSQLQuery(selectConditions + sql);
		
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
//				System.out.println(key);
				// query.setParameter(key, params.get(key));
				Object obj = params.get(key);
//				System.out.println(obj);
				// 根据参数类型进行设置，用于支持in查询
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(key, (Object[]) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
//		System.out.println(query);
        //page是当前页数，pageSize是rows为显示条数
		query.setFirstResult((page.getPage() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP); //当我们不加这个方法时，查出来的list是一个没有跟字段对应，即["a","b","c"]，如果加上setResultTransformer这个方法，list里面的元素就会成为一个跟数据库字段
		page.setContent(query.list());
		page.setTotal(total);

		return page;
	}

	public Page<T> findPageBySql(Page page, String selectConditions, String sql, Map<String, Object> params) throws DBException {
		long total = this.countSql(sql, params);

		if (page == null) {
			page = new Page();
		}

		Query query = this.getCurrentSession().createSQLQuery(selectConditions + sql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}

		query.setFirstResult((page.getPage() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());
		page.setContent(query.list());
		page.setTotal(total);

		return page;
	}

	public long update(String hql, String... values) throws DBException {
		Query query = this.getCurrentSession().createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.executeUpdate();
	}
	
	public long update(String hql, Session session, String... values) throws DBException {
		if (null == session) {
			session = this.getCurrentSession();
		}
		Query query = session.createQuery(hql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.executeUpdate();
	}

	public long updateBySql(String sql, String... values) throws DBException {
		Query query = this.getCurrentSession().createSQLQuery(sql);
		for (int i = 0; i < values.length; i++) {
			query.setParameter(i, values[i]);
		}
		return query.executeUpdate();
	}

	public long update(String hql, Map<String, Object> params) throws DBException {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.executeUpdate();
	}

	public long update(String hql, Map<String, Object> params, Session session) throws DBException {
		if (null == session) {
			session = this.getCurrentSession();
		}

		Query query = session.createQuery(hql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}

		return query.executeUpdate();
	}

	public long updateBySql(String sql, Map<String, Object> params) throws DBException {
		Query query = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return query.executeUpdate();
	}

	public long updateBySql(String sql, Map<String, Object> params, Session session) throws DBException {
		if (null == session) {
			session = this.getCurrentSession();
		}

		Query query = session.createSQLQuery(sql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}

		return query.executeUpdate();
	}

	public Page<T> findPage(Page page) throws DBException {

		return this.findPage(page, null, null);
	}

	/**
	 * 根据条件组装hql
	 * 
	 * @param condition
	 * @return
	 * @throws DBException
	 */
	private String handleHql(String condition, String tables) throws DBException {
		StringBuffer hql = new StringBuffer();
		hql.append(" from ");
		if (StringUtils.isEmpty(tables)) {
			hql.append(entityClass.getName());
			// System.out.println(entityClass +"  "+tables);
			// hql.append(" entity ");
		} else {
			hql.append(tables);
		}

		hql.append(condition);

		return hql.toString();
	}

	public Page<T> findPage(Page page, String selectConditions, String tables) throws DBException {
		//    System.out.println("selectConditions="+selectConditions+"     tables="+tables);
		
		return this.findPage(page, selectConditions, tables, "");

	}

	public Page<T> findPage(Page page, String selectConditions, String tables, String specialCondetions) throws DBException {
//		System.out.println(tables);
		if (page == null) {
			page = new Page();
		}

		QueryHelper queryHelper = new QueryHelper();
		//根据帅选条件来拼接不完整的hql语句
		String hql = queryHelper.buildQuery(page.getFilters());  
		
		//		System.out.println(hql);
		
		//拼接不完整hql中储存的帅选参数
		Map<String, Object> params = queryHelper.getParams();
		
		//打印出params中的键值对
		Set<Entry<String, Object>> entrySet = params.entrySet();
		for (Entry<String, Object> entry : entrySet) {
//			System.out.println(entry.getKey()+ "   "+entry.getValue());
		}

		if (specialCondetions != null && !"".equals(specialCondetions)) {
			hql += specialCondetions;
		}
		
		//根据实体来拼接完整的hql语句
		hql = this.handleHql(hql, tables);
		
		//		System.out.println(hql);
		
		//查询条数
		long total = this.count(hql, params);
		
		//		System.out.println(total);
		
		for(int i=0;i<page.getOrders().size();i++){
//			System.out.print(page.getOrders().get(i).toString()+"****");
		}
		
		String alias = page.getAlias();
//		System.out.println(alias);
		
		//再根据排序参数进行帅选数据
		hql += queryHelper.buildOrder(page.getOrders(), page.getAlias());

		if (StringUtils.isNotEmpty(selectConditions)) {
			hql = selectConditions + hql;
		}
		
//		System.out.println(hql);
		
		/*Query query = this.getCurrentSession().createQuery(SysConstant.ROW_COUNT_PRE + hql);
		System.out.println(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				System.out.println("开始拼接占位符参数...key="+key+"   params.get(key)="+params.get(key));
				query.setParameter(key, params.get(key));
				System.out.println(hql);
			}
			System.out.println(hql);
		}

		//所有占位符拼接完成后进行数据库查询条数
		Object count = query.uniqueResult();*/
		
		
		

		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
//				System.out.println(key);
				// query.setParameter(key, params.get(key));
				Object obj = params.get(key);
//				System.err.println(obj.toString());
				// 根据参数类型进行设置，用于支持in查询
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(key, (Object[]) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}

//		System.out.println(hql);		
		
		query.setFirstResult((page.getPage() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());
		
//		System.out.println(query.list().toString());

		page.setContent(query.list());
		page.setTotal(total);

		return page;
	}

	public T load(Serializable id) throws DBException {
		Assert.notNull(id, "id is required");
		return (T) this.getCurrentSession().load(entityClass, id);
	}

	public boolean propertyExists(String property, Object value) throws DBException {
		Long count = (Long) this.getCurrentSession().createCriteria(entityClass).add(Restrictions.eq(property, value)).setProjection(Projections.rowCount())
				.uniqueResult();
		if (count > 0)
			return true;
		return false;
	}

	public void delBatch(Serializable[] ids) throws DBException {
		Assert.notEmpty(ids, "ids must not be empty");
		for (Serializable id : ids) {
			T entity = this.load(id);
			this.delete(entity);
		}

	}

	public T findUniqueByFilter(List<Filter> filters) throws DBException {
		QueryHelper queryHelper = new QueryHelper();
		String hql = queryHelper.buildQuery(filters);
		hql = this.handleHql(hql, null);

		Map<String, Object> params = queryHelper.getParams();
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		return (T) query.uniqueResult();
	}

	public List<T> findList(Integer first, Integer count, List<Filter> filters, List<Order> orders) throws DBException {
		QueryHelper queryHelper = new QueryHelper();
		String hql = queryHelper.buildQuery(filters);
		Map<String, Object> params = queryHelper.getParams();
		hql = this.handleHql(hql, null);

		hql += queryHelper.buildOrder(orders, null);
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}

		if (first != null) {
			query.setFirstResult(first);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.list();
	}

	public List<T> findListByIn(String hql, Map<String, Object> params) throws DBException {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);

				// 这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(key, (Object[]) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
		return query.list();
	}
	
	public long updateByIn(String hql, Map<String, Object> params) throws DBException {
		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);

				// 这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(key, (Object[]) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
		return query.executeUpdate();
	}

	public long updateByIn(String hql, Map<String, Object> params, Session session) throws DBException {
		if (null == session) {
			session = this.getCurrentSession();
		}

		Query query = session.createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);

				// 这里考虑传入的参数是什么类型，不同类型使用的方法不同
				if (obj instanceof Collection<?>) {
					query.setParameterList(key, (Collection<?>) obj);
				} else if (obj instanceof Object[]) {
					query.setParameterList(key, (Object[]) obj);
				} else {
					query.setParameter(key, obj);
				}
			}
		}
		return query.executeUpdate();
	}

	public List<Object[]> findObjectListBySql(String sql, Map<String, Object> params) throws DBException {
		Query query = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		List<Object[]> list = query.list();
		return list;
	}

	public Object[] findObjectBySql(String sql, Map<String, Object> params) throws DBException {
		Query query = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		Object[] objects = (Object[]) query.uniqueResult();
		return objects;
	}

	public Map<String, Object> findObjPageBySql(String sql, String selectConditions, String orderString, Map<String, Object> params, Integer page,
			Integer pageSize) throws DBException {
		Map<String, Object> retMap = new HashMap<String, Object>();
		long total = this.countSql(sql, params);

		if (null == orderString) {
			orderString = "";
		}
		sql = selectConditions + sql + orderString;
		Query query = this.getCurrentSession().createSQLQuery(sql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		if (null == page || page == 0) {
			page = 1;
		}
		if (null == pageSize || pageSize == 0) {
			pageSize = 10;
		}
		query.setFirstResult((page - 1) * pageSize);
		query.setMaxResults(pageSize);
		List<Object[]> list = query.list();

		retMap.put("total", total);
		retMap.put("content", list);
		retMap.put("pageSize", pageSize);
		retMap.put("page", page);
		return retMap;
	}

	public void clear() {
		this.getCurrentSession().clear();

	}

	public void flush() {
		this.getCurrentSession().flush();

	}

	public void refresh(T entity) {
		this.getCurrentSession().refresh(entity);
	}

	public void evict(T entity) {
		this.getCurrentSession().evict(entity);
	}

	/**
	 * 转换时间类型
	 */
	public Page<T> findPageByDateType(Page page, String selectConditions, String tables, String specialCondetions, String dateType) throws DBException {
		if (page == null) {
			page = new Page();
		}

		// 进行时间类型转换
		if (null == dateType || "".equals(dateType)) {
			dateType = "yyyyMMddHHmmss";
		}

		SimpleDateFormat sdf = null;

		try {
			sdf = new SimpleDateFormat(dateType);
		} catch (Exception e) {
			sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		}

		QueryHelper queryHelper = new QueryHelper();
		String hql = queryHelper.buildQuery(page.getFilters());
		Map<String, Object> params = queryHelper.getParams();

		if (specialCondetions != null && !"".equals(specialCondetions)) {
			hql += specialCondetions;
		}

		hql = this.handleHql(hql, tables);
		long total = this.countByDateType(hql, params, sdf);
		hql += queryHelper.buildOrder(page.getOrders(), page.getAlias());

		if (StringUtils.isNotEmpty(selectConditions)) {
			hql = selectConditions + hql;
		}

		Query query = this.getCurrentSession().createQuery(hql);
		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);

				if (obj instanceof Date) {
					query.setParameter(key, sdf.format(obj));
				} else {
					query.setParameter(key, obj);
				}
			}
		}

		query.setFirstResult((page.getPage() - 1) * page.getPageSize());
		query.setMaxResults(page.getPageSize());

		page.setContent(query.list());
		page.setTotal(total);

		return page;
	}

	public long countByDateType(String hql, Map<String, Object> params, SimpleDateFormat sdf) throws DBException {
		if (hql.contains("fetch")) {
			hql = hql.replaceAll("fetch", "");
		}

		Query query = this.getCurrentSession().createQuery(SysConstant.ROW_COUNT_PRE + hql);

		if (params != null && !params.isEmpty()) {
			for (String key : params.keySet()) {
				Object obj = params.get(key);

				if (obj instanceof Date) {
					query.setParameter(key, sdf.format(obj));
				} else {
					query.setParameter(key, obj);
				}
			}
		}

		Object count = query.uniqueResult();

		if (null == count)
			return 0;

		return (Long) count;
	}
	

	public List<T> findList(Integer first, Integer count,List<Filter> filters, List<Order> orders,String specialCondetions) throws DBException {
		QueryHelper queryHelper = new QueryHelper();
		String hql = queryHelper.buildQuery(filters);
		
		if(specialCondetions != null && !"".equals(specialCondetions)){
			hql += specialCondetions;
		}
		
		hql = this.handleHql(hql,null);
		
		hql += queryHelper.buildOrder(orders,null);
		
		Map<String, Object> params = queryHelper.getParams();
		Query query = this.getCurrentSession().createQuery(hql);
		if(params != null && !params.isEmpty()){
			for(String key : params.keySet()){
				query.setParameter(key, params.get(key));
			}
		}
		
		if (first != null) {
			query.setFirstResult(first);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.list();
	}
	
	 //获取两个日期之间的天数
	public  int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
	//获取两个日期之间的月数
	public static int getMonthDiff(Date d1, Date d2) { 
		Calendar c1 = Calendar.getInstance(); 
		Calendar c2 = Calendar.getInstance();  
		c1.setTime(d1);     
		c2.setTime(d2);  
		int year1 = c1.get(Calendar.YEAR); 
		int year2 = c2.get(Calendar.YEAR); 
		int month1 = c1.get(Calendar.MONTH); 
		int month2 = c2.get(Calendar.MONTH); 
		int day1 = c1.get(Calendar.DAY_OF_MONTH);
		int day2 = c2.get(Calendar.DAY_OF_MONTH); 
		// 获取年的差值        
		int yearInterval = year1 - year2;  
		// 如果 d1的 月-日 小于 d2的 月-日 那么 yearInterval-- 这样就得到了相差的年数        
		if (month1 < month2 || month1 == month2 && day1 < day2) 
			yearInterval--;     
		// 获取月数差值        
		int monthInterval = (month1 + 12) - month2; 
		if (day1 < day2)   
			monthInterval--;        
		monthInterval %= 12;
		int monthsDiff = Math.abs(yearInterval * 12 + monthInterval);       
		return monthsDiff;   
	}
}
