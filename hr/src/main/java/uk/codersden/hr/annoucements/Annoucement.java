package uk.codersden.hr.annoucements;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import uk.codersden.hr.profiles.Profile;

@Entity
@Table(name="annoucements")
public class Annoucement {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
    private String identifier;
	private String body;
	private String topic;
	private boolean draft;
	
	@Column(name="date_created")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp dateCreated;
	
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "annoucement_audience", 
        joinColumns = { @JoinColumn(name = "annoucement_identifier") }, 
        inverseJoinColumns = { @JoinColumn(name = "profile_identifier") }
    )
    Set<Profile> audience = new HashSet<>();
    
    @Transient
    private List<Map<String, String>> audienceValues;
    
	public List<Map<String, String>> getAudienceValues() {
		return audienceValues;
	}


	public void setAudienceValues(List<Map<String, String>> audienceValues) {
		this.audienceValues = audienceValues;
	}

	public Set<Profile> getAudience() {
		return audience;
	}

	public void setAudience(Set<Profile> audience) {
		this.audience = audience;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}
	@Column(name = "ping_to_the_top")
	private boolean pingToTheTop;
	
	@Column(name="email_notification")
	private boolean emailNotification;
	
	@Column(name="profile_identifier")
	private String profileIdentifier;
	
    @ManyToOne
    @JoinColumn(name="profile_identifier",  nullable=false, insertable=false, updatable=false)
	private Profile profile;
    
	public String getProfileIdentifier() {
		return profileIdentifier;
	}

	public void setProfileIdentifier(String profileIdentifier) {
		this.profileIdentifier = profileIdentifier;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	protected Annoucement() {
		
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
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public boolean isDraft() {
		return draft;
	}
	public void setDraft(boolean draft) {
		this.draft = draft;
	}
	public boolean isPingToTheTop() {
		return pingToTheTop;
	}
	public void setPingToTheTop(boolean pingToTheTop) {
		this.pingToTheTop = pingToTheTop;
	}
	public boolean isEmailNotification() {
		return emailNotification;
	}
	public void setEmailNotification(boolean emailNotification) {
		this.emailNotification = emailNotification;
	}


	public void addAudience(Profile p) {
		this.audience.add(p);
		
	}
	
}
