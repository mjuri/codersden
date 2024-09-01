package uk.codersden.hr.profiles;

import java.sql.Timestamp;


import com.fasterxml.jackson.annotation.JsonFormat;

public class AccountAccess {

	private String userName;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	
	private Timestamp start;
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp end;
	private String token;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Timestamp getStart() {
		return start;
	}
	public void setStart(Timestamp start) {
		this.start = start;
	}
	public Timestamp getEnd() {
		return end;
	}
	public void setEnd(Timestamp end) {
		this.end = end;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public AccountAccess() {}
}
