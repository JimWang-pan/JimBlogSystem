package com.java1234.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * 用于获取数据库连接数据库的工具类
 * */
public class DbUtil {
	private Connection con = null;
	/**
	 * 获取数据库连接
	 * @return 返回一个数据库连接
	 * */
	public Connection getCon() throws SQLException{
		//创建用于读取数据库配置文件的字节流
		InputStream in = DbUtil.class.getClassLoader().getResourceAsStream("database.properties");
		Properties pro = new Properties();
		try {
			//获取配置文件中的键值对
			pro.load(in);
			String driverClass = pro.getProperty("driverClass");
			String url = pro.getProperty("url");
			String username = pro.getProperty("username");
			String password = pro.getProperty("password");
			//注册驱动
			try {
				Class.forName(driverClass);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			con = DriverManager.getConnection(url, username, password);
		} catch (IOException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 关闭数据连接
	 * @param con 接收java.sql.Connection对象
	 * */
	public void conClose(Connection con) {
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("数据库关闭失败");
				e.printStackTrace();
			}
		}
	}
}
