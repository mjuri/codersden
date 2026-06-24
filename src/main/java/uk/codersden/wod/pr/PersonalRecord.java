package uk.codersden.wod.pr;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="wod_personal_records")
public class PersonalRecord {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
    private String identifier;
	
	private String type;
	private Integer value;
	private Date date;
	@Column(name="previous_value")
	private Integer previousValue;
	
	@Column(name="profile_identifier")
	private String profileIdentifier;
	
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getPreviousValue() {
		return previousValue;
	}
	public void setPreviousValue(Integer previousValue) {
		this.previousValue = previousValue;
	}
	public String getProfileIdentifier() {
		return profileIdentifier;
	}
	public void setProfileIdentifier(String profileIdentifier) {
		this.profileIdentifier = profileIdentifier;
	}



}
