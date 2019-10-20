	package com.java1234.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.java1234.dao.UserDao;
import com.java1234.model.User;
import com.java1234.utils.DbUtil;
import com.java1234.utils.Md5Util;

/**
 * 找回密码业务类
 * */
public class RetrievePwdServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 创建一个DbUtil工具类对象
	private DbUtil dbUtil = new DbUtil();
	// 创建一个UserDao对象
	private UserDao userDao = new UserDao();
	//创建加密工具类
	private Md5Util md5Util = new Md5Util();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mail = request.getParameter("mail");
		String verifyCode = request.getParameter("verifyCode");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		try {
			Connection con = dbUtil.getCon();
			if(userDao.selectMail(con, mail)){
				//判断验证码是否正确
				if(verifyCode.equals(SendVerifyCodeServlet.getVerify_code())){
					User user = new User("", md5Util.md5(password),mail);
					userDao.motifyPassword(con, user);
					response.sendRedirect("index.jsp");
				}else{
					request.setAttribute("mail", mail);
					request.setAttribute("verifyCode", verifyCode);
					request.setAttribute("password", password);
					request.setAttribute("confirmPassword", confirmPassword);
					request.setAttribute("error", "验证码错误");
					request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
					return;
				}
			}else{
				request.setAttribute("mail", mail);
				request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
				return;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
