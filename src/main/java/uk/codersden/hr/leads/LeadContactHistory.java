package uk.codersden.hr.leads;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import uk.codersden.hr.profiles.Profile;

@Entity
@Table(name="lead_contact_history")
public class LeadContactHistory {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
    private String identifier;
	
    @ManyToOne
    @JoinColumn(name="profile_identifier",  nullable=false, insertable=false, updatable=false)
	private Profile profile;
    
	@Column(name="profile_identifier")
	private String profileIdentifier;
	
    @ManyToOne
    @JoinColumn(name="lead_identifier",  nullable=false, insertable=false, updatable=false)
	private Lead lead;
    
	@Column(name="lead_identifier")
	private String leadIdentifier;
    
	@Column(name="contact_date")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp contactDate;
	
	@Column(name="contact_type")
	private String contactType;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="attempt_number")
	private Integer attemptNumber;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public Lead getLead() {
		return lead;
	}

	public void setLead(Lead lead) {
		this.lead = lead;
	}

	public Timestamp getContactDate() {
		return contactDate;
	}

	public void setContactDate(Timestamp contactDate) {
		this.contactDate = contactDate;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getProfileIdentifier() {
		return profileIdentifier;
	}

	public void setProfileIdentifier(String profileIdentifier) {
		this.profileIdentifier = profileIdentifier;
	}

	public String getLeadIdentifier() {
		return leadIdentifier;
	}

	public void setLeadIdentifier(String leadIdentifier) {
		this.leadIdentifier = leadIdentifier;
	}

	public Integer getAttemptNumber() {
		return attemptNumber;
	}

	public void setAttemptNumber(Integer attemptNumber) {
		this.attemptNumber = attemptNumber;
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
		LeadContactHistory other = (LeadContactHistory) obj;
		return Objects.equals(identifier, other.identifier);
	}
	
	
}
