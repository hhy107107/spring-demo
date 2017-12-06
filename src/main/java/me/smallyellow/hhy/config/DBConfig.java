package me.smallyellow.hhy.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "xh.datasource")
public class DBConfig {
	private String test1DriverClassName;
	private String test1Url;
	private String test1Username;
	private String test1Password;
	
	private String test2DriverClassName;
	private String test2Url;
	private String test2Username;
	private String test2Password;
	
	public String getTest1DriverClassName() {
		return test1DriverClassName;
	}

	public void setTest1DriverClassName(String test1DriverClassName) {
		this.test1DriverClassName = test1DriverClassName;
	}

	public String getTest1Url() {
		return test1Url;
	}

	public void setTest1Url(String test1Url) {
		this.test1Url = test1Url;
	}

	public String getTest1Username() {
		return test1Username;
	}

	public void setTest1Username(String test1Username) {
		this.test1Username = test1Username;
	}

	public String getTest1Password() {
		return test1Password;
	}

	public void setTest1Password(String test1Password) {
		this.test1Password = test1Password;
	}

	public String getTest2DriverClassName() {
		return test2DriverClassName;
	}

	public void setTest2DriverClassName(String test2DriverClassName) {
		this.test2DriverClassName = test2DriverClassName;
	}

	public String getTest2Url() {
		return test2Url;
	}

	public void setTest2Url(String test2Url) {
		this.test2Url = test2Url;
	}

	public String getTest2Username() {
		return test2Username;
	}

	public void setTest2Username(String test2Username) {
		this.test2Username = test2Username;
	}

	public String getTest2Password() {
		return test2Password;
	}

	public void setTest2Password(String test2Password) {
		this.test2Password = test2Password;
	}
}
