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
	@Column(name = "cn_zh")
	private String cnZh;
	@Column(name = "en_us")
	private String enUs;


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
	public String getCnZh() {
		return cnZh;
	}
	public void setCnZh(String cnZh) {
		this.cnZh = cnZh;
	}
	public String getEnUs() {
		return enUs;
	}
	public void setEnUs(String enUs) {
		this.enUs = enUs;
	}
}
