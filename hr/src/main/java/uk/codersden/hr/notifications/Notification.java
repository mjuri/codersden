package uk.codersden.hr.notifications;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import uk.codersden.hr.profiles.Profile;

@Entity
@Table(name="notifications")
public class Notification {
	public Profile getOwner() {
		return owner;
	}

	public void setOwner(Profile owner) {
		this.owner = owner;
	}

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
	private String identifier;
	
	private Timestamp time;
	private String message;
	
	private boolean unread;
	private boolean deleted;
	
	@ManyToOne
	@JoinColumn(name = "profile_identifier", nullable=true, referencedColumnName = "identifier", insertable = false, updatable = false)
	private Profile profile;
	
	@ManyToOne
	@JoinColumn(name = "owner_identifier", nullable=true, referencedColumnName = "identifier", insertable = false, updatable = false)
	private Profile owner;
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}


	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isUnRead() {
		return unread;
	}

	public void setUnRead(boolean unRead) {
		this.unread = unRead;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	
	
}
