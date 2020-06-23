package com.shop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 分页
 * @className Page 
 * @date 2014-9-29 上午09:15:27
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = -2053800594583879853L;
	
	/** 默认页码 */
	private static final int DEFAULT_PAGE_NUMBER = 1;

	/** 默认每页记录数 */
	private static final int DEFAULT_PAGE_SIZE = 10;

	/** 最大每页记录数 */
	private static final int MAX_PAGE_SIZE = 1000;

	/** 内容 */
	private List<T> content = new ArrayList<T>();

	/** 总记录数 */
//	private long total;
	private long count ;
	private Object data;

	/** 页码 */
	private int page = DEFAULT_PAGE_NUMBER;// 当前页
	/** 每页记录数 */
//	private int rows = DEFAULT_PAGE_SIZE;// 每页显示记录数
	
	private String sort;// 排序字段
	private String msg;// 
	private String order;// asc/desc
	private String code;// 0/1
	
	/** 每页记录数 */
	private int limit = DEFAULT_PAGE_SIZE;// 每页显示记录数
	
	private String alias;//查询别名
	
	private List<Filter> filters = new ArrayList<Filter>();//过滤查询
	private List<ORFilter> oRFilters = new ArrayList<ORFilter>();//过滤查询 'OR'
	
	private List<UpdateItem> updateItem = new ArrayList<>();//修改项
	
	private List<Order> orders = new ArrayList<Order>();//排序
	
	/**
	 * 初始化一个新创建的Page对象
	 */
	public Page() {
		this.count = 0L;
	}

	/**
	 * @param content
	 *            内容
	 * @param total
	 *            总记录数
	 */
	public Page(Object data, long total, String msg) {
		this.data = data;
		this.count = total;
		this.msg = msg;
	}

	/**
	 * 默认为 10
	 * <p>	 
	 * @return
	 * int
	 * @see
	 * @since 1.0
	 */
	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public long getCount() {
		return count;
	}

	public void setCount(long count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 获取总页数
	 * 
	 * @return 总页数
	 */
	public int getTotalPages() {
		return (int) Math.ceil((double) getCount() / (double) this.limit);
	}

	

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * 获取内容
	 * 
	 * @return 内容
	 */
	@JsonProperty(value = "rows")
	public List<T> getContent() {
		return content;
	}
	
	/**
	 * 设置集合
	 * @param content
	 */
	public void setContent(List<T> content) {
		this.content = content;
	}

	/**
	 * 获取页码 默认为1
	 * 
	 * @return 页码
	 */
	public int getPage() {
		return page;
	}

	/**
	 * 设置页码
	 * 
	 * @param page
	 *            页码
	 */
	public void setPage(int page) {
		if (page < 1) {
			page = DEFAULT_PAGE_NUMBER;
		}
		this.page = page;
	}
	
	public int getPageSize(){
		return this.limit;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<Filter> getFilters() {
		return filters;
	}
	public List<ORFilter> getORFilters() {
		return this.oRFilters;
	}

	public void setFilters(List<Filter> filters) {
		this.filters = filters;
	}
	
	public List<UpdateItem> getUpdateItems() {
		return updateItem;
	}
	
	public void setUpdateItems(List<UpdateItem> updateItem) {
		this.updateItem = updateItem;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	public void addFilter(Filter filter){
		filters.add(filter);
	}
	
	public void setORFilters(List<ORFilter> oRFilters) {
		this.oRFilters = oRFilters;
	}
	public void addORFilters(ORFilter orf) {
		this.oRFilters.add(orf);
	}
	
	public void addUpdateItem(UpdateItem updateItem){
		this.updateItem.add(updateItem);
	}
	
	/**
	 * 排序
	 * <p>使用案例：<br>
	 * page.addOrder(Order.desc("a.create_date"));	 
	 * @param order
	 * void
	 * @see
	 * @since 1.0
	 */
	public void addOrder(Order order){
		this.orders.add(order);
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public boolean getHasPrevious() {
		
		return page > 1;
	}
	
	public boolean getHasNext () {
		return  page < this.getTotalPages();
	}
	
	
	public boolean getIsFirst () {
		return  page == 1;
	}
	
	public boolean getIsLast () {
		
		return  page == this.getTotalPages();
	}
	
	
	public int getPreviousPageNumber () {
		return page - 1;
	}
	
	public int getNextPageNumber () {
		
		return page + 1;
	}

}