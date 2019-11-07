package com.shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_address")
public class UserAddress implements Serializable {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "province")
	private String province;
	@Column(name = "city")
	private String city;
	@Column(name = "area")
	private String area;
	@Column(name = "street")
	private String street;
	@Column(name = "user_infor_id")
	private String userInforId;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getUserInforId() {
		return userInforId;
	}
	public void setUserInforId(String userInforId) {
		this.userInforId = userInforId;
	}
}
