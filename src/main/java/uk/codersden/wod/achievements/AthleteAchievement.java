package uk.codersden.wod.achievements;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="wod_athlete_achievement")
public class AthleteAchievement {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
	private String identifier;
	@Column(name="profile_identifier")
	private String profileIdentifier;
	
	@Column(name="achievement_identifier")
	private String achievementIdentifier;
	
	private Boolean unlocked;
	private Integer progress;
	private Date date;
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getProfileIdentifier() {
		return profileIdentifier;
	}
	public void setProfileIdentifier(String profileIdentifier) {
		this.profileIdentifier = profileIdentifier;
	}

	public Boolean getUnlocked() {
		return unlocked;
	}
	public void setUnlocked(Boolean unlocked) {
		this.unlocked = unlocked;
	}
	public Integer getProgress() {
		return progress;
	}
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAchievementIdentifier() {
		return achievementIdentifier;
	}
	public void setAchievementIdentifier(String achievementIdentifier) {
		this.achievementIdentifier = achievementIdentifier;
	}
	
	
}
