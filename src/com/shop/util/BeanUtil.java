package com.shop.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanUtil {
	public static Object getBean(String beanName){
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml") ;
		return context.getBean(beanName) ;
	}
}
