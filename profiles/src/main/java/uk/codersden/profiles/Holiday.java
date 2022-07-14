package uk.codersden.profiles;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
	private Date start;
	
	@Column(name="end_date")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Date end;
	
	@Column(name="profile_identifier")
	private String profileIdentifier;
	
	private String comments;
	private HolidayType type;
	
	@Column(name="authorized_by")
	private String authorizedBy;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="date_created")
	private Date dateCreated;
	
	@Column(name="mod_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date modDate;
	
	private HolidayStatus status;


	public Holiday() {
		
	}
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
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

	public HolidayType getType() {
		return type;
	}

	public void setType(HolidayType type) {
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

	public HolidayStatus getStatus() {
		return status;
	}

	public void setStatus(HolidayStatus status) {
		this.status = status;
	}
	
}
