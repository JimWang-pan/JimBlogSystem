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
 * �����ʼ�ҵ����
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
		//�����ֽ���������ȡmail�����ļ�
		InputStream in = SendMailServlet.class.getClassLoader().getResourceAsStream("mail.properties");
		try {
			//��mail�����ļ���Ϣ
			props.load(in);
			//���÷����ʼ�ʱʹ�õĻ���
			Session session = Session.getInstance(props);
			//�ʼ����͵ĵ���ģʽ
			session.setDebug(true);
			//�����ʼ�
			MimeMessage message = new MimeMessage(session);
			try {
				//���÷����˵ĵ�ַ
				message.setFrom(new InternetAddress(fromAddress));
				//�����ռ��˵�ַ
				message.setRecipients(Message.RecipientType.TO, toAddress);
				//�����ʼ�����
				String subject = MimeUtility.encodeWord("Jim������֤��Ϣ","utf-8","Q");
				message.setSubject(subject);
				//�����ʼ������Լ��ʼ�����
				message.setContent(mailMessage, "text/html;charset=utf-8");
				//���ʼ��������
				message.saveChanges();
				//�����ʼ�
				Transport transport = session.getTransport();
				//���ӵ�����ͻ���,auth_key������ͻ�����Ȩ��
				transport.connect(fromAddress,auth_key);
				//�����ʼ�
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
