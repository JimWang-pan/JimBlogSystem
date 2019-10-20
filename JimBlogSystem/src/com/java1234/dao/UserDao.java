package com.java1234.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java1234.model.User;
import com.mysql.cj.xdevapi.SqlDataResult;
/**
 * ���ڲ�ѯ���ݿ���û����Լ�����Ĺ�����
 * */
public class UserDao {
	/**
	 * ���ڲ�ѯ�û����û����Լ����� 
	 * @param con ����Connection���ݿ�������
	 * @param user ����һ��Userʵ����
	 * @return ����һ��Userʵ����
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
	 * ���ڸ��������޸��û�����
	 * @param con ����Connection���ݿ�������
	 * @param user ����һ��Userʵ����
	 * @return ����һ��Userʵ����
	 * */
	public int motifyPassword(Connection con,User user) throws SQLException{
		String sql = "UPDATE t_user SET PASSWORD=? WHERE mail =? ";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, user.getPassword());
		pst.setString(2, user.getMail());
		return pst.executeUpdate();
	}
	/**
	 * ��ѯ�û����Ƿ����
	 * @param con ����Connection���ݿ�������
	 * @param userName ����һ���û������ַ���
	 * @return ����boolean����
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
	 * ��ѯ�����Ƿ��Ѿ��������˺�
	 * @param con ����Connection���ݿ�������
	 * @param mail ����һ�������ַ���ַ���
	 * @return ����boolean����
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
	 * �����ݿ��д������û�
	 * @param con ����Connection���ݿ�������
	 * @param user ����һ��User�û�����
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
