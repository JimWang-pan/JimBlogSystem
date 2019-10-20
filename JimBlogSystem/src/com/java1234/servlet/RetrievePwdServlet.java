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
 * �һ�����ҵ����
 * */
public class RetrievePwdServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ����һ��DbUtil���������
	private DbUtil dbUtil = new DbUtil();
	// ����һ��UserDao����
	private UserDao userDao = new UserDao();
	//�������ܹ�����
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
				//�ж���֤���Ƿ���ȷ
				if(verifyCode.equals(SendVerifyCodeServlet.getVerify_code())){
					User user = new User("", md5Util.md5(password),mail);
					userDao.motifyPassword(con, user);
					response.sendRedirect("index.jsp");
				}else{
					request.setAttribute("mail", mail);
					request.setAttribute("verifyCode", verifyCode);
					request.setAttribute("password", password);
					request.setAttribute("confirmPassword", confirmPassword);
					request.setAttribute("error", "��֤�����");
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
