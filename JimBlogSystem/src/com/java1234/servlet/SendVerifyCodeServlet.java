	package com.java1234.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java1234.utils.StringUtil;

/**
 * ������֤��ҵ����
 * */
public class SendVerifyCodeServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//���������ʼ���ҵ����
	SendMailServlet sendMailServlet = new SendMailServlet();
	//���������ַ����Ĺ�����
	private StringUtil stringUtil = new StringUtil();
	//�����洢��֤��ľ�̬����
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
		//��ȡ�����ַ
		String mail = request.getParameter("mail");
		//��ȡ���ĸ�ҳ�������ȡ��֤��
		String path = request.getSession().getAttribute("path").toString();
		//�ж������Ƿ�Ϊ��
		request.setAttribute("userName", userName);
		request.setAttribute("mail", mail);
		createVerifyCode();
		sendMailServlet.sendMailMessage(mail, "<h3>���������֤��</h3><p>"+verify_code+"</p>");
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
