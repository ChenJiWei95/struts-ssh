package com.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.annotation.RCacheEvict;
import com.shop.annotation.RCacheable;
import com.shop.annotation.RParamer;
import com.shop.dao.UserDao;
import com.shop.entity.User;
import com.shop.exception.DBException;
import com.shop.service.UserService;

/** 
 * <b>一句话描述该类</b>
 * <p>
 * 描述:<br>
 * 
 * @author 威 
 * <br>2019年10月26日 下午6:04:52 
 * @see
 * @since 1.0
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	@Resource
	UserDao userDao;

	@Resource
	public void setBaseDao(UserDao userDao) {
		super.setBaseDao(userDao);
	}
	
	// 缓存 key指定为id
	/*@Transactional(readOnly = true)
	@RCacheable(key="#id")
	public User get(@RParamer("id")String id) throws DBException {
		return getBaseDao().get(id);
	}*/
	
	// 缓存 key未指定 默认参数拼接作为key
	/*@Transactional(readOnly = true)
	@RCacheable
	public User find(String hql, String... values) throws DBException {
		return getBaseDao().find(hql, values);
	}*/
	
	// 清除
	/*@Transactional
	@RCacheEvict(key="#user.id")
	public void update(@RParamer("user")User entity) throws DBException {
		getBaseDao().update(entity);
	}*/
}
