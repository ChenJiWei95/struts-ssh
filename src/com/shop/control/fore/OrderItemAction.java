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
import com.shop.annotation.RequestTypeAnno;
import com.shop.control.SuperActionSupport;
import com.shop.entity.OrderItem;
import com.shop.service.OrderItemService;
import com.shop.util.ActionUtil;
import com.shop.util.Message;
import com.shop.util.enums.RequestType;

@SuppressWarnings("serial")
@Component	 		
@Scope("prototype")
public class OrderItemAction extends SuperActionSupport implements ServletRequestAware{

	private static final Logger log = Logger.getLogger(OrderItemAction.class); // 日志对象

	private OrderItem orderItem; 
	
	@Autowired
	private OrderItemService orderItemServiceImpl; 
	
	public OrderItem getOrderItem() {
		orderItem = orderItem == null ? new OrderItem() : orderItem;
		return orderItem;
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
//			orderItem.setId(CommonUtil.getId());
			orderItemServiceImpl.save(orderItem);
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
			orderItemServiceImpl.update(orderItem);
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
			orderItemServiceImpl.delete(orderItem);
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
				orderItemServiceImpl.delBatch(ids);
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
			StringBuilder eq = new StringBuilder("from OrderItem where 1=1");
			List<String> param = new ArrayList<String>(map.size());
			for(Map.Entry<String, String> item : map.entrySet()) {
				eq.append(" AND "+item.getKey()+"=?");
				param.add(item.getValue());
			}
			List<OrderItem> list = orderItemServiceImpl.findList(eq.toString(), param.toArray());	
			setMessage(new Message().success(getText("shop.error.getOk"), list));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
		
	}	
	
	/**
	 * 获取方法 测试
	 * 链接格式 当前类为例：user(类前缀)_list(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String get(){
		
		try {
//			OrderItem t = orderItemServiceImpl.get(orderItem.getId());	
//			setMessage(new Message().success(getText("shop.error.getOk"), t));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
		
	}	
}
