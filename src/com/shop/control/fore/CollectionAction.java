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
import com.shop.entity.Collection;
import com.shop.entity.User;
import com.shop.service.CollectionService;
import com.shop.util.ActionUtil;
import com.shop.util.CommonUtil;
import com.shop.util.Message;
import com.shop.util.enums.RequestType;

@Component	 		
@Scope("prototype")
public class CollectionAction extends SuperActionSupport implements ServletRequestAware{

	private static final Logger log = Logger.getLogger(CollectionAction.class); // 日志对象

	private Collection collection; 
	
	@Autowired
	private CollectionService collectionServiceImpl; 
	
	public Collection getCollection() {
		collection = collection == null ? new Collection() : collection;
		return collection;
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
			Collection collection2 = collectionServiceImpl.find("FROM Collection WHERE userId = '"+user.getId()+"'  AND goodsId = '"+collection.getGoodsId()+"'");
			if(collection2 != null) {
				setMessage(Message.success(getText("shop.error.collexist"), collection));
				return JSON;
			}
				
			collection.setId(CommonUtil.getId());
			collection.setUserId(user.getId()); user = null;
			collectionServiceImpl.save(collection);
			setMessage(Message.success(getText("shop.error.collSuccess"), collection));
			user = null;
		} catch (Exception e) {
			e.printStackTrace();
			setMessage(Message.error(getText("shop.error.collfail")));
		}
		return JSON;
	}	
	/**
	 * 修改方法 测试
	 * 链接格式 当前类为例：user(类前缀)_update(方法)
	 * @return
	 */
	@SuppressWarnings("static-access")
	public String update(){
		try {
			collectionServiceImpl.update(collection);
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
	public String delete(){
		
		try {
			collectionServiceImpl.delete(collection);
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
	public String delBatch(){
		
		try {
			JSONArray json = JSONObject.parseArray(ActionUtil.read(request));
			String[] ids = new String[json.size()];
			for(int i = 0; i < json.size(); i++) 
				ids[i] = json.getJSONObject(i).getString("id");
			if(json.size() > 0) 
				collectionServiceImpl.delBatch(ids);
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
			StringBuilder eq = new StringBuilder("from Collection where 1=1");
			List<String> param = new ArrayList<String>(map.size());
			for(Map.Entry<String, String> item : map.entrySet()) {
				eq.append("AND"+item.getKey()+"=?");
				param.add(item.getValue());
			}
			collectionServiceImpl.findList(eq.toString(), (String[]) param.toArray());	
			setMessage(new Message().success(getText("shop.error.getOk")));
		}catch(Exception e) {
			log.info("异常"+e);
			e.printStackTrace();
			setMessage(new Message().error(getText("shop.error.getError")));
		}
		return JSON;
		
	}		
}
