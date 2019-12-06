package com.shop;

import java.math.BigDecimal;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shop.Filter.Operator;
import com.shop.Order.Direction;
import com.shop.util.CommonUtil;


/**
 * <b>查询通用帮助类 按一定格式处理 </b>
 * <p>
 * 例如：Qu_x#name_eq_s  得出查询语句为： and x.name = :name 其中s是指string
 * S=String L=Long I=Integer D=Double DT=Date ST=Short BD=BigDecimal F=Float 
 * 描述:<br>
 * 
 * @author 威 
 * <br>2019年10月18日 下午11:25:27 
 * @see
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public class QueryHelper {
	
private static Logger log = LoggerFactory.getLogger(QueryHelper.class);
	
	private Page page;//存在对应查询及排序信息
	private StringBuffer hql;
	private Map<String,Object> params = new HashMap<String, Object>();;//查询参数及对应值
	
	/**
	 * 查询参数绑定
	 * @param request
	 * @param page
	 * @return 
	 */
	
	public void paramBind(HttpServletRequest request,Page page){
		this.page = page;
		if (request.getMethod().equalsIgnoreCase("POST")) {
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				String value = request.getParameter(name);
				//System.out.println(name+"---"+value);
				this.addFilter(name,value);
			}
		}
		
		if(StringUtils.isNotEmpty(page.getSort())){
			Order order = new Order();
			order.setProperty(page.getSort());
			if(StringUtils.isNotEmpty(page.getOrder())){
				order.setDirection(Direction.fromString(page.getOrder()));
			}
			this.page.addOrder(order);
		}
		
	}
	
	/**
	 * 增加查询条件
	 * @param name
	 * @param value
	 */
	private void addFilter(String name,String value){
		if(name.startsWith(SysConstant.QUERY_PRE) && StringUtils.isNotEmpty(value)){
			String[] params = name.split(SysConstant.QUERY_SPIT);
			if(params.length == 4){
				Filter filter = new Filter();
				filter.setProperty(params[1].replaceAll("#", "."));
				filter.setOperator(Operator.fromString(params[2]));
				filter.setValue(this.getValue(params[3],value));
				this.page.addFilter(filter);
			} else {
				System.err.println(name+"属性非法！");
			}
		} else if(name.startsWith(SysConstant.UPDATE_PRE) && StringUtils.isNotEmpty(value)){
			String[] params = name.split(SysConstant.QUERY_SPIT);
			if(params.length == 3){
				UpdateItem updateItem = new UpdateItem();
				updateItem.setProperty(params[1].replaceAll("#", "."));
				updateItem.setValue(this.getValue(params[2],value));
				this.page.addUpdateItem(updateItem);
			} else {
				System.err.println(name+"属性非法！");
			}
		} else {
			System.err.println(name+"属性非法！");
		}
	}
	
	/**
	 * 将对应类型转换
	 * @param valueType
	 * @param value
	 * @return
	 */
	private Object getValue(String valueType,String value) {
		value = value.trim();
		try {
			if(StringUtils.endsWithIgnoreCase(valueType, "s")){
				return value;
			}
			
			if(StringUtils.endsWithIgnoreCase(valueType, "l")){
				return Long.parseLong(value);
			}
			
			if(StringUtils.endsWithIgnoreCase(valueType, "i")){
				return Integer.parseInt(value);
			}
			
			if(StringUtils.endsWithIgnoreCase(valueType, "d")){
				return Double.parseDouble(value);
			}
			
			if(StringUtils.endsWithIgnoreCase(valueType, "dt")){
				return DateUtils.parseDate(value, SysConstant.DATE_PATTERNS);
			}
			
			if(StringUtils.endsWithIgnoreCase(valueType, "bd")){
				return new BigDecimal(value);
			}
			
			if(StringUtils.endsWithIgnoreCase(valueType, "f")){
				return Float.parseFloat(value);
			}
		} catch (Exception e) {
			log.error("类型转换失败",e);
		}
		
		return null;
	}
	
	
	/**
	 * 创建查询语句包含排序
	 * @param page
	 * @return
	 */
	public String buildAllQuery(Page page){
		hql = new StringBuffer();
		hql.append(this.buildQuery(page.getFilters()));
		hql.append(this.buildOrder(page.getOrders(),page.getAlias()));
		return hql.toString();
	}
	
//	/**
//	 * 组装查询语句
//	 * @param page
//	 */
//	public String buildQuery(Page page){
//		List<Filter> filters = page.getFilters();
//		return this.buildQuery(filters);
//	}
	
	/**
	 * 根据过滤条件组装查询语句
	 * @param filters
	 * @return
	 */
	public String buildQuery(List<Filter> filters){
		StringBuffer hql = new StringBuffer();
		hql.append(" where 1=1 ");
		for(Filter filter : filters){
			hql.append(" and ");
			hql.append(filter.getProperty());
			hql.append(filter.getQueryOperator());
			if(!filter.getOperator().equals(Operator.is) && !filter.getOperator().equals(Operator.no)){
				int count = CommonUtil.cal();
				hql.append(":");
				String property = filter.getProperty().replaceAll("\\.", "_");
				hql.append(property);
				hql.append(count);
				if(filter.getOperator().equals(Operator.lk)){
					params.put(property + count, "%" + filter.getValue() + "%");
				}else if(filter.getOperator().equals(Operator.slk)){
					params.put(property + count, filter.getValue() + "%");
				}else{
					params.put(property + count, filter.getValue());
				}
			}
		}
		return hql.toString();
	}
	public String buildUpdate(List<UpdateItem> items){
		if(items.size())
		StringBuffer hql = new StringBuffer();
		hql.append(" set ");
		for(UpdateItem item : items){
			
			hql.append(item.getProperty());
			hql.append(item.getQueryOperator());
			hql.append(" and ");
			 
		}
		hql.delete(hql.length() - 4, hql.length());
		return hql.toString();
	}
	
	/**
	 * 根据过滤条件组装查询语句
	 * @param filters
	 * @return
	 */
	public String buildQuery(List<Filter> filters, String tablesInfo){
		StringBuffer hql = new StringBuffer();
		
		if(null == tablesInfo || "".equals(tablesInfo)) {
			hql.append(" where 1=1 ");
		}
		else {
			hql.append(" where " + tablesInfo);
		}
		
		for(Filter filter : filters){
			hql.append(" and ");
			hql.append(filter.getProperty());
			hql.append(filter.getQueryOperator());
			if(!filter.getOperator().equals(Operator.is) && !filter.getOperator().equals(Operator.no)){
				int count = CommonUtil.cal();
				hql.append(":");
				String property = filter.getProperty().replaceAll("\\.", "_");
				hql.append(property);
				hql.append(count);
				if(filter.getOperator().equals(Operator.lk)){
					params.put(property + count, "%" + filter.getValue() + "%");
				}else{
					params.put(property + count, filter.getValue());
				}
			}
		}
		return hql.toString();
	}
	
	/**
	 * 创建排序
	 * @param page
	 * @return
	 */
	public String buildOrder(List<Order> orders,String alias){
		if(orders == null){
			return "";
		}
		StringBuffer hql = new StringBuffer();
		int size = orders.size();
		if(size > 0 ){
			hql.append(" order by ");
			if(size > 1){
				for(int i = 0; i < size - 1; i++){
					if(StringUtils.isNotEmpty(alias)){
						hql.append(alias);
						hql.append(".");
					}
					hql.append(orders.get(i).getProperty());
					
					hql.append(" ");
					hql.append(orders.get(i).getDirection().toString());
					hql.append(",");
				}
			}
			
			if(StringUtils.isNotEmpty(alias)){
				hql.append(alias);
				hql.append(".");
			}
			
			hql.append(orders.get(size -1).getProperty());
			hql.append(" ");
			hql.append(orders.get(size -1).getDirection().toString());
		}
		
		return hql.toString();
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
