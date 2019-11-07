package com.shop.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class UserTest implements Serializable {
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
		return UserTest.class + "["
				+ "id = " + id
				+ ", username = " + username
				+ "]";
	}

}
