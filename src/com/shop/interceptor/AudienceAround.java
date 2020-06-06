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
	/*@Pointcut("execution(* com.shop.service.impl.*.*(..))")*/
  	public void performance(){
		log.info("performance....");
	}
  	/*@Around("performance()")*/
  	public Object watchPerformance(ProceedingJoinPoint joinPoint) throws Throwable{
  		try {
	  		/*log.info("获取RedisServer不为空则正常：redisService="+redisService);
	  		log.info("前置通知");*/
	  		
	  		// 获取该方法的注解  RCacheable  RCacheEvict
	  		// 执行前对key进行获取并判断是否有缓存，有 则在缓存中读； 无 则在数据库读，然后在载入缓存 
	  		// 数据保存24小时
	    	Object result = joinPoint.proceed();
	    	// 缓存中无数据  则在数据库读，然后在载入缓存 
	    	log.info("后置通知"); 
	    	Class<?>[] clazz = new Class<?>[joinPoint.getArgs().length];
	    	for(int i = 0; i < joinPoint.getArgs().length; i++)// 没有传key 那么就用参数拼接 中间无任何分隔符
	    	{
	//    		log.info("第"+(i+1)+"个参数："+joinPoint.getArgs()[i].getClass().toString());
	//    		if("class [Ljava.lang.String;".equals(joinPoint.getArgs()[i].getClass().toString())) log.info("是一个数组");
	    		clazz[i] = joinPoint.getArgs()[i].getClass(); // 拼接所注解方法的传入参数类型
	    	}
	    		
	    	
	    	log.info("代理对象："+joinPoint.getTarget().getClass().getName());
	    	
//	    	此处如果当前类没有该方法时会报错、即使父类有，也会报错
//	    	java.lang.NoSuchMethodException
	    	Method method = joinPoint.getTarget().getClass().getMethod(joinPoint.getSignature().getName(), 
	    			clazz);
	    	
	    	log.info("返回值："+method.getReturnType().getSimpleName()); // 结果：Object 
	    	
	    	List<String> l = new ArrayList<>(1);
	    	log.info("返回值：getClass.getSimpleName"+l.getClass().getSimpleName()); // 结果：ArrayList 
	    	// 根据返回类型判断执行的缓存方式
	    	
	    	Annotation[] annotations = method.getAnnotations();
	    	for (Annotation annotation : annotations) {
	    		log.info("存在注解"+annotation.toString());
				if (annotation instanceof RCacheable) {
					log.info("方法包含缓存注解：RCacheable");
					// 标注RCacheable注解 缓存+读取 
					RCacheable rCacheable = (RCacheable) annotation;
					// 判断RCacheable注解 是否包含key
					String key = "";
					if("".equals(rCacheable.key())) // RCacheable未标明key   就用所有参数的拼接作为key
						for(int i = 0; i < joinPoint.getArgs().length; i++) {
							if("class [Ljava.lang.String;".equals(joinPoint.getArgs()[i].getClass().toString()))// 判断数组格式的对象分开处理
								for(String arg : (String[]) joinPoint.getArgs()[i]) key += arg;
							else 
								key += joinPoint.getArgs()[i].toString();}
					else { // RCacheable存在key
						// 查找参数中的注解 返回ctx
						key = (String) key_(rCacheable.key(), createOgnl(joinPoint, method));}
					// 确定key之后 -- 进行缓存读取或者写入
					// 执行前对key进行获取并判断是否有缓存，有 则在缓存中读； 无 则在数据库读，然后在载入缓存 
					if(redisService.hasKey(key)) {
						// 根据不同的返回类型进行读取
						log.info("从缓存中读取。。。，key："+key);
						return redisService.get(key);}
					else { 
						// 根据不同的类返回型进行写入
						log.info("载入缓存，key："+key);
						redisService.set(key, result);
					}
				} else if (annotation instanceof RCacheEvict) {
					log.info("方法包含缓存注解：RCacheEvict");
					// 删除缓存
					RCacheEvict rCacheEvict = (RCacheEvict) annotation;
					// 判断注解 是否包含key
					String key = "";
					if("".equals(rCacheEvict.key())) // RCacheable未标明key就用所有参数的拼接作为key
						for(int i = 0; i < joinPoint.getArgs().length; i++)
							key += joinPoint.getArgs()[i];
					else // 注解存在
						key = (String) key_(rCacheEvict.key(), createOgnl(joinPoint, method));
					if(redisService.hasKey(key)) redisService.del(key);
				} else if (annotation instanceof RCachePut) {
					log.info("方法包含缓存注解：RCachePut");
					// 载入缓存
					RCachePut rCachePut = (RCachePut) annotation;
					// 判断注解 是否包含key
					String key = "";
					if("".equals(rCachePut.key())) // RCacheable未标明key就用所有参数的拼接作为key
						for(int i = 0; i < joinPoint.getArgs().length; i++)
							key += joinPoint.getArgs()[i];
					else // 注解存在
						key = (String) key_(rCachePut.key(), createOgnl(joinPoint, method));
					redisService.set(key, result);
				} 
			} 
	    	return result;
  		}catch (NoSuchMethodException e) {
  			e.printStackTrace();
    	}
    	return null;
  	}
  	/**
  	 * 通过包含RParamer注解的参数获取key 当前返回OgnlContext对象
  	 * @param joinPoint
  	 * @param method
  	 * @return
  	 */
	protected OgnlContext createOgnl(ProceedingJoinPoint joinPoint, Method method) {
		OgnlContext ctx = null;
    	// 获取参数注解--> 根据注解获取对应值 --》获取缓存key
    	for(int i = 0; i < method.getParameters().length; i++){
    		log.info("第"+(i+1)+"个参数："+method.getParameters()[i].toString());
    		for(Annotation annotation : method.getParameters()[i].getAnnotations()) {
    			if(annotation instanceof RParamer) {
    				RParamer rParamer = (RParamer) annotation;
//    				log.info("参数有注解，"+rParamer.value()+"，"+joinPoint.getArgs()[i]);
    				ctx = ctx == null ? new OgnlContext () : ctx;
    				ctx.put(rParamer.value(), joinPoint.getArgs()[i]);
    			}
    		}
    	}
		return ctx;
	}
  	/**
  	 * 处理#id 通过ognl取值
  	 * #user.id
  	 * @return
  	 */
  	private Object key_(String key, OgnlContext ctx) {
  		log.info(key.indexOf("#"));
  		if(key.indexOf("#") == 0) {
  			try {
  				log.info("Ognl.getValue "+key+"："+Ognl.getValue(key, ctx, ctx.getRoot()));
				return Ognl.getValue(key, ctx, ctx.getRoot());
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