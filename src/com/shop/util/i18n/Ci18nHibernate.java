package com.shop.util.i18n;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.NonUniqueResultException;
import org.springframework.stereotype.Component;

import com.shop.entity.Language;
import com.shop.service.LanguageService;
import com.shop.util.SpringUtils;

/**
 * <b>i18n 整合mybatis</b>
 * <p>
 * 描述:<br>
 * 
 * @author 威 
 * <br>2020年6月7日 下午4:54:58 
 * @see
 * @since 1.0
 */
@Component
public class Ci18nHibernate extends AbstractCi18nCore {
	
	private static Logger log = Logger.getLogger(Ci18nHibernate.class); // 日志对象
	 
	protected Language getLanguage(String currentCode){
		LanguageService langService = (LanguageService) SpringUtils.getBean("languageServiceImpl");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lang;
	}
	
}
