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
import com.shop.util.SnowFlakeGenerator;



// @ParentPackage("struts-default")  // 此处注释掉的代码与<package name="default" namespace="/" extends="struts-default">此配置意义相同
// @Namespace(value="/")


/**
 * <b>第一步：注解和配置</b>
 * <p>
 * 描述:<br>
 * <h1>struts以注解和配置文件的方式构建action控制类</h1>
 * 1、配置文件的方式构建：<br>
 * struts整合spring之后也就代表着struts action对象可以归spirng容器管理，此时需要添加注解@Component() 、@Scope("prototype")<br>
 * spring容器管理对象的好处，使得相互依赖的对象解耦。struts配置文件设置请见name="testAction"<br>
 * <p>
 * 2、注解方式配置<br>
 * 请见test()方法，此时struts配置文件无需添加任何相关配置
 * <p>
 * 模板驱动  获取前台参数
 * 
 * @author 威 
 * <br>2019年10月26日 下午6:16:07 
 * @see
 * @since 1.0
 */
@Component	 			// 表示此类将被spring容器托管，能实现依赖对象的控制反转，例如：@Autowired注解获取userServiceImpl对象
@Scope("prototype")		// 表示每次获得bean都会生成一个新的对象
public class TestAction extends SuperActionSupport implements ModelDriven<UserTest>{
	private static final long serialVersionUID = 7539369474585568995L;
	private static Logger log = Logger.getLogger(TestAction.class);
	
	@Autowired
	private UserTestService userTestServiceImpl; //@autowired查找bean首先是先通过byType查，如果发现找到有很多bean，则按照byName方式对比获取
	
	private UserTest user = new UserTest();
	
	/**
	 * 模板驱动  获取前台参数 <br>
	 * 实现ModelDriven接口 
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public UserTest getModel() {
		return user;
	}

	public String execute(){
		log.info("配置文件的方式构建action：开始执行"); 
		UserTest u = new UserTest();
		u.setId(new SnowFlakeGenerator(2, 2).nextId());
		u.setUsername("xiaoming");
		userTestServiceImpl.save(u);
		print("true");  // 如果是以ajax请求 返回数据 此处得有流输出，否则报此异常Can not find a java.io.InputStream with the name
		return SUCCESS;
	}
	
	public String stream(){
		log.info("测试前台ajax请求--返回一般数据：");
		log.info("前台获取数据： "+user);
		print("返回响应数据");
		log.info("测试结束");
		return SUCCESS;
	}
	
	public String json(){
		log.info("测试前台ajax请求--返回对象序列化数据：");
		log.info("前台获取数据： "+user);
		UserTest u = new UserTest();
		u.setId(new SnowFlakeGenerator(2, 2).nextId());
		u.setUsername("xiaoming"); 
		setMessage(Message.success("请求成功", u));
		log.info("测试结束");
		return SUCCESS;
	}
	
	
	/*
	上面注解在配置中的写法：
	<action name="testAno" class="com.shop.control.TestAction"> 
		<result name="success">/index.jsp</result>
		<result name="error">/error.jsp</result>
	</action>
	*/
	/*@Action( //表示请求的Action及处理方法
			value="testAno",  //表示action的请求名称
			results={  //表示结果跳转
					@Result(name="success",location="/index.jsp",type="redirect"),
					@Result(name="login",location="/login.jsp",type="redirect"),
					@Result(name="error",location="/error.jsp",type="redirect")
			}
		)
	public String test(){	
		log.info("注解方式构建action：开始执行"); 
		User u = new User();
		u.setId(new SnowFlakeGenerator(2, 2).nextId());
		u.setUsername("xiaoming"); 
		userServiceImpl.save(u);
		log.info(u);
		print("true") ;
		return SUCCESS ;
	}*/
}
