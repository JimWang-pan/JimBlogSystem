package com.java1234.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * ���ܹ�����
 * */
public class Md5Util {
	private final String salt = "userName";
	public String md5(String value){
		return new Md5Hash(value, salt).toString();
	}
}
