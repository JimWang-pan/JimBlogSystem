package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java1234.model.User;
import com.mysql.cj.xdevapi.SqlDataResult;
/**
 * 用于查询数据库的用户名以及密码的工具类
 * */
public class UserDao {
	/**
	 * 用于查询用户的用户名以及密码 
	 * @param con 接收Connection数据库连接类
	 * @param user 接收一个User实体类
	 * @return 返回一个User实体类
	 * */
	public User userLogin(Connection con, User user) throws SQLException {
		User resultUser = null;
		String sql = "select * from t_user where username=? and password=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, user.getuserName());
		pst.setString(2, user.getPassword());
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			resultUser = new User();
			resultUser.setuserName(rs.getString("username"));
			resultUser.setPassword(rs.getString("password"));
			resultUser.setMail(rs.getString("mail"));
		}
		return resultUser;
	}
	/**
	 * 用于根据邮箱修改用户密码
	 * @param con 接收Connection数据库连接类
	 * @param user 接收一个User实体类
	 * @return 返回一个User实体类
	 * */
	public int motifyPassword(Connection con,User user) throws SQLException{
		String sql = "UPDATE t_user SET PASSWORD=? WHERE mail =? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, user.getPassword());
		pst.setString(2, user.getMail());
		return pst.executeUpdate();
	}
	/**
	 * 查询用户名是否存在
	 * @param con 接收Connection数据库连接类
	 * @param userName 接收一个用户名的字符串
	 * @return 返回boolean类型
	 * */
	public boolean selectUserName(Connection con,String userName) throws SQLException{
		String sql = "select * from t_user where userName=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, userName);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 查询邮箱是否已经创建过账号
	 * @param con 接收Connection数据库连接类
	 * @param mail 接收一个邮箱地址的字符串
	 * @return 返回boolean类型
	 * */
	public boolean selectMail(Connection con,String mail) throws SQLException{
		String sql = "select * from t_user where mail=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, mail);
		ResultSet rs = pst.executeQuery();
		if(rs.next()){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 在数据库中创建新用户
	 * @param con 接收Connection数据库连接类
	 * @param user 接收一个User用户类型
	 * */
	public int insertUser(Connection con,User user) throws SQLException{
		String sql = "insert into t_user(userName,password,mail) values(?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, user.getuserName());
		pst.setString(2, user.getPassword());
		pst.setString(3, user.getMail());
		int result = pst.executeUpdate();
		return result;
	}
}
