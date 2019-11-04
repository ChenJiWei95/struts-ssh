package com.shop.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.shop.annotation.RequestTypeAnno;
/**
 * <b>拦截POST GET请求</b>
 * <p>
 * 描述:<br>
 * 
 * @author 威 
 * <br>2019年11月2日 下午8:35:08 
 * @see
 * @since 1.0
 */
public class RequestTypeInterceptor extends AbstractInterceptor {
 
	private static final long serialVersionUID = 5543269481004367532L;
	private static Logger log = Logger.getLogger(RequestTypeInterceptor.class);
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		Action action = (Action) invocation.getAction();
		Method method = action.getClass().getMethod(invocation.getProxy().getMethod(),  new Class[] {});
		Annotation[] annotations = method.getAnnotations();
		String methodName = ServletActionContext.getRequest().getMethod();
		System.out.println(methodName);
		for (Annotation annotation : annotations) {
			if (annotation instanceof RequestTypeAnno) {
                RequestTypeAnno reqTypeAnnotation = (RequestTypeAnno) annotation;
                if (!reqTypeAnnotation.value().name().equalsIgnoreCase(methodName)) {
                    // 当前台用户请求类型不是方法注解中对应类型时提示此信息
                    return "error";
                }
            }
		}
		return invocation.invoke();	
	} 
}