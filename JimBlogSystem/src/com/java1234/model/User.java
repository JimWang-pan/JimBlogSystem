package com.java1234.model;

/**
 * 存放用户的用户名以及密码的实体类
 * */
public class User {
	private String userName;	//用户名
	private String password;	//用户密码
	private String mail;		//用户邮箱

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public User() {}

	public User(String userName, String password, String mail) {
		super();
		this.userName = userName;
		this.password = password;
		this.mail = mail;
	}

	public String getuserName() {
		return userName;
	}

	public void setuserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	
	
}
