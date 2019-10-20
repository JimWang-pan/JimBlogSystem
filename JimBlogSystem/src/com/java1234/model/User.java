package com.java1234.model;

/**
 * ����û����û����Լ������ʵ����
 * */
public class User {
	private String userName;	//�û���
	private String password;	//�û�����
	private String mail;		//�û�����

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
