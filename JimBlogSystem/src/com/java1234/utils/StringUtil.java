package com.java1234.utils;

/**
 * �����ַ���������
 * */
public class StringUtil {
	/**
	 * ��int����תΪ�ַ���
	 * @param intArray ����һ��int����
	 * */
	public String arrayToString(int[] intArray){
		StringBuffer sb = new StringBuffer();
		for(int i:intArray){
			sb.append(i);
		}
		return sb.toString();
	}
	/**
	 * �ж��ַ����Ƿ�Ϊ���ַ���
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
