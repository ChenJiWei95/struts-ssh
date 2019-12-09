package com.shop.control.fore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shop.control.SuperActionSupport;
import com.shop.entity.Address;
import com.shop.entity.User;
import com.shop.service.AddressService;
import com.shop.util.ActionUtil;
import com.shop.util.Message;
import com.shop.util.SnowFlakeGenerator;
import com.shop.Constants;
import com.shop.annotation.RequestTypeAnno;
import com.shop.util.enums.RequestType;

@Component	
@Scope("prototype")
public class AddressAction extends SuperActionSupport implements ServletRequestAware{

	private static final Logger log = Logger.getLogger(AddressAction.class); // 日志对象

	private Address address;
	
	@Autowired
	private AddressService addressServiceImpl;
	
	public Address getAddress() {
		address = address == null ? new Address() : address;
		return address;
	}
	
	private HttpServletRequest request;
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
		
	}
	public HttpServletRequest getRequest() {  
        return request;  
    }
	
	/**
	 * 添加方法 测试
	 * 链接格式 当前类为例：user(类前缀)_save(方法)
	 * @return
	 */
	@RequestTypeAnno(RequestType.POST)
	public String save(){
		try {
			User user = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
			address.setId(String.valueOf(new SnowFlakeGenerator(2, 2).nextId()));
			address.setUserId(user.getId());
			addressServiceImpl.save(address);
			setMessage(Message.success("添加成功"));
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(Message.success("添加失败"));
		}
		return JSON;
	}	
	/**
	 * 修改方法 测试
	 * 链接格式 当前类为例：user(类前缀)_update(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestTypeAnno(RequestType.POST)
	public String update(){
		try {
			User user = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
			address.setUserId(user.getId());
			addressServiceImpl.update(address);
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
	 * 链接格式 当前类为例：user(类前缀)_delete(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestTypeAnno(RequestType.POST)
	public String delete(){
		
		try {
			User user = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
			address.setUserId(user.getId());
			addressServiceImpl.delete(address);
			setMessage(new Message().success(getText("shop.error.deleteOk")));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.deleteError")));
		}
		return JSON;
		
	}	
	/**
	 * 删除方法 测试
	 * 链接格式 当前类为例：user(类前缀)_delete(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	@RequestTypeAnno(RequestType.POST)
	public String delBatch(){
		
		try {
			JSONArray json = JSONObject.parseArray(ActionUtil.read(request));
			String[] ids = new String[json.size()];
			for(int i = 0; i < json.size(); i++) 
				ids[i] = json.getJSONObject(i).getString("id");
			if(json.size() > 0) 
				addressServiceImpl.delBatch(ids);
			setMessage(new Message().success(getText("shop.error.deleteOk")));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.deleteError")));
		}
		return JSON;
		
	}			
			
	/**
	 * 获取方法 测试
	 * 链接格式 当前类为例：user(类前缀)_list(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String list(){
		
		try {
			Map<String, String> map = ActionUtil.getRequestParameterMap(request);
			Integer limit = Integer.parseInt(map.get("limit"));
			Integer page = Integer.parseInt(map.get("page"));
			map.remove("limit");
			map.remove("page");
			
			StringBuilder eq = new StringBuilder("from Address where 1=1");
			List<String> param = new ArrayList<String>(map.size());
			for(Map.Entry<String, String> item : map.entrySet()) {
				eq.append(" AND "+item.getKey()+"=?");
				param.add(item.getValue());
			}
			User user = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
			eq.append(" AND userId=?");
			param.add(user.getId());
			List<Address> list = addressServiceImpl.findByPage(eq.toString(), 
					page, 
					limit, 
					param.toArray());
			setMessage(new Message().success(getText("shop.error.getOk"), list));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
		
		
		/*try {
			Map<String, String> map = ActionUtil.getRequestParameterMap(request);
			Integer limit = Integer.parseInt(map.get("limit"));
			Integer page = Integer.parseInt(map.get("page"));
			map.remove("limit");
			map.remove("page");
			
			StringBuilder eq = new StringBuilder("from Address where 1=1");
			Object[] param = new Object[map.size()+1];
			int count = 0;
			for(Map.Entry<String, String> item : map.entrySet()) {
				eq.append(" AND "+item.getKey()+"=?");
				param[count++] = item.getValue();
			}
			User user = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
			eq.append(" AND userId"+"=?");
			param[count++] = user.getId();
			List<Address> list = addressServiceImpl.findByPage(eq.toString(), 
					page, 
					limit, 
					param);
			setMessage(new Message().success(getText("shop.error.getOk"), list));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;*/
		
	}	
	
	/**
	 * 获取方法 测试
	 * 链接格式 当前类为例：user(类前缀)_list(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String get(){
		
		try {
			Address t = addressServiceImpl.get(address.getId());	
			setMessage(new Message().success(getText("shop.error.getOk"), t));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
		
	}	
}
