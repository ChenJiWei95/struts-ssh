package com.shop.entity;


import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.engine.jdbc.SerializableBlobProxy;


@Entity
@Table(name = "cart_list")
public class CartList implements Serializable{
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "name")
	private String name;
	@Column(name = "url")
	private String url;
	@Column(name = "count")
	private Integer count;
	@Column(name = "g_id")
	private String goodsId;
	@Column(name = "u_id")
	private String userId;
	@Column(name = "colour")
	private String colour;
	@Column(name = "size")
	private String size;
	@Column(name = "price")
	private BigDecimal price;
	@Column(name = "discount")
	private BigDecimal discount;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public BigDecimal getPrice() {
		return price.setScale(2,BigDecimal.ROUND_HALF_DOWN);
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
	
	public String toString() {
		return "[id = "+id+"]";
	}
	 
}
