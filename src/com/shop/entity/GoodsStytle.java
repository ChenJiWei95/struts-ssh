package com.shop.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "goodsStytle")
public class GoodsStytle {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "goods_id")
	private String goodsId;
	@Column(name = "color")
	private String color;
	@Column(name = "color_sign")
	private String colorSign;
	@Column(name = "url")
	private String url;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getColorSign() {
		return colorSign;
	}
	public void setColorSign(String colorSign) {
		this.colorSign = colorSign;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
