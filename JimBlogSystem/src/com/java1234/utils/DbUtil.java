package com.java1234.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * ���ڻ�ȡ���ݿ��������ݿ�Ĺ�����
 * */
public class DbUtil {
	private Connection con = null;
	/**
	 * ��ȡ���ݿ�����
	 * @return ����һ�����ݿ�����
	 * */
	public Connection getCon() throws SQLException{
		//�������ڶ�ȡ���ݿ������ļ����ֽ���
		InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("database.properties");
		Properties pro = new Properties();
		try {
			//��ȡ�����ļ��еļ�ֵ��
			pro.load(in);
			String driverClass = pro.getProperty("driverClass");
			String url = pro.getProperty("url");
			String username = pro.getProperty("username");
			String password = pro.getProperty("password");
			//ע������
			try {
				Class.forName(driverClass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, username, password);
		} catch (IOException e) {
			System.out.println("���ݿ�����ʧ��");
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * �ر���������
	 * @param con ����java.sql.Connection����
	 * */
	public void conClose(Connection con) {
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("���ݿ�ر�ʧ��");
				e.printStackTrace();
			}
		}
	}
}
