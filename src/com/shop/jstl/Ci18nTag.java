package com.shop.jstl;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueResultException;

import com.shop.Constants;
import com.shop.entity.Language;
import com.shop.service.LanguageService;
import com.shop.util.RedisService;
import com.shop.util.SpringUtils;

public class Ci18nTag extends SimpleTagSupport {
	
	private Logger log = Logger.getLogger(Ci18nTag.class);
	
	// 标签属性text
	private String name;
	
	public void setName(String text) {
		this.name = text;
	}

	// 内容体
	StringWriter sw = new StringWriter();
	
	public void doTag() throws JspException, IOException {
		log.info("正在使用Ci18nTag");
		JspWriter out = getJspContext().getOut();
		String currentCode = "";
		if(name != null){
			currentCode = name;
		} else {
			currentCode = sw.toString();
		}
		if(name == null || "".equals(name)){
			out.println("");
			return;
		}
		
		String value = execute(currentCode);
		log.info("输出："+value);
		
	    out.println(value);
	}

	protected String execute(String currentCode) {
		RedisService redis = (RedisService) SpringUtils.getBean("redisService");
		LanguageService langService = (LanguageService) SpringUtils.getBean("languageServiceImpl");
		// redis的key：语言前缀+text，这样区分语言
		String prefix = (String) redis.get(com.shop.Constants.LANGUAGE_SIGN)
				, key = prefix + currentCode
				, value = currentCode;
		if((value = (String) redis.get(key)) == null){
			value = currentCode;
			Language lang = null;
			try{
				log.info("查询数据库");
				lang = langService.find("from Language where code=?", currentCode);
			}catch(NonUniqueResultException e){
				log.info("查询数据库 有多个数据");
				List<Language> langs = langService.findList("from Language where code=?", currentCode);
				for(Language l : langs){
					
					if(l.getCode().equals(currentCode)){
						lang = l;
						
					}
				}
			}
			if(lang != null){
				value = getLang(prefix, lang);
				log.info("进行缓存，"+key+"="+value);
				redis.set(key, value);
			}
		}
		return value;
	}

	protected String getLang(String prefix, Language lang) {
		switch(prefix){
			case Constants.LANGUAGE_ZH_CN:
				return lang.getCnZh();
			case Constants.LANGUAGE_EN_US:
				return lang.getEnUs();
			default: 
				log.error("找不到对应的语言");
				return "";
		}
	}
	
}
