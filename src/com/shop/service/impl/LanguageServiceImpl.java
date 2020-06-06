package com.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shop.dao.LanguageDao;
import com.shop.entity.Language;
import com.shop.service.LanguageService;

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
public class LanguageServiceImpl extends BaseServiceImpl<Language> implements LanguageService{
	
	@Resource
	LanguageDao languageDao;

	@Resource
	public void setBaseDao(LanguageDao languageDao) {
		super.setBaseDao(languageDao);
	}
}


