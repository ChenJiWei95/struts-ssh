package com.shop.control;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.opensymphony.xwork2.ModelDriven;
import com.shop.entity.UserTest;
import com.shop.service.UserTestService;
import com.shop.util.Message;
import com.shop.util.PropertiesUtil;
import com.shop.util.SnowFlakeGenerator;



// @ParentPackage("struts-default")  // 此处注释掉的代码与<package name="default" namespace="/" extends="struts-default">此配置意义相同
// @Namespace(value="/")


/**
 * <b>第二步：请求优化</b>
 * <p>
 * 描述:<br>
 * 通配符优化请求方式，使请求控制数据的操作
 * @author 威 
 * <br>2019年10月26日 下午6:16:07 
 * @see
 * @since 1.0
 */
@Component 				// 表示此类将被spring容器托管，能实现依赖对象的控制反转，例如：@Autowired注解获取userServiceImpl对象
@Scope("prototype")		// 表示每次获得bean都会生成一个新的对象
public class TestAjaxAction extends SuperActionSupport{
	private static final long serialVersionUID = 7539369474585568995L;
	
	private static Logger log = Logger.getLogger(TestAjaxAction.class); // 日志对象 
	
	@Autowired
	private UserTestService userTestServiceImpl; 		//@autowired查找bean首先是先通过byType查，如果发现找到有很多bean，则按照byName方式对比获取
	
	// 添加方法 测试
	// 链接格式 当前类为例：testAjax(类前缀)_save(方法)
	public String save(){
		
		log.info("测试前台ajax请求--添加数据：");
		log.info("测试前台ajax请求--添加完毕：");
		setMessage(Message.success("操作成功")); 	// 返回Message序列化对象， 状态成功，返回data=null
		return JSON;
		
	}
	
	// 修改方法 测试
	// 链接格式 当前类为例：testAjax(类前缀)_update(方法)
	public String update(){
		
		log.info("测试前台ajax请求--修改数据：");
		log.info("测试前台ajax请求--修改完毕：");
		setMessage(Message.error("操作失败")); 	// 返回Message序列化对象，状态失败， 返回data=null
		return JSON;
		
	}
	// 删除方法 测试
	// 链接格式 当前类为例：testAjax(类前缀)_delete(方法)
	public String delete(){
		
		log.info("测试前台ajax请求--删除数据：");
		log.info("测试前台ajax请求--删除完毕：");
		setMessage(Message.success("操作成功")); 	// 返回Message序列化对象， 状态成功，返回data=null
		return JSON;
		
	}
	
	// 获取方法 测试
	// 链接格式 当前类为例：testAjax(类前缀)_list(方法)
	public String list(){
		
		log.info("测试前台ajax请求--获取数据：");
		log.info("测试前台ajax请求--获取完毕：");
		UserTest u = new UserTest();
		u.setId(new SnowFlakeGenerator(2, 2).nextId());
		u.setUsername("xiaoming");
		setMessage(Message.success("操作成功", 
				u));							// 返回Message序列化对象， 状态成功， 返回data=User
		return JSON;
		
	}
	 
	// 负责页面跳转 
	// 链接格式 当前类为例：testAjax(类前缀)_chtml(方法，固定为chtml)_success(跳转页面，这里以success.jsp为例)
	public String chtml(){
		
		log.info("测试前台ajax请求--跳转页面：");
		log.info("测试前台ajax请求--跳转完毕：");
		return CHTML;
		
	}
	 
}
