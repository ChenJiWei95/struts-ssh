package com.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.LogisticsDao;
import com.shop.entity.Logistics;
import com.shop.service.LogisticsService;

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
public class LogisticsServiceImpl extends BaseServiceImpl<Logistics> implements LogisticsService{
	
	@Resource
	LogisticsDao logisticsDao;

	@Resource
	public void setBaseDao(LogisticsDao logisticsDao) {
		super.setBaseDao(logisticsDao);
	}
}


