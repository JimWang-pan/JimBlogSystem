package com.java1234.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.java1234.dao.UserDao;
import com.java1234.model.User;
import com.java1234.utils.DbUtil;
import com.java1234.utils.Md5Util;

/**
 * 用户注册业务类
 * */
public class RegisterServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private User user = new User();
	private UserDao userDao = new UserDao();
	private DbUtil dbUtil = new DbUtil();
	private Md5Util md5Util = new Md5Util();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String mail = request.getParameter("mail");
		String verifyCode = request.getParameter("verifyCode");
		String password = request.getParameter("password");
		Connection con = null;
		try {
			con = dbUtil.getCon();
			//判断用户名是否已经存在
			if(userDao.selectUserName(con, userName)){
				request.setAttribute("userName", userName);
				request.setAttribute("mail", mail);
				request.setAttribute("passwprd", password);
				request.setAttribute("confirmPassword", password);
				request.setAttribute("error", "用户名不可用");
				request.getRequestDispatcher("register.jsp").forward(request, response);
				return;
			}else{
				//判断邮箱是否已经注册过账号
				if(userDao.selectMail(con, mail)){
					request.setAttribute("userName", userName);
					request.setAttribute("mail", mail);
					request.setAttribute("passwprd", password);
					request.setAttribute("confirmPassword", password);
					request.setAttribute("error", "一个邮箱只能注册一个账号");
					request.getRequestDispatcher("register.jsp").forward(request, response);
					return;
				}else{
					if(verifyCode.equals(SendVerifyCodeServlet.getVerify_code())){
						User user = new User(userName, md5Util.md5(password), mail);
						if(userDao.insertUser(con, user)!=0){
							response.sendRedirect("index.jsp");
						}
					}else{
						request.setAttribute("userName", userName);
						request.setAttribute("mail", mail);
						request.setAttribute("passwprd", password);
						request.setAttribute("confirmPassword", password);
						request.setAttribute("error", "验证码错误");
						request.getRequestDispatcher("register.jsp").forward(request, response);
						return;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(con!=null){
				dbUtil.conClose(con);
			}
		}
	}
	
}
