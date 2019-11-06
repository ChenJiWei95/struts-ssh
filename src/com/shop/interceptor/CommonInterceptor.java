package com.shop.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CommonInterceptor extends AbstractInterceptor {
 
	private static final long serialVersionUID = 5543269481004367532L;
	private static Logger log = Logger.getLogger(CommonInterceptor.class);
	
	private Action ac;
	
	@Override
	public void destroy() {
		// 销毁
		// 销毁才执行
		System.out.println("CommonInterceptor end");
		log.info(ac.getClass().getName());
	}
	
	public String intercept(ActionInvocation invocation) throws Exception {
		ac = (Action) invocation.getAction();
//		ActionContext ac = invocation.getInvocationContext();
		HttpServletRequest request= (HttpServletRequest) invocation.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		System.out.println(request.getSession());
		// 传自身uri让语言切换模块化自动提供uri
		request.getSession().setAttribute("URIPath", request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1));
		
		log.info("进行前置拦截处理："+request.getSession().getAttribute("URIPath"));
		return invocation.invoke();	
		/*
		if(no login)
			return login
		*/
	} 
}