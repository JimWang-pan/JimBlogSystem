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
 * �û���֤�û���¼��ҵ����
 */
public class LoginServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// ����һ��DbUtil���������
	private DbUtil dbUtil = new DbUtil();
	// ����һ��UserDao����
	private UserDao userDao = new UserDao();
	//����Md5Util���ܹ��������
	private Md5Util md5Util = new Md5Util();
	//�����ַ�������������
	private StringUtil strUtil = new StringUtil();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��ȡ�û�������û����Լ����룬�������װ��Userʵ������
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		//�ж��û����Ƿ�Ϊ��
//		if(strUtil.isEmpty(userName)||strUtil.isEmpty(password)){
//			request.setAttribute("userName", userName);
//			request.setAttribute("password", password);
//			request.setAttribute("error", "�û���������Ϊ��");
//			request.getRequestDispatcher("login.jsp").forward(request, response);
//			return;
//		}
		User user = new User(userName, md5Util.md5(password));
		// ������ͨ��DbUtil�������ȡ���ݿ�������
		Connection con = null;
		try {
			con = dbUtil.getCon();
			//����UserDao����userLogin�����鿴�û���Ϣ,����currentUser�������շ��صĽ����
			User currentUser = userDao.userLogin(con, user);
			//�жϽ�����Ƿ�Ϊ��,�ռ���¼����֮���¼�ɹ�
			if (currentUser == null) {
				request.setAttribute("userName", userName);
				request.setAttribute("password", password);
				request.setAttribute("error", "�û������������");
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
			//�ر����ݿ�������Դ
			if(con!=null){
				dbUtil.conClose(con);
			}
		}
	}

}
