package uk.codersden.hr.settings;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="settings")
public class Settings {
	@Id
	private String identifier;

	@Column(name = "mailsmtphost")
	private String mailSmtpHost;
	@Column(name ="mailsmtpport")
	private String mailSmtpPort;
	
	@Column(name ="mailsmtpauth")
	private boolean mailSmtpAuth;
	
	@Column(name = "mailsmtpstarttlsenable")
	private boolean mailSmtpStarttlsEnable;
	
	@Column(name = "mailusername")
	private String mailUsername;
	
	@Column(name = "linkelntoken")
	private String linkeinToken;
	
	@Column(name = "mailpassword")
	private String mailPassword;
	
	public String getMailUsername() {
		return mailUsername;
	}
	public void setMailUsername(String mailUsername) {
		this.mailUsername = mailUsername;
	}
	public String getMailPassword() {
		return mailPassword;
	}
	public void setMailPassword(String mailPassword) {
		this.mailPassword = mailPassword;
	}
	protected Settings()
	{
		
	}	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getMailSmtpHost() {
		return mailSmtpHost;
	}
	public void setMailSmtpHost(String mailSmtpHost) {
		this.mailSmtpHost = mailSmtpHost;
	}
	public String getMailSmtpPort() {
		return mailSmtpPort;
	}
	public void setMailSmtpPort(String mailSmtpPort) {
		this.mailSmtpPort = mailSmtpPort;
	}
	public boolean isMailSmtpAuth() {
		return mailSmtpAuth;
	}
	public void setMailSmtpAuth(boolean mailSmtpAuth) {
		this.mailSmtpAuth = mailSmtpAuth;
	}
	public boolean isMailSmtpStarttlsEnable() {
		return mailSmtpStarttlsEnable;
	}
	public void setMailSmtpStarttlsEnable(boolean mailSmtpStarttlsEnable) {
		this.mailSmtpStarttlsEnable = mailSmtpStarttlsEnable;
	}
	public String getLinkeinToken() {
		return linkeinToken;
	}
	public void setLinkeinToken(String linkeinToken) {
		this.linkeinToken = linkeinToken;
	}
	
}
