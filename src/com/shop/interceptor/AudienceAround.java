package com.shop.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.util.RedisService;

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
	
	@Autowired
	private RedisService redisService;
	
	//使用@Pointcut注解声明频繁使用的切入点表达式
	@Pointcut("execution(* com.shop.service.impl.*.*(..))")
  	public void performance(){}
  	@Around("performance()")
  	public Object watchPerformance(ProceedingJoinPoint joinPoint) throws Throwable{
  		System.out.println("获取RedisServer不为空则正常：redisService="+redisService);
  		System.out.println("前置通知");
  		// 获取该方法的注解  RCacheable  RCacheEvict
  		// 执行前对key进行获取并判断是否有缓存，有 则在缓存中读； 无 则在数据库读，然后在载入缓存 
  		// 数据保存24小时
    	Object result = joinPoint.proceed();
    	// 缓存中无数据  则在数据库读，然后在载入缓存 
    	System.out.println("后置通知");
    	for(int i = 0; i < joinPoint.getArgs().length; i++)// 没有传key 那么就用参数拼接 中间无任何分隔符
    		System.out.println("第"+(i+1)+"个参数："+joinPoint.getArgs()[i]);
    	
    	System.out.println("代理对象："+joinPoint.getTarget().getClass().getName());
    	
    	System.out.println("方法："+joinPoint.getSignature());
    	System.out.println("方法名："+joinPoint.getSignature().getName());
    	System.out.println("返回值："+result);
    	
    	return result;
  	}
}