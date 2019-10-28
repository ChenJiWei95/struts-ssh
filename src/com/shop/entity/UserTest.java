package com.shop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "username")
	private String username; 
	private String password;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String toString(){
		return User.class + "["
				+ "id = " + id
				+ ", username = " + username
				+ "]";
	}

}
