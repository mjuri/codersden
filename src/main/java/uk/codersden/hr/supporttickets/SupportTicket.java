package uk.codersden.hr.supporttickets;

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
@Table(name="supporttickets")
public class SupportTicket {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
    private String identifier;
	
	private String body;
	private String title;
	private String status;
	private String answer;
	private String type;
	
	@Column(name="moddate")
	private Timestamp modDate;
	
	@Column(name="date")
	private Timestamp dateCreated;
	
	public Timestamp getModDate() {
		return modDate;
	}

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}

	@Column(name="account_identifier")
	private String accountIdentifier;
	
	@Column(name="profile_identifier")
	private String profileIdentifier;

    @ManyToOne
    @JoinColumn(name="profile_identifier",  nullable=true, insertable=false, updatable=false)
	private Profile profile;
    
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public String getProfileIdentifier() {
		return profileIdentifier;
	}

	public void setProfileIdentifier(String profileIdentifier) {
		this.profileIdentifier = profileIdentifier;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identifier);
	}
	

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SupportTicket other = (SupportTicket) obj;
		return Objects.equals(identifier, other.identifier);
	}
	
	
}
