package com.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.UserDao;
import com.shop.entity.User;
import com.shop.service.UserService;

/** 
 * <b>一句话描述该类<b>
 * @author 威 
 * <br>2018年3月15日 下午3:50:08 
 *  
 */
@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	
	@Resource
	UserDao userDao;

	@Resource
	public void setBaseDao(UserDao userDao) {
		super.setBaseDao(userDao);
	}
}
