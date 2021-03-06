package uk.codersden.profiles;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "holidays")
public class Holiday {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
	private String identifier;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp start;
	
	@Column(name="end_date")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp end;
	
	@Column(name="profile_identifier")
	private String profileIdentifier;
	
	private String comments;
	private String type;
	
	@Column(name="authorized_by")
	private String authorizedBy;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="date_created")
	private Date dateCreated;
	
	@Column(name="mod_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date modDate;

	private String status = HolidayStatus.REQUESTED.toString();


	public Holiday() {
		
	}
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
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

	public String getProfileIdentifier() {
		return profileIdentifier;
	}

	public void setProfileIdentifier(String profileIdentifier) {
		this.profileIdentifier = profileIdentifier;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	public String getAuthorizedBy() {
		return authorizedBy;
	}

	public void setAuthorizedBy(String authorizedBy) {
		this.authorizedBy = authorizedBy;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
