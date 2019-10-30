package com.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.GoodsDao;
import com.shop.entity.Goods;
import com.shop.service.GoodsService;

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
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService{
	
	@Resource
	GoodsDao goodsDao;

	@Resource
	public void setBaseDao(GoodsDao goodsDao) {
		super.setBaseDao(goodsDao);
	}
}
