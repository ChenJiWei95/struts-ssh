package com.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.CartListDao;
import com.shop.entity.CartList;
import com.shop.service.CartListService;

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
public class CartListServiceImpl extends BaseServiceImpl<CartList> implements CartListService{
	
	@Resource
	CartListDao cartListDao;

	@Resource
	public void setBaseDao(CartListDao cartListDao) {
		super.setBaseDao(cartListDao);
	}
}


