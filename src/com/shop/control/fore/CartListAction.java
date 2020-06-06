package com.shop.control.fore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

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
import com.shop.entity.CartList;
import com.shop.entity.ResponseData;
import com.shop.entity.User;
import com.shop.service.CartListService;
import com.shop.util.ActionUtil;
import com.shop.util.CommonUtil;
import com.shop.util.Message;
import com.shop.util.enums.RequestType;

@Component	 		
@Scope("prototype")
public class CartListAction extends SuperActionSupport implements ServletRequestAware{

	private static final Logger log = Logger.getLogger(CartListAction.class); // 日志对象

	private CartList cartList; 
	
	@Autowired
	private CartListService cartListServiceImpl; 
	
	public CartList getCartList() {
		cartList = cartList == null ? new CartList() : cartList;
		return cartList;
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
			CartList cartList2 = cartListServiceImpl.find("FROM CartList WHERE colour = '"+cartList.getColour()+"' AND userId = '"+user.getId()+"' AND size = '"+cartList.getSize()+"' AND goodsId = '"+cartList.getGoodsId()+"'");
			if(cartList2 != null) {
				cartList2.setCount(cartList2.getCount()+cartList.getCount());
				cartListServiceImpl.update(cartList2);
				setMessage(Message.success(getText("shop.error.cartSuccess")));
				return JSON;
			}
			cartList.setId(CommonUtil.getId());
			cartList.setUserId(user.getId());
			cartListServiceImpl.save(cartList);
			user = null; 
			setMessage(Message.success(getText("shop.error.cartSuccess")));
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(Message.error(getText("shop.error.cartfail")));
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
			JSONObject json = JSONObject.parseObject(ActionUtil.read(request));
			log.info(json.toString());
			StringBuilder eqS = new StringBuilder(" WHERE");
			JSONObject query = json.getJSONObject("query");
			for(Entry<String, Object> item : query.entrySet())
				eqS.append(" u."+item.getKey() + " = " + (item.getValue() instanceof String ? "'"+item.getValue()+"'" : item.getValue())).append(" AND");
			
			StringBuilder setS = new StringBuilder(" SET"); //`count` = 3
			JSONObject set = json.getJSONObject("set");
			Map<String, Object> setMap = new HashMap<String, Object>();
			for(Entry<String, Object> item : set.entrySet()) {
				setMap.put(item.getKey(), item.getValue());
				setS.append(" u."+item.getKey() + " = " + ":"+item.getKey()).append(" AND");
			}
			if(setS.toString().length() > 4) setS.delete(setS.toString().length()-4, setS.toString().length());
			if(eqS.toString().length() > 12) eqS.delete(eqS.toString().length()-4, eqS.toString().length());
			//update User u set u.userName=:userName where u.userId=:userId
			log.info("UPDATE CartList u"+setS.toString()+eqS.toString());
			cartListServiceImpl.update("UPDATE CartList u"+setS.toString()+eqS.toString(), setMap);
			 
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
			log.info(cartList);
			cartListServiceImpl.delete(cartList);
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
				cartListServiceImpl.delBatch(ids);
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
			StringBuilder eq = new StringBuilder("from CartList where 1=1");
			List<String> param = new ArrayList<String>(map.size());
			for(Map.Entry<String, String> item : map.entrySet()) {
				eq.append(" AND "+item.getKey()+"=?");
				param.add(item.getValue());
			}
			User user = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
			eq.append(" AND u_id="+user.getId());
			user = null;
			List<CartList> list = cartListServiceImpl.findList(eq.toString(), param.toArray());	
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
			CartList t = cartListServiceImpl.get(cartList.getId());	
			setMessage(new Message().success(getText("shop.error.getOk"), t));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
		
	}	
	@SuppressWarnings("static-access")
	public String count() {
		try {
			User user = (User) request.getSession().getAttribute(Constants.LOGIN_SIGN);
			ResponseData d  =new ResponseData();
			d.setCount(cartListServiceImpl.count("FROM CartList where userId = ?", user.getId()));
			setMessage(new Message().success(getText("shop.error.getOk"), d));
			user = null;
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
	}
}
