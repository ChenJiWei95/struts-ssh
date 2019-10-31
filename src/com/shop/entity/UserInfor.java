package com.shop.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_infor")
public class UserInfor {
	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "msg")
	private BigDecimal msg;
	
	@Column(name = "u_id")
	private String userId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getMsg() {
		return msg;
	}

	public void setMsg(BigDecimal msg) {
		this.msg = msg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	} 
	
	public String toString(){
		return UserTest.class + "["
				+ "id = " + id
				+ ", name = " + name
				+ ", email = " + email
				+ ", phone = " + phone
				+ ", msg = " + msg
				+ ", userid = " + userId
				+ "]";
	}
 
}
