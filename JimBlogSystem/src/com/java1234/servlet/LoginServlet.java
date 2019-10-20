package com.java1234.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.crypto.hash.Md5Hash;

import com.java1234.dao.UserDao;
import com.java1234.model.User;
import com.java1234.utils.DbUtil;
import com.java1234.utils.Md5Util;
import com.java1234.utils.StringUtil;

/**
 * 用户验证用户登录的业务类
 */
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 创建一个DbUtil工具类对象
	private DbUtil dbUtil = new DbUtil();
	// 创建一个UserDao对象
	private UserDao userDao = new UserDao();
	//创建Md5Util加密工具类对象
	private Md5Util md5Util = new Md5Util();
	//创建字符串操作工具类
	private StringUtil strUtil = new StringUtil();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 获取用户输入的用户名以及密码，并将其封装到User实体类中
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		//判断用户名是否为空
//		if(strUtil.isEmpty(userName)||strUtil.isEmpty(password)){
//			request.setAttribute("userName", userName);
//			request.setAttribute("password", password);
//			request.setAttribute("error", "用户名或密码为空");
//			request.getRequestDispatcher("login.jsp").forward(request, response);
//			return;
//		}
		User user = new User(userName, md5Util.md5(password));
		// 创建并通过DbUtil工具类获取数据库连接类
		Connection con = null;
		try {
			con = dbUtil.getCon();
			//调用UserDao类中userLogin方法查看用户信息,并用currentUser变量接收返回的结果集
			User currentUser = userDao.userLogin(con, user);
			//判断结果集是否为空,空即登录，反之则登录成功
			if (currentUser == null) {
				request.setAttribute("userName", userName);
				request.setAttribute("password", password);
				request.setAttribute("error", "用户名或密码错误");
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}else{
				HttpSession session = request.getSession();
				session.setAttribute("currentUser", currentUser);
				if(request.getParameter("remember").equals("remember-me")){
					Cookie cookie = new Cookie("userNamePassword", userName+"-"+password);
					cookie.setMaxAge(1*60*60*24*7);
					response.addCookie(cookie);
				}
				response.sendRedirect("main.jsp");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//关闭数据库连接资源
			if(con!=null){
				dbUtil.conClose(con);
			}
		}
	}

}
