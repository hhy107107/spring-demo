package me.smallyellow.base.core.operator.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;

import me.smallyellow.base.core.operator.AESOperator;
import me.smallyellow.base.core.operator.mail.config.MailConfig;

/**
 * 邮件工具类(发邮件SMTP)
 * @author hhy
 * 2017年12月4日下午3:14:36
 */
public class MailOperator {
    
	MailConfig mailConfig;
    
    private static MailOperator instance = null;  
    
    private MailOperator(MailConfig mailConfig) {  
    	this.mailConfig = mailConfig;
    }  
  
    public static MailOperator getInstance(MailConfig mailConfig) {  
        if (instance == null)  
            instance = new MailOperator(mailConfig);  
        return instance;  
    }  

    /**
     * 收件人邮箱
     * @param receiveMailAccount
     * @throws Exception
     */
    public void sendEmail(String receiveMailAccount) throws Exception {
    	 // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                    // 参数配置
        props.setProperty("mail.transport.protocol", "smtp");   // 使用的协议（JavaMail规范要求）
        props.setProperty("mail.smtp.host", mailConfig.getMyEmailSmtpHost());   // 发件人的邮箱的 SMTP 服务器地址
        props.setProperty("mail.smtp.auth", "true");            // 需要请求认证
        //开启 SSL 安全连接。
        props.setProperty("mail.smtp.port", mailConfig.getSmtpPort());
        props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.setProperty("mail.smtp.socketFactory.fallback", "false");
        props.setProperty("mail.smtp.socketFactory.port", mailConfig.getSmtpPort());
        // 2. 根据配置创建会话对象, 用于和邮件服务器交互
        Session session = Session.getInstance(props);
        session.setDebug(true);                                 // 设置为debug模式, 可以查看详细的发送 log
        // 3. 创建一封邮件
        MimeMessage message = createMimeMessage(session, mailConfig.getMyEmailAccount(), receiveMailAccount);
        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        // 5. 使用 邮箱账号 和 密码 连接邮件服务器, 这里认证的邮箱必须与 message 中的发件人邮箱一致, 否则报错
        transport.connect(mailConfig.getMyEmailAccount(), mailConfig.getMyEmailPassword());
        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
        // 7. 关闭连接
        transport.close();
    }
    
    /**
     * 创建一封只包含文本的简单邮件
     *
     * @param session 和服务器交互的会话
     * @param sendMail 发件人邮箱
     * @param receiveMail 收件人邮箱
     * @return
     * @throws Exception
     */
    public MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        // 1. 创建一封邮件
        MimeMessage message = new MimeMessage(session);
        // 2. From: 发件人（昵称有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改昵称）
        message.setFrom(new InternetAddress(sendMail, "小黄平台", "UTF-8"));
        // 3. To: 收件人（可以增加多个收件人、抄送、密送）
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "小黄平台用户", "UTF-8"));
        // 4. Subject: 邮件主题（标题有广告嫌疑，避免被邮件服务器误认为是滥发广告以至返回失败，请修改标题）
        message.setSubject("用户注册验证", "UTF-8");
        // 5. Content: 邮件正文（可以使用html标签）
        String url = mailConfig.getActivateUrl() + AESOperator.getInstance().encrypt(sendMail);
        message.setContent("<a herf=\"" + url + "\">欢迎注册小黄平台，请点击此处完成验证</a> </br>"
        + "如果没有跳转，请复制链接地址到浏览器" + url, "text/html;charset=UTF-8");
        // 6. 设置发件时间
        message.setSentDate(new Date());
        // 7. 保存设置
        message.saveChanges();
        return message;
    }
}
