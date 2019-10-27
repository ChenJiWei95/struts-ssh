package com.shop.control;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component 				// 表示此类将被spring容器托管，能实现依赖对象的控制反转，例如：@Autowired注解获取userServiceImpl对象
@Scope("prototype")		// 表示每次获得bean都会生成一个新的对象
public class MainAction extends SuperActionSupport{
	private static final long serialVersionUID = 7539369474585568995L;
	
	private static final Logger log = Logger.getLogger(MainAction.class); // 日志对象
	
	public String main(){
		
		log.info("主要页面跳转：");
		log.info("跳转完毕：");
		return MAIN;
	}
}
