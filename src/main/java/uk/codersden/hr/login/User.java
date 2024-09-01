package uk.codersden.hr.login;

import java.util.Date;

public class User {

	
	private String userName;
	private String password;
	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	private Date dob;
	private String firstName;
	private String lastName;

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
		
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
		
	}

	public void setDOB(Date date) {
		this.dob = date;
		
	}

}
