package com.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.UserAddressDao;
import com.shop.entity.UserAddress;
import com.shop.service.UserAddressService;

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
public class UserAddressServiceImpl extends BaseServiceImpl<UserAddress> implements UserAddressService{
	
	@Resource
	UserAddressDao userAddressDao;

	@Resource
	public void setBaseDao(UserAddressDao userAddressDao) {
		super.setBaseDao(userAddressDao);
	}
}


