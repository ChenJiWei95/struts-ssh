package com.shop.control.fore;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.shop.Constants;
import com.shop.control.SuperActionSupport;
import com.shop.entity.User;
import com.shop.entity.UserInfor;
import com.shop.service.UserInforService;
import com.shop.service.UserService;
import com.shop.util.Message;
import com.shop.util.SnowFlakeGenerator;
/**
 * @version: V 1.0 
 * @Description:
 * 用户
 * @author: cjw 
 * @date: 2019年10月30日 上午11:39:22
 */
@Component 				// 表示此类将被spring容器托管，能实现依赖对象的控制反转，例如：@Autowired注解获取userServiceImpl对象
@Scope("prototype")		// 表示每次获得bean都会生成一个新的对象
public class UserAction extends SuperActionSupport implements ServletRequestAware{
	private static final long serialVersionUID = 7539369474585568995L;
	
	private static final Logger log = Logger.getLogger(UserAction.class); // 日志对象
	
	private HttpServletRequest request;
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
		
	}
	public HttpServletRequest getRequest() {  
        return request;  
    }
	
	private User user = null;
	private UserInfor userInfor = null;
	
	public User getUser() {
		user = user == null ? new User() : user;
		return user;
	}	
	
	public UserInfor getUserInfor() {
		userInfor = userInfor == null ? new UserInfor() : userInfor;
		return userInfor;
	}	
	
	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private UserInforService userInforServiceImpl;
	
	/**
	 * 添加方法 测试
	 * 链接格式 当前类为例：testAjax(类前缀)_save(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String save(){
		try { 
			if(null != userServiceImpl.find("from User where username = ?", user.getUsername())) {
				setMessage(new Message().error(getText("shop.error.usernameExists")));
				return JSON;
			}
			String id = String.valueOf(new SnowFlakeGenerator(2,2).nextId());
			user.setId(id);
			user.setLoginCount(0);
			user.setState(Constants.COMMON_STATUS_ENABLEED);
			userServiceImpl.save(user);
			userInfor.setId(String.valueOf(new SnowFlakeGenerator(2,2).nextId()));
			userInfor.setUserId(id);
			userInforServiceImpl.save(userInfor);
			setMessage(new Message().success(getText("shop.error.registerOk")));
		} catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.registerError")+"|"+e));
		} 
		return JSON;
		
	}	
		
	/**
	 * 修改方法 测试
	 * 链接格式 当前类为例：testAjax(类前缀)_update(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String update(){
		// 只能修改 当前账号
		User loginUser = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
		if(!loginUser.getId().equals(user.getId()) || !loginUser.getUsername().equals(user.getUsername()))
			return ERROR;
		try {
			userServiceImpl.update(user);
			setMessage(new Message().success(getText("shop.error.updateOk")));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.updateError")));
		}
		return JSON;
	}	
	
	/**
	 * 删除方法 测试
	 * 链接格式 当前类为例：testAjax(类前缀)_delete(方法)
	 * @return
	 */
	// 特殊权限 管理员权限
	/*@SuppressWarnings("static-access")
	public String delete(){
		
		try {
			userServiceImpl.delete(user);
			setMessage(new Message().success(getText("shop.error.deleteOk")));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.deleteError")));
		}
		return JSON;
		
	}	*/
	/**
	 * 删除方法 测试
	 * 链接格式 当前类为例：testAjax(类前缀)_delete(方法)
	 * @return
	 */
	// 特殊权限 管理员权限
	/*@SuppressWarnings("static-access")
	public String delBatch(){
		
		try {
			JSONArray json = JSONObject.parseArray(ActionUtil.read(request));
			String[] ids = new String[json.size()];
			for(int i = 0; i < json.size(); i++) 
				ids[i] = json.getJSONObject(i).getString("id");
			if(json.size() > 0) 
				userServiceImpl.delBatch(ids);
			setMessage(new Message().success(getText("shop.error.deleteOk")));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.deleteError")));
		}
		return JSON;
		
	}*/	
		
	/**
	 * 获取方法 测试
	 * 链接格式 当前类为例：testAjax(类前缀)_list(方法)
	 * @return
	 */
	
	/*@SuppressWarnings("static-access")
	public String list(){
		
		try {
			Map<String, String> map = ActionUtil.getRequestParameterMap(request);
			StringBuilder eq = new StringBuilder("from User where 1=1");
			List<Object> param = new ArrayList<Object>(map.size());
			for(Map.Entry<String, String> item : map.entrySet()) {
				eq.append("AND"+item.getKey()+"=?");
				param.add(item.getValue());
			}
			List<User> list = userServiceImpl.findList(eq.toString(), param.toArray());	
			setMessage(new Message().success(getText("shop.error.getOk"), list));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
		
	}*/
	
	/**
	 * 获取方法 测试
	 * 链接格式 当前类为例：testAjax(类前缀)_list(方法)
	 * @return
	 */
	/*@SuppressWarnings("static-access")
	public String get(){
		
		try {
			User t = userServiceImpl.get(user.getId());	
			setMessage(new Message().success(getText("shop.error.getOk"), t));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
		
	}*/
	
	/**
	 * 登录判断
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String login() {
		try {
			User temp;
			if(null != (temp = userServiceImpl.find("from User where username = ?", user.getUsername()))) {
			    System.out.println(temp);
				if (temp.getLoginCount() >= 5) {
			    	setMessage(new Message().error(getText("shop.error.accountLocked")));
			    } else if(Constants.COMMON_STATUS_DISABLEED.equals(temp.getState())) {
					setMessage(new Message().error(getText("shop.error.accountsDisabled")));
				} else if(!temp.getPassword().equals(user.getPassword())) {
					setMessage(new Message().error(getText("shop.error.passError")));
					temp.setLoginCount(temp.getLoginCount()+1);
					userServiceImpl.update(temp);
			    }else {
			    	if (temp.getLoginCount() > 0) {
			    		temp.setLoginCount(0);
					    userServiceImpl.update(temp);
			    	}
				    setMessage(new Message().success(getText("shop.error.loginSuccess")));
				    request.getSession().setAttribute(Constants.LOGIN_SIGN, temp);
				    return JSON;
			    }
			} else {
				setMessage(new Message().error(getText("shop.error.usernameNoExists")));
			}
			return JSON;
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.serverError")));
		}
		return JSON;
	}

}
