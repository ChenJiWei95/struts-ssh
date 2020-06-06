package com.shop.control;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.Constants;
import com.shop.util.RedisService;
/**
 * <b>初始化语种在redis的缓存 默认中文</b>
 * <p>
 * 描述:<br>
 * 
 * @author 威 
 * <br>2020年6月6日 下午2:14:07 
 * @see
 * @since 1.0
 */
@Component
public class InitStander implements InitializingBean{
	private static Logger log = Logger.getLogger(InitStander.class);
	
	@Autowired
	private RedisService redisService;
	 
	@Override
	public void afterPropertiesSet() throws Exception {
		redisService.set(Constants.LANGUAGE_SIGN, Constants.LANGUAGE_ZH_CN);
		log.info(redisService.get(Constants.LANGUAGE_SIGN));
	}

}
 

  