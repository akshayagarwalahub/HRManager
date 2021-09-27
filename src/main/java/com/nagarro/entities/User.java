package com.nagarro.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@NotBlank(message="User Id cannot be blank !!")
	@Size(min=5 , max=50 , message = "User Id must be between 5-50 Characters!!")
	private String username;
	
	@NotBlank(message="Password cannot be blank !!")
	@Size(min=5 , max=50 , message = "Password must be between 5-50 Characters!!")
	private String password;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(int id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
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
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
	
	

}
