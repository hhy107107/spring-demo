package me.smallyellow.base.core.operator.mail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "xh.mail")
public class MailConfig {
	private String myEmailAccount; //邮箱地址
	private String myEmailPassword; // smtp独立密码（授权码）
	private String myEmailSmtpHost; // 发件人邮箱的 SMTP 服务器地址
	private String smtpPort; // 邮箱的SSL端口
	private String activateUrl; // 用户激活地址
	
	public String getMyEmailAccount() {
		return myEmailAccount;
	}
	public void setMyEmailAccount(String myEmailAccount) {
		this.myEmailAccount = myEmailAccount;
	}
	public String getMyEmailPassword() {
		return myEmailPassword;
	}
	public void setMyEmailPassword(String myEmailPassword) {
		this.myEmailPassword = myEmailPassword;
	}
	public String getSmtpPort() {
		return smtpPort;
	}
	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}
	public String getActivateUrl() {
		return activateUrl;
	}
	public void setActivateUrl(String activateUrl) {
		this.activateUrl = activateUrl;
	}
	public String getMyEmailSmtpHost() {
		return myEmailSmtpHost;
	}
	public void setMyEmailSmtpHost(String myEmailSmtpHost) {
		this.myEmailSmtpHost = myEmailSmtpHost;
	}
	
}
