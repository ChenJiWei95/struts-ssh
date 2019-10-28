package com.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.UserTestDao;
import com.shop.entity.UserTest;
import com.shop.service.UserTestService;

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
@Service("userServiceImpl")
public class UserTestServiceImpl extends BaseServiceImpl<UserTest> implements UserTestService{
	
	@Resource
	UserTestDao userTestDao;

	@Resource
	public void setBaseDao(UserTestDao userDao) {
		super.setBaseDao(userDao);
	}
}
