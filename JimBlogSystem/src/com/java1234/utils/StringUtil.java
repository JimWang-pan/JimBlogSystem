package com.java1234.utils;

/**
 * 操作字符串工具类
 * */
public class StringUtil {
	/**
	 * 将int数组转为字符串
	 * @param intArray 接收一个int数组
	 * */
	public String arrayToString(int[] intArray){
		StringBuffer sb = new StringBuffer();
		for(int i:intArray){
			sb.append(i);
		}
		return sb.toString();
	}
	/**
	 * 判断字符串是否为空字符串
	 * */
	 public boolean isEmpty(String str){
		 return str.equals("");
	 }
	
	public static void main(String[] args) {
		StringUtil stringUtil = new StringUtil();
		int intArray[] = {1,2,3,4,5};
		System.out.println(stringUtil.arrayToString(intArray));
	}
}
