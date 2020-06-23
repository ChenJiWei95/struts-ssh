package com.shop.util.i18n;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.shop.Constants;
import com.shop.entity.Language;
import com.shop.util.CommonUtil;
import com.shop.util.RedisService;
import com.shop.util.SpringUtils;

/**
 * <b>i18n基础支持类</b>
 * <p>
 * 描述:<br>
 * 
 * @author 威 
 * <br>2020年6月7日 下午4:54:32 
 * @see
 * @since 1.0
 */
public abstract class AbstractCi18nCore {
	
	private static Logger log = Logger.getLogger(AbstractCi18nCore.class); // 日志对象
	
	public String execute(String currentCode) {
		RedisService redis = (RedisService) SpringUtils.getBean("redisService");
		
		// redis的key：语言前缀+text，这样区分语言
		String prefix = (String) redis.get(Constants.LANGUAGE_CURRENT)
				, key = prefix + currentCode
				, value = currentCode;
		if((value = (String) redis.get(key)) == null){
			value = currentCode;
			Language lang = getLanguage(currentCode);
			if(lang != null){
				try {
					value = coverLang(prefix, lang);
					log.info("进行缓存，"+key+"="+value);
					redis.set(key, value);
				} catch (Exception e) {
					log.info("i18n 异常："+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	protected abstract Language getLanguage(String currentCode);
	
	protected String coverLang(String prefix, Language lang) throws Exception {
		Class<?> clazz = lang.getClass();
		Method method = clazz.getMethod("get"+CommonUtil.upFirst(prefix.replace("_", "").toLowerCase()));
		return (String) method.invoke(lang); 
	}
}
