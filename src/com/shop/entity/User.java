package com.shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User implements Serializable {
	@Id
	@Column(name = "id")
	private String id;
	 
	@Column(name = "username")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "create_date")
	private String createDate;
	
	@Column(name = "modify_date")
	private String modifyDate;
	
	@Column(name = "login_count")
	private Integer loginCount;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Integer getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(Integer loginCount) {
		this.loginCount = loginCount;
	}
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String toString(){
		return User.class + "["
				+ " password = " + password + ","
				+ " id = " + id + ","
				+ " username = " + username + ","
				+ " state = " + state + ","
				+ " loginCount = " + loginCount + ","
				+ " createDate = " + createDate + ","
				+ " modifyDate = " + modifyDate + ","
			+"]";
	}
	
}
