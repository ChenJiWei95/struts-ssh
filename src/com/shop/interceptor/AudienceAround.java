package com.shop.interceptor;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.annotation.RCacheEvict;
import com.shop.annotation.RCachePut;
import com.shop.annotation.RCacheable;
import com.shop.annotation.RParamer;
import com.shop.util.RedisService;

import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

/**
 * @version: V 1.0 
 * @Description: 
 * redis缓存
 * @author: cjw 
 * @date: 2019年11月4日 下午5:23:16
 */
@Aspect
@Component
public class AudienceAround {
	
	private static Logger log = Logger.getLogger(AudienceAround.class);
	
	@Autowired
	private RedisService redisService;
	
	//使用@Pointcut注解声明频繁使用的切入点表达式
	@Pointcut("execution(* com.shop.service.impl.*.*(..))")
  	public void performance(){
		log.info("performance....");
	}
  	@Around("performance()")
  	public Object watchPerformance(ProceedingJoinPoint joinPoint) throws Throwable{
  		log.info("获取RedisServer不为空则正常：redisService="+redisService);
  		log.info("前置通知");
  		
  		OgnlContext ctx = new OgnlContext ();
  		Object root = ctx.getRoot();
  		
  		// 获取该方法的注解  RCacheable  RCacheEvict
  		// 执行前对key进行获取并判断是否有缓存，有 则在缓存中读； 无 则在数据库读，然后在载入缓存 
  		// 数据保存24小时
    	Object result = joinPoint.proceed();
    	// 缓存中无数据  则在数据库读，然后在载入缓存 
    	log.info("后置通知");
    	Class<?>[] clazz = new Class<?>[joinPoint.getArgs().length];
    	for(int i = 0; i < joinPoint.getArgs().length; i++)// 没有传key 那么就用参数拼接 中间无任何分隔符
    	{
    		log.info("第"+(i+1)+"个参数："+joinPoint.getArgs()[i]);
    		clazz[i] = joinPoint.getArgs()[i].getClass(); // 拼接所注解方法的传入参数类型
    	}
    		
    	
    	log.info("代理对象："+joinPoint.getTarget().getClass().getName());
    	Method method = joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName(), 
    			clazz);
    	// 获取参数注解--> 根据注解获取对应值 --》获取缓存key
    	for(int i = 0; i < method.getParameters().length; i++){
    		for(Annotation annotation : method.getParameters()[i].getAnnotations()) {
    			if(annotation instanceof RParamer) {
    				RParamer rParamer = (RParamer) annotation;
    				ctx.put(rParamer.value(), joinPoint.getArgs()[i]);
    			}
    		}
    	}
    	
    	Annotation[] annotations = method.getAnnotations();
    	for (Annotation annotation : annotations) {
    		log.info("存在注解"+annotation.toString());
			if (annotation instanceof RCacheable) {
				// 标注RCacheable注解 缓存+读取 
				RCacheable rCacheable = (RCacheable) annotation;
				// 判断RCacheable注解 是否包含key
				String key = "";
				if("".equals(rCacheable.key())) // RCacheable未标明key就用所有参数的拼接作为key
					for(int i = 0; i < joinPoint.getArgs().length; i++)
						key += joinPoint.getArgs()[i];
				else // RCacheable存在
					key = (String) key_(rCacheable.key(), ctx, root);
				
				// 执行前对key进行获取并判断是否有缓存，有 则在缓存中读； 无 则在数据库读，然后在载入缓存 
				if(redisService.hasKey(key)) {log.info("从缓存中读取。。。");
					return redisService.get(key);}
				else { log.info("载入缓存");
					redisService.set(key, result);
				}
			} else if (annotation instanceof RCacheEvict) {
				// 删除缓存
				RCacheEvict rCacheEvict = (RCacheEvict) annotation;
				// 判断注解 是否包含key
				String key = "";
				if("".equals(rCacheEvict.key())) // RCacheable未标明key就用所有参数的拼接作为key
					for(int i = 0; i < joinPoint.getArgs().length; i++)
						key += joinPoint.getArgs()[i];
				else // 注解存在
					key = (String) key_(rCacheEvict.key(), ctx, root);
				if(redisService.hasKey(key)) redisService.del(key);
			} else if (annotation instanceof RCachePut) {
				// 载入缓存
				RCachePut rCachePut = (RCachePut) annotation;
				// 判断注解 是否包含key
				String key = "";
				if("".equals(rCachePut.key())) // RCacheable未标明key就用所有参数的拼接作为key
					for(int i = 0; i < joinPoint.getArgs().length; i++)
						key += joinPoint.getArgs()[i];
				else // 注解存在
					key = (String) key_(rCachePut.key(), ctx, root);
				redisService.set(key, result);
			} 
		}
    	
    	log.info("反射获取方法："+method);
    	log.info("方法："+joinPoint.getSignature());
    	log.info("方法名："+joinPoint.getSignature().getName()); 
    	log.info("返回值："+result);
    	
    	return result;
  	}
  	/**
  	 * 处理#id 通过ognl取值
  	 * #user.id
  	 * @return
  	 */
  	private Object key_(String key, OgnlContext ctx, Object root) {
  		log.info(key.indexOf("#"));
  		if(key.indexOf("#") == 0) {
  			try {
  				log.info("Ognl.getValue"+key+"："+Ognl.getValue(key, ctx, root));
				return Ognl.getValue(key, ctx, root);
			} catch (OgnlException e) {
				e.printStackTrace();
			}
  		}
  		return key;
  	}
  	
  	public static void main(String[] args) {
  		// 测试Ognl
  		OgnlContext ctx = new OgnlContext ();
  		Object root = ctx.getRoot();
  		ctx.put("name", "123456");
  		Shop shop = new Shop();
  		shop.setName("尚品家私");
  		shop.setShell("开业中");
  		shop.setFrom("华丽路");
  		People people = new People();
  		people.setName("John");
  		people.setDoing("shoping");
  		shop.setPeople(people);
  		ctx.put("shop", shop);
  		try {
			log.info(Ognl.getValue("#name", ctx, root));
			log.info(Ognl.getValue("#shop.name", ctx, root));
			log.info(Ognl.getValue("#shop.people.name", ctx, root));
			/*[INFO ] 11:05:23:673 [AudienceAround.main:167] 123456 
			[INFO ] 11:05:23:823 [AudienceAround.main:168] 尚品家私 
			[INFO ] 11:05:23:839 [AudienceAround.main:169] John */
		} catch (OgnlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
  	}
  	// 测试redisService
  	public void test() {
  		// 测试存 是否有key 取
  		redisService.set("name", "cjw");
  		log.info(redisService.hasKey("name"));
  		log.info(redisService.get("name"));
  		redisService.del("name");
  		log.info(redisService.hasKey("name"));
  		
  		People people = new People();
  		people.setName("John");
  		people.setDoing("shoping");
  		// 测试存 不同类型的数据 是否有key  自定义对象 
  		redisService.set("people", people);
  		log.info(redisService.hasKey("people"));
  		log.info(redisService.get("people"));
  		redisService.del("people");
  		log.info(redisService.hasKey("people"));
  		
  		// 测试存 不同类型的数据 是否有key  Map
  		Map<String, Object> map = new HashMap<String, Object>();
  		map.put("name", "cjwmap");
  		redisService.hmset("map", map);
  		log.info(redisService.hasKey("map"));
  		log.info(redisService.hmget("map"));
  		redisService.del("map");
  		log.info(redisService.hasKey("map"));
  		
  		// 测试存 不同类型的数据 是否有key  List
  		List<String> list = new ArrayList<String>();
  		list.add("name");
  		redisService.lSet("list", list);
  		log.info(redisService.hasKey("list"));
  		log.info(redisService.lGet("list", 0, -1));
  		redisService.del("list");
  		log.info(redisService.hasKey("list"));
  	}
}
class Shop{
	String name;
	String shell;
	String from;
	People people;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShell() {
		return shell;
	}
	public void setShell(String shell) {
		this.shell = shell;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public People getPeople() {
		return people;
	}
	public void setPeople(People people) {
		this.people = people;
	}
	
}
class People implements Serializable {
	String name;
	String doing;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDoing() {
		return doing;
	}
	public void setDoing(String doing) {
		this.doing = doing;
	}
	public String toString() {
		return "["+
				 "name="+name+
				 ",doing="+doing+
				"]";
	}
}