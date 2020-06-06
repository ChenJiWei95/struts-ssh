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
import com.shop.Constants;
import com.shop.annotation.RequestTypeAnno;
import com.shop.control.SuperActionSupport;
import com.shop.entity.Address;
import com.shop.entity.Logistics;
import com.shop.entity.User;
import com.shop.service.AddressService;
import com.shop.service.LogisticsService;
import com.shop.util.ActionUtil;
import com.shop.util.CommonUtil;
import com.shop.util.Message;
import com.shop.util.enums.RequestType;

@Component	 		
@Scope("prototype")
public class LogisticsAction extends SuperActionSupport implements ServletRequestAware{

	private static final Logger log = Logger.getLogger(LogisticsAction.class); // 日志对象

	private Logistics logistics; 
	
	@Autowired
	private LogisticsService logisticsServiceImpl; 
	
	@Autowired
	private AddressService addressServiceImpl;
	
	public Logistics getLogistics() {
		logistics = logistics == null ? new Logistics() : logistics;
		return logistics;
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
			String addressId = request.getParameter("addressId");
			log.info(addressId);
			if(addressId == null || "".equals(addressId.trim())) setMessage(Message.error("请选择地址"));
			Address address = addressServiceImpl.get(addressId); 
			logistics.setArea(address.getArea());
			logistics.setCity(address.getCity());
			logistics.setStreet(address.getStreet());
			logistics.setProvince(address.getProvince());
			logistics.setName(address.getName());
			logistics.setPhone(address.getPhone());
			
			logistics.setLogisticsId(CommonUtil.getId());
			logistics.setNickname(user.getUsername());
			logisticsServiceImpl.save(logistics);
			setMessage(Message.success("支付处理中..."));
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(Message.error("支付操作失败"));
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
			logisticsServiceImpl.update(logistics);
			setMessage(new Message().success("支付处理中..."));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error("支付操作失败"));
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
			logisticsServiceImpl.delete(logistics);
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
				logisticsServiceImpl.delBatch(ids);
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
			StringBuilder eq = new StringBuilder("from Logistics where 1=1");
			List<String> param = new ArrayList<String>(map.size());
			for(Map.Entry<String, String> item : map.entrySet()) {
				eq.append(" AND "+item.getKey()+"=?");
				param.add(item.getValue());
			}
			List<Logistics> list = logisticsServiceImpl.findList(eq.toString(), param.toArray());	
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
			Logistics t = logisticsServiceImpl.get(logistics.getLogisticsId());	
			setMessage(new Message().success(getText("shop.error.getOk"), t));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
		
	}	
}
