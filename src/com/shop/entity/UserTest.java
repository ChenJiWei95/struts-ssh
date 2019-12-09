package com.shop.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

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
	
	@Column(name = "text")
	private byte[] text; 

	
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
	public byte[] getText() {
		return text;
	}
	public void setText(byte[] text) {
		this.text = text;
	}
	
	public String toString(){
		try {
			return UserTest.class + "["
					+ "id = " + id
					+ ", username = " + username
					+ ", text = " + new String(text, "UTF-8")
					+ "]";
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return UserTest.class + "["
		+ "id = " + id
		+ ", username = " + username
		+ "]";
	}

}
