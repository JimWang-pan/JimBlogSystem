package com.java1234.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;

import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;

/**
 * 发送邮件业务类
 */
public class SendMailServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Properties props = new Properties();

	public void sendMailMessage(String toAddress,String mailMessage) {
		String fromAddress = "1941008440@qq.com";
		String auth_key = "abkomgmiglfdcjij";
		//创建字节输入流读取mail配置文件
		InputStream in = SendMailServlet.class.getClassLoader().getResourceAsStream("mail.properties");
		try {
			//将mail配置文件信息
			props.load(in);
			//配置发送邮件时使用的环境
			Session session = Session.getInstance(props);
			//邮件发送的调试模式
			session.setDebug(true);
			//创建邮件
			MimeMessage message = new MimeMessage(session);
			try {
				//设置发件人的地址
				message.setFrom(new InternetAddress(fromAddress));
				//设置收件人地址
				message.setRecipients(Message.RecipientType.TO, toAddress);
				//设置邮件主题
				String subject = MimeUtility.encodeWord("Jim博客验证信息","utf-8","Q");
				message.setSubject(subject);
				//设置邮件内容以及邮件类型
				message.setContent(mailMessage, "text/html;charset=utf-8");
				//让邮件保存更改
				message.saveChanges();
				//发送邮件
				Transport transport = session.getTransport();
				//连接到邮箱客户端,auth_key是邮箱客户端授权码
				transport.connect(fromAddress,auth_key);
				//发送邮件
				transport.sendMessage(message,message.getAllRecipients());
			} catch (AddressException e) {
				e.printStackTrace();
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SendMailServlet sendMailServlet = new SendMailServlet();
		sendMailServlet.sendMailMessage("13104998292@163.com","1");
	}
}
