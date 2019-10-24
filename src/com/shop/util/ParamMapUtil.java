package com.shop.util;

import java.util.HashMap;
import java.util.Map;

import com.JSON2.json.JSONObject;

/**
 * <b>前后台交互时对表单数据的处理</b>
 * <p>
 * 描述:<br>
 * ActionContext.getContext().getParameters();
 * @author 威 
 * <br>2018年9月12日 下午10:06:45 
 * @since 1.0
 */
public class ParamMapUtil {
	/**
	 * 前台表单数据集
	 */
	private Map<String, Object> paramMap;
	public ParamMapUtil (Map<String, Object> paramMap){
		this.paramMap = paramMap;
	}
	/**
	 * 根据name属性集获取json格式的表单数据
	 * <p>	 
	 * @param names	表单input标签name属性值集
	 * @return
	 * JSONObject
	 * @see
	 * @since 1.0
	 */
	public JSONObject getJSONData(String... names){
		JSONObject obj = new JSONObject();
		for(String name : names){
			obj .put(name, get(name));
		}
		return obj;
	}
	/**
	 * 根据name属性集获取map格式的表单数据
	 * <p>	 
	 * @param names	表单input标签name属性值集
	 * @return
	 * Map<String,Object>
	 * @since 1.0
	 */
	public Map<String, Object> getMapData(String... names){
		Map<String, Object> map = new HashMap<>(names.length);
		for(String name : names){
			map.put(name, get(name));
		}
		return map;
	}
	/**
	 * 根据单个name属性值获取单个表单数据
	 * <p>	 
	 * @param name	表单input标签name属性值
	 * @return
	 * Object
	 * @see
	 * @since 1.0
	 */
	public Object get(String name){
		return ((String[]) paramMap.get(name))[0];
	}
}
