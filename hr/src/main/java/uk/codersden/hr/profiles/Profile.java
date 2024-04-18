package uk.codersden.hr.profiles;



import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import uk.codersden.hr.groups.Group;

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
	
	@OneToOne(mappedBy = "profile")
	private Contract contract;
	
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
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
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	@Column(name ="entitlement_absence")
	private int entitlementAbsence;
	
	@ManyToMany(cascade = { CascadeType.ALL })
	@JoinTable(name="profile_role",
			 joinColumns=@JoinColumn(name="profile_identifier"),
			 inverseJoinColumns=@JoinColumn(name="role_key")
	)
	@JsonIgnoreProperties("profiles")
	private List<Role> roles;
	
	
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	@ManyToMany(mappedBy = "children")
	@JsonIgnoreProperties("children")
	private List<Profile> parents;



	@Column(name="account_identifier", nullable = true)
	private String accountIdentifier;
	

	@ManyToMany
	@JoinTable(name="organization_chart",
			 joinColumns=@JoinColumn(name="parent_identifier"),
			 inverseJoinColumns=@JoinColumn(name="child_identifier")

	)
	@JsonIgnoreProperties("parents")
	private List<Profile> children;

   // @ManyToMany(mappedBy = "sharedWith")
   // private Set<Document> sharedDocumentsWith = new HashSet<>();
	
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
	
	@ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(
        name = "profile_group",
        joinColumns = @JoinColumn(name = "profile_identifier"),
        inverseJoinColumns = @JoinColumn(name = "group_identifier")
    )
	@JsonIgnoreProperties("members")
	@JsonIgnore
    private Set<Group> groups;
	
	public Set<Group> getGroups() {
		return groups;
	}
	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}
	@ManyToMany(cascade = {CascadeType.MERGE},fetch = FetchType.EAGER)
    @JoinTable(
        name = "event_attendee",
        joinColumns = @JoinColumn(name = "profile_identifier"),
        inverseJoinColumns = @JoinColumn(name = "event_identifier")
    )
	@JsonIgnoreProperties("attendees")
	@JsonIgnore
    private Set<Event> events;

	public Set<Event> getEvents() {
		return events;
	}
	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	
	public boolean isOnline() {
		return online;
	}
	public void setOnline(boolean online) {
		this.online = online;
	}
	@Override
	public int hashCode() {
		return Objects.hash(identifier);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profile other = (Profile) obj;
		return Objects.equals(identifier, other.identifier);
	}

	/*public Set<Document> getSharedDocumentsWith() {
		return sharedDocumentsWith;
	}
	public void setSharedDocumentsWith(Set<Document> sharedDocumentsWith) {
		this.sharedDocumentsWith = sharedDocumentsWith;
	}*/
	
}
