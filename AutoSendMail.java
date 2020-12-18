///*
// * ============================================================================
// * 系统名: GTE新销售系统
// *
// * 程序名: autoSendMail
// * ----------------------------------------------------------------------------
// * 作成日(YYYY-MM-DD) 作成者 说明
// * ----------------------------------------------------------------------------
// * 2009-07-22 ds 初期建成(v1.0)
// * ----------------------------------------------------------------------------
// * (C) Copyright TC Systems CORPORATION 2009-2010 All Rights Reserved.
// * ============================================================================
// */
//package com.example.demo.utils;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.activation.DataHandler;
//import javax.activation.FileDataSource;
//import javax.mail.BodyPart;
//import javax.mail.Message;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.*;
//import java.util.List;
//import java.util.Properties;
//
//@Component
//public class AutoSendMail {
//
//	// MimeMultipart初期化
//	private MimeMultipart mp;
//
//	// BodyPart初期化
//	private BodyPart bp;
//
//	@Value("${email.autoSendMailServerAddress}")
//	private String autoSendMailServerAddress;
//
//	@Value("${email.autoSendMailServerPsw}")
//	private String autoSendMailServerPsw;
//
//	@Value("${email.mailserver}")
//	private String mailserver;
//
//	@Value("${email.corn1}")
//	private String corn1;
//
//	/**
//	 * 自动发送邮件
//	 *
//	 * @param too
//	 *            收件人
//	 * @param title
//	 *            标题
//	 * @param content
//	 *            邮件内容
//	 * @param fileAddress
//	 *            附件地址
//	 * @return
//	 * @author ds
//	 */
//	public String mailsend(List too, List cc,String title, String content,
//			String fileAddress) {
//
//		try {
//			//发件人地址
//			String MailFrom = autoSendMailServerAddress;
//
//			//发件人密码
//			String MailPass = autoSendMailServerPsw;
//
//			//邮件服务器地址
//			Properties props = new Properties();
//			props.put("mail.smtp.host", mailserver);// 设置邮件服务器地址
//			props.put("mail.smtp.auth", "true");// 设置是否需要验证邮件密码
//			Session sess = Session.getInstance(props);
//			sess.setDebug(true);
//			MimeMessage message = new MimeMessage(sess);
//			InternetAddress from_mail = new InternetAddress(MailFrom);
//			message.setFrom(from_mail);
//			InternetAddress[] to_mail = new InternetAddress[too.size()];
//
//			for (int i = 0; i < too.size(); i++) { // 设置接收邮件人的地址
//				to_mail[i] = new InternetAddress(String.valueOf(too.get(i)));
//			}
//			InternetAddress[] cc_mail = new InternetAddress[cc.size()];
//			for (int i = 0; i < cc.size(); i++) { // 设置接收邮件人的地址
//				cc_mail[i] = new InternetAddress(String.valueOf(cc.get(i)));
//			}
//
//			message.addRecipients(Message.RecipientType.TO, to_mail);
//			message.addRecipients(Message.RecipientType.CC, cc_mail);
//			// sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//			// //防止邮件标题乱码
//			// message.setSubject("=?GB2312?B?"+enc.encode(title.getBytes())+"?=");
//
//			message.setSubject(title); // 设置邮件标题
//			mp = new MimeMultipart();
//
//			// 判断是否带有附件传送
//			// 当没有附件传送时
//			if (null != fileAddress && !"".equals(fileAddress.trim())) {
//				this.addFileAffix(fileAddress);// 设置附件
//			}
//			this.setBody(content);// 用此函数来设置邮件内容
//			message.setContent(mp);// 传送邮件的附件和内容
//			message.saveChanges();
//			Transport transport = sess.getTransport("smtp");
//			transport.connect(mailserver, MailFrom, MailPass);
//			transport.sendMessage(message, message.getAllRecipients());
//			transport.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "failed";
//		}
//		return "success";
//	}
//
//	/**
//	 * 添加附件
//	 *
//	 * @param filename
//	 * @return
//	 */
//	public boolean addFileAffix(String filename) {
//		//System.out.println("增加附件...");
//		if (filename.equals("") || filename == null) {
//			return false;
//		}
//		String file[];
//		file = filename.split(";");
//		//System.out.println("你有 " + file.length + " 个附件!");
//		try {
//			for (int i = 0; i < file.length; i++) {
//				bp = new MimeBodyPart();
//				FileDataSource fileds = new FileDataSource(file[i]);
//				bp.setDataHandler(new DataHandler(fileds));
//			    //sun.misc.BASE64Encoder enc = new sun.misc.BASE64Encoder();
//				//防止附件标题乱码
//			    bp.setFileName(MimeUtility.encodeText(fileds.getName()));
//			    mp.addBodyPart(bp);
//			}
//			return true;
//		} catch (Exception e) {
//			System.err.println("增加附件: " + filename + "--faild!" + e);
//			return false;
//		}
//	}
//
//	/**
//	 * 设置信的内容!
//	 *
//	 * @param mailBody
//	 * @return boolean
//	 */
//	public boolean setBody(String mailBody) {
//
//		try {
//			BodyPart bp = new MimeBodyPart();
//			;
//			// bp.setContent("<meta http-equiv=Context-Type
//			// context=text/html;charset=utf-8>"+mailBody,"text/html;charset=UTF-8");
//			bp.setContent(mailBody, "text/html;charset=UTF-8");
//			// bp.setContent("<meta http-equiv=Context-Type
//			// context=text/html;charset=gb2312>"+mailBody,"text/html;charset=GB2312");
//			mp.addBodyPart(bp);
//			return true;
//		} catch (Exception e) {
//			System.out.println("Set context Faild! " + e);
//			return false;
//		}
//	}
//
//	/*
//	 * 将对象转换字符串,用来处理request.getParameterMap()中的值 @param obj @return
//	 */
//	// private String objToString(Object obj) {
//	// if (obj != null) { // 判断对象是否是数组
//	// if (obj.getClass().isArray()) {
//	// String[] value = (String[]) obj;
//	// return correctDisplay(value[0]);
//	// }
//	// return correctDisplay(obj.toString());
//	// }
//	// return "";
//	// }
//	/**
//	 * 乱码处理
//	 *
//	 * @param temp
//	 * @return
//	 */
//	// private String correctDisplay(String temp) {
//	//
//	// try {
//	// temp = new String(temp.getBytes("ISO-8859-1"), "UTF-8");
//	// } catch (UnsupportedEncodingException e) {
//	// // TODO 自动生成 catch 块
//	// e.printStackTrace();
//	// }
//	// return temp;
//	// }
//}