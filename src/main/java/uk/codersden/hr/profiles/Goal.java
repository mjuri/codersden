package uk.codersden.hr.profiles;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name="goals")
public class Goal {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
	private String identifier;
	
	private String name;
	
	@Column(name="target")
	private String targetValue;
	
	@Column(name="profile_identifier")
	private String profileIdentifier;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date deadline;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "goals")
	private Set<PerformanceReview> performanceReviews;
	
	
	private Integer progress;
	
	private String description;
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public Goal() {}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTargetValue() {
		return targetValue;
	}

	public void setTargetValue(String targetValue) {
		this.targetValue = targetValue;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getProfileIdentifier() {
		return profileIdentifier;
	}

	public void setProfileIdentifier(String profileIdentifier) {
		this.profileIdentifier = profileIdentifier;
	}
	
	
}
