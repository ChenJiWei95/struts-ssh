package com.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.GoodsStytleDao;
import com.shop.entity.GoodsStytle;
import com.shop.service.GoodsStytleService;

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
public class GoodsStytleServiceImpl extends BaseServiceImpl<GoodsStytle> implements GoodsStytleService{
	
	@Resource
	GoodsStytleDao goodsStytleDao;

	@Resource
	public void setBaseDao(GoodsStytleDao goodsStytleDao) {
		super.setBaseDao(goodsStytleDao);
	}
}


