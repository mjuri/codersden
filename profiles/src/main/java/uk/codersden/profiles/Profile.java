package uk.codersden.profiles;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

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
	//private Date dao;
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
	
	/*public Date getDao() {
		return dao;
	}
	public void setDao(Date dao) {
		this.dao = dao;
	}*/
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
	
	
}
