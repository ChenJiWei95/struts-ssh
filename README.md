# struts-ssh   

## 引言  
	* 表	
		物流表（logistics）
			id province city area street order_id name phone nickname
			logisticsId province city area street orderId name phone nickname
			String String String String String String String String String
		订单项表(order_item)
			id name url price discount count total g_id colour size order_id
			orderItemId name url price discount count total goodsId colour size orderId
			String String String j String Integer Integer j String String String String
		购物车(cart_list)
			id name url count g_id u_id colour size
			id name url count goodsId userId colour size
			String String String Integer String String String String
		订单(order)
			id create_date payment_status logistics_status total_amount original_amount
			id createDate paymentStatus logisticsStatus totalAmount originalAmount
			String String String String j j
			
	* 案例页面  
		http://localhost:8080/strust-ssh/main_main_practice
	* 注解和配置  
		请求件TestAction类
		1、配置文件的方式构建：
	 	struts整合spring之后也就代表着struts action对象可以归spirng容器管理，此时需要添加注解@Component() 、@Scope("prototype")
	 	spring容器管理对象的好处，使得相互依赖的对象解耦。struts配置文件设置请见name="testAction"
	 	
	 	2、注解方式配置
	 	请见test()方法，此时struts配置文件无需添加任何相关配置
	* 请求优化  
		请求件TestAjaxAction类
		通配符优化请求方式，使请求控制数据的操作；以当前测试类为参考，请求链接如下
		testAjax(类前缀)_save(动作方法)
		testAjax(类前缀)_update(动作方法)
		testAjax(类前缀)_delete(动作方法)
		testAjax(类前缀)_list(动作方法)
		testAjax(类前缀&文件名)_chtml(动作方法，固定为chtml为页面跳转处理)_success(跳转页面，这里以success.jsp为例)
	* 参数的获取    	 
		模型驱动
			TestParameAction.java
			WEB-INF/views/testparam/saveorupdate.jsp
		对象驱动  
			TestParame2Action.java
			WEB-INF/views/testparam2/saveorupdate.jsp
		属性驱动
			TestParame3Action.java
			WEB-INF/views/testparam3/saveorupdate.jsp
		request请求
			TestParame4Action.java
			WEB-INF/views/testparam4/saveorupdate.jsp
	* 测试redis
		依赖架包： 
			commons-pool-1.5.4.jar 
			commons-pool.jar 
			commons-pool2-2.4.2.jar 
			jedis-2.9.0.jar 
			spring-data-redis-1.7.10.RELEASE.jar
		RedisService 为工具类
		TestParameAction.saveorupdate中进行测试插入，获取数据。测试结果正常
		在http://localhost:8080/strust-ssh/main_main_practice页面进行模型驱动测试即可测试redis
## 异常收集：    
	* InvalidMappingException:  
		Could not parse mapping document from input stream
			无法解析来自输入流的映射文档
		Could not get constructor for org.hibernate.persister.entity.SingleTableEntityPersister
			实体类和那个映射没对应起来
			class不能和数据库中的表映射；
			SetterGetter方法没对应或者缺少或者不规范
		Could not get constructor for org.hibernate.persister.entity.SingleTableEntityPersister报错解决办法
			在做Hibernate框架数据库的关联关系映射练习中出现了Could not get constructor for org.hibernate.persister.entity.SingleTableEntityPersister错误。
			在百度上搜了很多答案，检查了自己的映射配置文件，都没有发现错误，最后在CSDN的一篇博客上找到了答案，博客地址：http://blog.csdn.net/weixin_36380516/article/details/52876204
			看了文章好检查了自己的实体类，也没有发现什么问题，后来就把有关的实体类中的get和set方法都删除，重新生成一次，错误就没有了。
			总结了一下，出现这种问题的原因可能是自己的实体类中的属性对应的get和set方法没有按照规范去写，所以才造成了这个问题。
			最后解决就是将曾经改动的最有可能出现问题的实体类的get、set方法删掉重新生成
	* org.hibernate.MappingException  
		: Association references unmapped class: com.shop.entity.MerchantMessage
		此异常出现在：memberAction register
		网上解决方案：
			1、查看User有没有写对应的映射文件User.hbm.xml
			2、写了映射文件，在hibernate.cfg.xml中有没有注册，注册的路径对不对<mapping resource="com/fate/oa/domain/User.hbm.xml" />
		个人解决方案：
			原因是 没有在applicationContext.xml添加相应的映射配置
			在applicationContext.xml 中增加<value>com/shop/entity/MerchantMessage.hbm.xml</value>
	* Could not find action or result  
		/shop/undefined
		There is no Action mapped for namespace [/] and action name 
		[undefined] associated with context path [/shop]. - [unknown location]
		链接：https://blog.csdn.net/yhl_jxy/article/details/50479925
	* org.apache.struts2.dispatcher.StreamResult error  
		Can not find a java.io.InputStream with the name [inputStream] in the invocation stack. 
		Check the <param name="inputName"> tag specified for this action.			
	
	* java.lang.StackOverflowError  
		StackOverflowError是由于当前线程的栈满了  ，也就是函数调用层级过多导致。
		比如死递归。
		
	* java.util.concurrent.ExecutionException: org.apache.catalina.LifecycleException:   
		Failed to initialize component [org.apache.catalina.webresources.JarResourceSet@67e2d983]
# hibernate：  
	* inverse = true 的使用：在set标签中使用时保存不会关联有关联的表  
	* cascade(属性)  
		属性值：
			save-update			save/update/saveOrUpdate操作时级联
			delete				delete操作时级联
			delete-orphan		孤儿删除，删除所有和当前对象解除关联关系的对象
			all					所有情况均进行关联，不包含孤儿删除
			all-delete-ophan	所有情况均进行关联
			none 				不关联
		使用 ：one-to-one, set, many-to-one 
		效果：使用后只要操作主键对象就可以关联操作外键对象
		案例：MemberDaoImpl saveInfor()
			附加代码：
			Member member = new Member();
			member.setUsername((String) obj.get("username"));
			member.setPassword((String) obj.get("password"));
			
			MemberInfor infor = new MemberInfor();
			infor.setEmail((String) obj.get("email"));
			infor.setIntegral("10");
			infor.setLevel(0);
			infor.setPhone_number((String) obj.get("phoneNumber"));
			infor.setSex((String) obj.get("sex"));
			infor.setTrueName((String) obj.get("trueName"));
			menber.setInfor(infor);	  //只需要在主键对象设置外键对象，不要执行infor的set方法
			getSession().save(menber);//只需要操作主键对象，外键对象将会自动关联操作
	* Query查询所有   
		例如：from Merchant 中的Merchant需要使用类名
		
	* Criteria  
		1、使用这个对象查询全部数据，不需要写语句，直接调用方法实现即可
		例：Criteria criteria=session.createCriteria(UsersEntity.class);//创建Criteria对象，此方法需要给出实体类名称
	        List<UsersEntity> usersEntitys=criteria.list();//调用Criteria方法进行查询
		2、该对象由session 执行createCriteria 创建
		Criteria执行add方法添加查询条件，可以使用Restrictions的静态方法eq("name", "1281384046")生成一个查询条件
		最后Criteria执行list() 可获取结果集； 执行uniqueResult()	只获取一个结果
	        
	* SQLQuery对象  
		使用这个对象查询全部数据，需要写底层sql。
		例：SQLQuery sqlQuery=session.createSQLQuery("select * from users");//创建SQLQuery对象，需要传入底层sql语句
		        sqlQuery.addEntity(UsersEntity.class);//返回的list是数组，需要将其转换成实体类对象
		        List<UsersEntity> usersEntitys=sqlQuery.list();//调用方法进行查询
	
	* 如果有某一个关联实体没有数据，那么可能是在执行save时外键没有关联上，必须要用set方法关联主键对象  
				
# Spring 注解自动写入：    
	配置文件中需要添加bean并标明autowire="byName"
		<bean id="XXX" class="XXX.XXX.XXX.XXX"  autowire="byName" />
	@Resource(name="merchantInforDaoImpl")
		在需要自动写入的属性前添加
		该属性需要setter方法
		

# Dao层：  
	级联操作中的关联对象的操作需要同一个session操作  
