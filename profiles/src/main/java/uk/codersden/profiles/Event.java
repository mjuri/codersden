package uk.codersden.profiles;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "events")
public class Event {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
    private String identifier;
	
    @ManyToOne
    @JoinColumn(name="profile_identifier",  nullable=false, insertable=false, updatable=false)
	private Profile profile;
	
	public Profile getProfile() {
		return profile;
	}
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
    
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@Column(name="start")
	private Timestamp startDate;
	
	@Column(name="end_date")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp endDate;
	
	private String description;
	private String label;
    private String status;
    
	@Column(name="profile_identifier")
	private String profileIdentifier;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@CreationTimestamp
	@Column(name="date_created")
	private Date dateCreated;
	
	@Column(name="mod_date")
	@CreationTimestamp
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date modDate;
	
	@Column(name="includesaturday")
	private boolean includeSaturday;
	
	@Column(name="includesunday")
	private boolean includeSunday;
	
    
	@Column(name="all_day")
	private boolean allDay;
	
    public boolean isAllDay() {
		return allDay;
	}
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	private String title;
	private String location;
    private String url;
    
    @ManyToMany(mappedBy = "events")
    private Set<Profile> attendees = new HashSet<>();
	
    
    public Set<Profile> getAttendees() {
		return attendees;
	}
	public void setAttendees(Set<Profile> attendees) {
		this.attendees = attendees;
	}
	
	// Default constructor
    public Event() {
    }
    
    // Getters and setters
    
    public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIdentifier() {
        return identifier;
    }
    
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    public Timestamp getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }
    
    public Timestamp getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }
        
    public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

    
    public String getLabel() {
		return label;
	}
    
	public void setLabel(String label) {
		this.label = label;
	}
	public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getProfileIdentifier() {
        return profileIdentifier;
    }
    
    public void setProfileIdentifier(String profileIdentifier) {
        this.profileIdentifier = profileIdentifier;
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
    
    public boolean isIncludeSaturday() {
        return includeSaturday;
    }
    
    public void setIncludeSaturday(boolean includeSaturday) {
        this.includeSaturday = includeSaturday;
    }
    
    public boolean isIncludeSunday() {
        return includeSunday;
    }
    
    public void setIncludeSunday(boolean includeSunday) {
        this.includeSunday = includeSunday;
    }
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
    
    
}

