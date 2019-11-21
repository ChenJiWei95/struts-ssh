package com.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.AddressDao;
import com.shop.entity.Address;
import com.shop.service.AddressService;

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
public class AddressServiceImpl extends BaseServiceImpl<Address> implements AddressService{
	
	@Resource
	AddressDao addressDao;

	@Resource
	public void setBaseDao(AddressDao addressDao) {
		super.setBaseDao(addressDao);
	}
}


