package com.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.UserInforDao;
import com.shop.entity.UserInfor;
import com.shop.service.UserInforService;

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
public class UserInforServiceImpl extends BaseServiceImpl<UserInfor> implements UserInforService{
	
	@Resource
	UserInforDao userInforDao;

	@Resource
	public void setBaseDao(UserInforDao userInforDao) {
		super.setBaseDao(userInforDao);
	}
}
