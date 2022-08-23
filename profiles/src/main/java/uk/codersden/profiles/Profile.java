package uk.codersden.profiles;



import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@Column(name ="entitlement_absence")
	private int entitlementAbsence;
	


	@Column(name="account_identifier", nullable = true)
	private String accountIdentifier;
	
	@ManyToMany
	@JoinTable(name="organization_chart",
			 joinColumns=@JoinColumn(name="child_identifier"),
			 inverseJoinColumns=@JoinColumn(name="parent_identifier")
	)
	@JsonIgnoreProperties("children")
	private List<Profile> parents;

	@ManyToMany
	@JoinTable(name="organization_chart",
			 joinColumns=@JoinColumn(name="parent_identifier"),
			 inverseJoinColumns=@JoinColumn(name="child_identifier")

	)
	@JsonIgnoreProperties("parents")
	private List<Profile> children;

	public List<Profile> getParents() {
		return parents;
	}
	public void setParents(List<Profile> parents) {
		this.parents = parents;
	}
	public List<Profile> getChildren() {
		return children;
	}
	public void setChildren(List<Profile> children) {
		this.children = children;
	}
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


	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}

	
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	
	public int getEntitlementAbsence() {
		return entitlementAbsence;
	}
	public void setEntitlementAbsence(int entitlementAbsence) {
		this.entitlementAbsence = entitlementAbsence;
	}
	
}
