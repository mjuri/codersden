package uk.codersden.hr.profiles;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "profiles")
public class Candidate {
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
	
	@JsonFormat(pattern="dd/MM/yyyy")
	private Date dob;
	private String email;
	
	private boolean deleted;
	private String avatar;
	private boolean online;
		
	private String title;
		
	@Column(name = "known_as")
	private String knownAs;
	
	private String address;
	private String gender;
	private String type;
	
	@Column(name = "gender_identity")
	private String genderIdentity;
	
	@Column(name = "preferred_pronoun")
	private String preferredPronoun;
	
	@Column(name = "marital_status")
	private String maritalStatus;
	
	@Column(name = "employee_number")
	private Integer employeeNumber;
	
	@Column(name = "work_phone")
	private String workPhone;
	
	@Column(name = "work_extn")
	private String workExtn;
	
	@Column(name = "work_mobile")
	private String workMobile;
	
	@Column(name = "personal_email")
	private String personalEmail;
	
	@Column(name = "personal_mobile")
	private String personalMobile;
	
	@Column(name = "home_phone")
	private String homePhone;
	
	@Column(name="account_identifier", nullable = true)
	private String accountIdentifier;

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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getKnownAs() {
		return knownAs;
	}

	public void setKnownAs(String knownAs) {
		this.knownAs = knownAs;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGenderIdentity() {
		return genderIdentity;
	}

	public void setGenderIdentity(String genderIdentity) {
		this.genderIdentity = genderIdentity;
	}

	public String getPreferredPronoun() {
		return preferredPronoun;
	}

	public void setPreferredPronoun(String preferredPronoun) {
		this.preferredPronoun = preferredPronoun;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public Integer getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(Integer employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getWorkExtn() {
		return workExtn;
	}

	public void setWorkExtn(String workExtn) {
		this.workExtn = workExtn;
	}

	public String getWorkMobile() {
		return workMobile;
	}

	public void setWorkMobile(String workMobile) {
		this.workMobile = workMobile;
	}

	public String getPersonalEmail() {
		return personalEmail;
	}

	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	public String getPersonalMobile() {
		return personalMobile;
	}

	public void setPersonalMobile(String personalMobile) {
		this.personalMobile = personalMobile;
	}

	public String getHomePhone() {
		return homePhone;
	}

	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}
	

}
