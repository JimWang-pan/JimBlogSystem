	package com.java1234.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java1234.utils.StringUtil;

/**
 * 发送验证码业务类
 * */
public class SendVerifyCodeServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//创建发送邮件的业务类
	SendMailServlet sendMailServlet = new SendMailServlet();
	//创建操作字符串的工具类
	private StringUtil stringUtil = new StringUtil();
	//创建存储验证码的静态变量
	private static String verify_code;
	
	
	public static String getVerify_code() {
		return verify_code;
	}
	public static void setVerify_code(String verify_code) {
		SendVerifyCodeServlet.verify_code = verify_code;
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userName = request.getParameter("userName");
		//获取邮箱地址
		String mail = request.getParameter("mail");
		//获取是哪个页面请求获取验证码
		String path = request.getSession().getAttribute("path").toString();
		//判断邮箱是否为空
		request.setAttribute("userName", userName);
		request.setAttribute("mail", mail);
		createVerifyCode();
		sendMailServlet.sendMailMessage(mail, "<h3>这是你的验证码</h3><p>"+verify_code+"</p>");
		request.getRequestDispatcher(path).forward(request, response);
	}
	private void createVerifyCode() {
		Random random = new Random();
		int verifyCodes[] = new int[5];
		for(int i=0;i<5;i++){
			verifyCodes[i] = random.nextInt(9);
		}
		setVerify_code(stringUtil.arrayToString(verifyCodes));
	}
}
