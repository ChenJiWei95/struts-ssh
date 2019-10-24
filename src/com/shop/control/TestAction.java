package com.shop.control;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.shop.entity.User;
import com.shop.service.UserService;

@Component() 
@Scope("prototype")
@ParentPackage("struts-default")  
@Namespace(value="/")
public class TestAction extends SuperActionSupport {
	private static final long serialVersionUID = 7539369474585568995L;
	
	@Autowired
	private UserService userServiceImpl;

	/*public String execute(){
		System.out.println("你好");
		User u = new User();
		u.setUsername("xjx");
		System.out.println(userServiceImpl);
		userServiceImpl.save(u);
		return SUCCESS;
	}
*/	
	@Action( //表示请求的Action及处理方法
		value="testAction",  //表示action的请求名称
		results={  //表示结果跳转
				@Result(name="success",location="/index.jsp",type="redirect"),
				@Result(name="login",location="/login.jsp",type="redirect"),
				@Result(name="error",location="/error.jsp",type="redirect")
		}
	)
	public String test(){
		System.out.println("你好");
		User u = new User();
		u.setUsername("xjx");
		System.out.println(userServiceImpl);
		userServiceImpl.save(u);
		System.out.println(com.opensymphony.xwork2.ActionContext.getContext().getParameters());
		print("true") ;
		return SUCCESS ;
	}
}
