package uk.codersden.login;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "accesses")
public class AccountAccess {
	@Column(name = "user_name")
	private String userName;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date start;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@Column(name = "ends")
	private Date end;
	public AccountAccess(String userName) {
		this.setUserName(userName);
	}
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
	private String token;
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
