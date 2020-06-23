package com.shop.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "language")
public class Language {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "code")
	private String code;
	@Column(name = "zh_CN")
	private String zhcn;
	@Column(name = "en_US")
	private String enus;


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getZhcn() {
		return zhcn;
	}
	public void setZhcn(String zhcn) {
		this.zhcn = zhcn;
	}
	public String getEnus() {
		return enus;
	}
	public void setEnus(String enus) {
		this.enus = enus;
	}
	
}
