package com.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.CollectionDao;
import com.shop.entity.Collection;
import com.shop.service.CollectionService;

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
public class CollectionServiceImpl extends BaseServiceImpl<Collection> implements CollectionService{
	
	@Resource
	CollectionDao collectionDao;
	
	@Resource
	public void setBaseDao(CollectionDao collectionDao) {
		super.setBaseDao(collectionDao);
	}
}


