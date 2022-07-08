package uk.codersden.profiles;



import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "profiles")
public class Profile {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String identifier;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name ="lastname")
	private String lastName;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date dob;
	private String email;
	
	private boolean deleted;
	
	@Column(name="account_identifier", nullable = true)
	private String accountIdentifier;
    /*@ManyToOne
    @JoinColumn(name="account_identifier", nullable=false)
	private Account account;
	*/
	public Profile() {
		
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
<<<<<<< HEAD
	
	/*public Date getDao() {
		return dao;
	}
	public void setDao(Date dao) {
		this.dao = dao;
	}*/
=======

>>>>>>> b6573a897700421d9951e3319d18aba95ead9910
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
		
	}
	public boolean isDeleted() {
		return this.deleted;
	}
<<<<<<< HEAD
	/*
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}*/
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	
=======
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
>>>>>>> b6573a897700421d9951e3319d18aba95ead9910
	
}
