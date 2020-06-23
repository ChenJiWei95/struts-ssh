package com.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.shop.Order.Direction;
import com.shop.util.enums.Operator;
import com.sun.istack.internal.logging.Logger;


/**
 * <b>查询通用帮助类 按一定格式处理 </b>
 * <p>
 * 例如：Qu_x#name_eq_s  得出查询语句为： and x.name = :name 其中s是指string
 * S=String L=Long I=Integer D=Double DT=Date ST=Short BD=BigDecimal F=Float 
 * 描述:<br>
 * 
 * <br>
 * 使用案例：<br>
  		QueryHelper queryHelper = new QueryHelper();<br>
		// 别名，详细的说是前端设定为createDate 实际数据库为 create_date <br>
		queryHelper.addCloumnAlias("createDate", "create_date"); <br>
		// 绑定前台参数<br>
		queryHelper.paramBind(request, page);	<br>
		// 排序<br>
		page.addOrder(Order.desc("a.create_date"));<br>
		<br>
		StringBuilder sql = new StringBuilder();<br>
		// 组装sql语句 <br>
		sql<br>
		.append("SELECT DISTINCT a.id, a.`name`, a.update_date, a.create_date, a.status, a.tags, a.content, a.admin_id ")<br>
		.append(queryHelper.buildForm("note a, note_tab_brige b, data c"))<br>
		//.append(queryHelper.buildQuery(page.getFilters(), page.getORFilters()))<br>
		//.append(queryHelper.buildOrder(page.getOrders(), page.getAlias()))<br>
		//.append(queryHelper.buildLimit(pageNum, limit));<br>
		.append(queryHelper.buildAllQuery(page, pageNum, limit));<br>
		// 使用	<br>
		noteServiceImpl.find(sql.toString())
 * @author 威 
 * <br>2019年10月18日 下午11:25:27 
 * @see
 * @since 1.0
 */
@SuppressWarnings("unchecked")
public class QueryHelper {
	
	private static Logger log = Logger.getLogger(QueryHelper.class); // 日志对象
	
	@SuppressWarnings("rawtypes")
	private Page page;//存在对应查询及排序信息
	private StringBuffer hql;
	private Map<String,Object> params = new HashMap<String, Object>();		//查询参数及对应值
	private Map<String,String> cloumnAlias = new HashMap<String, String>();	//前端列 别名 防止前端列名中有下划线影响列名解析  
	private Map<String,String> disableSelect = new HashMap<String, String>();// 例如status 为-1则忽略
	List<Object> batisParams = new ArrayList<Object>();						//查询参数及对应值
	
	/**
	 * 属性名替换<br>
	 * 前端列名 别名 防止前端列名中有下划线影响列名解析 	<br>
	 * 前端设定为createDate 实际数据库为 create_date   <br>
	 * 因此调用addCloumnAlias("createDate", "create_date"); 在生成sql语句时将列名转化为真实列名 <br>
	 * <p>	 
	 * @param alias  前端传过来的属性名
	 * @param cloumn 新的属性名，将会替换原有属性名（需要替换的最终属性名）
	 * void
	 * @see
	 * @since 1.0
	 */
	public void addCloumnAlias(String alias, String cloumn){
		this.cloumnAlias.put(alias, cloumn);
	}
	/**
	 * 该字段所传的值无效，
	 * <p>	 
	 * @param alias 前端传过来的属性名
	 * @param value	无效值 通常为 ’-1‘
	 * void
	 * @see
	 * @since 1.0
	 */
	public void addDisableSelect(String alias, String value){
		this.disableSelect.put(alias, value);
	}
	
	/**
	 * 查询参数绑定
	 * @param request
	 * @param page
	 * @return 
	 */
	
	public void paramBind(HttpServletRequest request, Page page){
		this.page = page;
		if (request.getMethod().equalsIgnoreCase("POST")) {
			Enumeration<String> names = request.getParameterNames();
			while (names.hasMoreElements()) {
				String name = names.nextElement();
				String value = java.net.URLDecoder.decode(request.getParameter(name));
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
		//log.info("接收:"+name);
		if(name.startsWith(SysConstant.QUERY_PRE) && StringUtils.isNotEmpty(value)){
			//log.info("接收:"+name);
			String[] params = name.split(SysConstant.QUERY_SPIT);
			if(params.length == 4){
				Filter filter = new Filter();
				filter.setProperty(params[1].replaceAll("#", "."));
				filter.setOperator(Operator.fromString(params[2]));
				filter.setValue(this.getValue(params[3],value));
				this.page.addFilter(filter);
			} else {
				System.err.println(name+"其他属性");
			}
		} else if(name.startsWith(SysConstant.UPDATE_PRE) && StringUtils.isNotEmpty(value)){
			String[] params = name.split(SysConstant.QUERY_SPIT);
			if(params.length == 3){
				UpdateItem updateItem = new UpdateItem();
				updateItem.setProperty(params[1].replaceAll("#", "."));
				updateItem.setValue(this.getValue(params[2],value));
				this.page.addUpdateItem(updateItem);
			} else {
				System.err.println(name+"其他属性");
			}
		} else {
			System.err.println(name+"其他属性");
		}
	} 
	
	/**
	 * 获取Filters长度  page.getFilters().size() 
	 * <p>	 
	 * @return
	 * int
	 * @see
	 * @since 1.0
	 */
	public int sizeOfFilter(){
		return this.page.getFilters().size();
	}
	/**
	 * update 语句中获取值
	 * <p>	 
	 * @param valueType
	 * @param value
	 * @return
	 * Object
	 * @see
	 * @since 1.0
	 */
	private Object getValue2(String valueType,String value) {
		value = value.trim();
		try {
			if(StringUtils.endsWithIgnoreCase(valueType, "s")){
				return "'"+value+"'";
			}
			return value;
		} catch (Exception e) {
			log.info("类型转换失败",e);
		}
		
		return null;
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
			log.info("类型转换失败",e);
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
		hql.append(this.buildUpdate(page.getUpdateItems()));
		hql.append(this.buildQuery(page.getFilters()));
		hql.append(this.buildOrder(page.getOrders(),page.getAlias()));
		return hql.toString();
	}
	
	public String buildAllQuery(Page page, int pageNum, int limit){
		hql = new StringBuffer();
		hql.append(this.buildUpdate(page.getUpdateItems()));
		hql.append(this.buildQuery(page.getFilters(), page.getORFilters()));
		hql.append(this.buildOrder(page.getOrders(),page.getAlias()));
		hql.append(this.buildLimit(pageNum, limit));
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
			if(isDisableSelect(filter))
				continue;
			hql.append(" and ");
			hql.append(coverProperty(filter));
			hql.append(filter.getQueryOperator());
			if(!filter.getOperator().equals(Operator.is) && !filter.getOperator().equals(Operator.no)){
				mybatis(hql, filter);
			}
		}
		
		log.info(hql.toString());
		return hql.toString();
	}
	
	public String buildQuery(List<Filter> filters, List<ORFilter> orFilters){
		StringBuffer hql = new StringBuffer();
		hql.append(" where 1=1 ");
		for(Filter filter : filters){
			if(isDisableSelect(filter))
				continue;
			hql.append(" and ");
			hql.append(coverProperty(filter));
			hql.append(filter.getQueryOperator());
			if(!filter.getOperator().equals(Operator.is) && !filter.getOperator().equals(Operator.no)){
				mybatis(hql, filter);
			}
		}
		if(orFilters.size() > 0){
			ORFilter orf = orFilters.get(0);
			hql.append(" and (");
			hql.append(coverProperty(orf));
			hql.append(orf.getQueryOperator());
			if(!orf.getOperator().equals(Operator.is) && !orf.getOperator().equals(Operator.no)){
				mybatis(hql, orf);
			}
			for(int i = 1; i < orFilters.size(); i++){
				orf = orFilters.get(i);
				hql.append(" OR ");
				hql.append(coverProperty(orf));
				hql.append(orf.getQueryOperator());
				if(!orf.getOperator().equals(Operator.is) && !orf.getOperator().equals(Operator.no)){
					mybatis(hql, orf);
				}
			}
			hql.append(" ) ");
		}
		 
		log.info(hql.toString());
		return hql.toString();
	}
	
	/**
	 * 当某字段传过来的值时-1时无效， 默认有status
	 * <p>	 
	 * @param filter
	 * @return
	 * boolean
	 * @see
	 * @since 1.0
	 */
	public boolean isDisableSelect(Filter filter) {
		disableSelect.put("status", "-1");
		for(Entry<String, String> item : disableSelect.entrySet())
			if(filter.getProperty().indexOf(item.getKey()) != -1 
					&& item.getValue().equals(filter.getValue())){
				return true;
			}
		return false;
	}
	/**
	 * 根据cloumnAlias 替换字段别名
	 * <p>	 
	 * @param filter
	 * void
	 * @see 
	 * @since 1.0
	 */
	public String coverProperty(Filter filter){
		String currProperty;
		String name = filter.getProperty();
		int index = name.indexOf(".");
		if(index != -1 && cloumnAlias.get(name.substring(index+1)) != null) // 存在表别名 
			currProperty = name.substring(0, index+1) + cloumnAlias.get(name.substring(index+1));
		else 
			currProperty = cloumnAlias.get(name); 
		log.info(currProperty + "  " + filter.getProperty());
		return currProperty != null ? currProperty : filter.getProperty();
	}
	public String coverProperty(ORFilter filter){
		String currProperty;
		String name = filter.getProperty();
		int index = name.indexOf(".");
		if(index != -1 && cloumnAlias.get(name.substring(index+1)) != null) // 存在表别名 
			currProperty = name.substring(0, index+1) + cloumnAlias.get(name.substring(index+1));
		else 
			currProperty = cloumnAlias.get(name); 
		log.info(currProperty + "  " + filter.getProperty());
		return currProperty != null ? currProperty : filter.getProperty();
	}

	/**
	 * 运用到count查询数量 中的条件拼接，暂时没用到，同时count也经过整改count(tables,eq) 估计日后可以删除
	 * <p>	 
	 * @return
	 * String
	 * @see
	 * @since 1.0
	 */
	public String getEq() {
		// 拼接查询数量的条件
		StringBuilder eq = new StringBuilder();
		List<Filter> fs = page.getFilters();
		for(Filter item : fs) {
			if(this.isDisableSelect(item)) continue;
			String prefix = "'",subfix = "'";
			if(item.getQueryOperator().indexOf("like") >= 0){
				prefix = "'%";
				subfix = "%'";
			}
			;
			eq.append(this.coverProperty(item))
			.append(item.getQueryOperator())
			.append(prefix+item.getValue()+subfix);
			eq.append(" AND ");
		}
		if(fs.size()>0)eq.delete(eq.length()-4, eq.length());// 删除多余
//				System.out.println("count:"+eq.toString());
		
		return eq.toString();
	}
	
	protected void Hibernate(StringBuffer hql, Filter filter) {
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
	
	protected void mybatis(StringBuffer hql, Filter filter) {
		if(filter.getOperator().equals(Operator.lk)){
			hql.append("'%" + filter.getValue() + "%'");
		}else if(filter.getOperator().equals(Operator.slk)){
			hql.append("'" + filter.getValue() + "%'");
		}else {
			hql.append("String".equals(filter.getValue().getClass().getSimpleName()) 
					&& !Filter.IGNORE_TYPE.equals(filter.getIgnoreType())
					? "'"+filter.getValue()+"'" 
					: filter.getValue());
		}
	}
	protected void mybatis(StringBuffer hql, ORFilter filter) {
		if(filter.getOperator().equals(Operator.lk)){
			hql.append("'%" + filter.getValue() + "%'");
		}else if(filter.getOperator().equals(Operator.slk)){
			hql.append("'" + filter.getValue() + "%'");
		}else {
			hql.append("String".equals(filter.getValue().getClass().getSimpleName()) 
					&& !Filter.IGNORE_TYPE.equals(filter.getIgnoreType())
					? "'"+filter.getValue()+"'" 
					: filter.getValue());
		}
	}
	
	/*public void Hibernate(StringBuffer hql, String property, int count){
		
	}*/
	/**
	 * 例如： SET name='cjw' AND age='12'
	 * <p>	 
	 * @param items
	 * @return
	 * String
	 * @see
	 * @since 1.0
	 */
	public String buildUpdate(List<UpdateItem> items){
		if(items.size() == 0) return "";
		StringBuffer hql = new StringBuffer();
		hql.append(" set ");
		for(UpdateItem item : items){
			
			String trueCloumn = this.cloumnAlias.get(item.getProperty());
			hql.append(trueCloumn == null ? item.getProperty() : trueCloumn);
			hql.append(item.getQueryOperator());
			hql.append("String".equals(item.getValue().getClass().getSimpleName()) ? "'"+item.getValue()+"'" : item.getValue());
			hql.append(", ");
			 
		}
		hql.delete(hql.length() - 2, hql.length());
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
			String trueCloumn = this.cloumnAlias.get(filter.getProperty());
			log.info(filter.getProperty() + " " + trueCloumn);
			hql.append(trueCloumn == null ? filter.getProperty() : trueCloumn);
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
	 * 返回 FROM ...
	 * <p>	 
	 * @param tables
	 * 			传入 admin a, admin_infor b, role c 
	 * @return
	 * String
	 * @see
	 * @since 1.0
	 */
	public String buildForm(String tables){
		return "FROM " + tables + " ";
	}
	
	/**
	 * 返回 SELECT ...
	 * <p>	 
	 * @param cloumns
	 * 			不传则返回 	SELECT *
	 * 			传name,age则返回	SELECT `name`, `age`
	 * @return
	 * String
	 * @see
	 * @since 1.0
	 */
	public String buildSelect(String... cloumns){
		if(cloumns.length > 0){
			StringBuffer sb = new StringBuffer("SELECT ");
			sb.append("`"+cloumns[0]+"` ");
			for(int i = 1; i < cloumns.length; i++){
				sb.append(",`"+cloumns[i]+"` ");
			}
			return sb.toString();
		}
		else 
			return "SELECT * ";
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

	/**
	 * 分页查询相关语句
	 * <p>	 
	 * @param pageNum
	 * @param limit
	 * @return
	 * String
	 * @see
	 * @since 1.0
	 */
	public String buildLimit(int pageNum, int limit){
		return " LIMIT " + (pageNum-1)*limit + "," + limit;
	}
	
	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

}
