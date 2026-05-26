package uk.codersden.wod;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="wod_programs")
public class Program {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
    private String identifier;
	private String name;
	private String type;
	private String date;
	private String source;
	
	private String exercises;
	
	@Column(name="account_identifier")
	private String accountIdentifier;
	
	@Column(name="full_warm_up")
	private String fullWarmUp;
	private String strength;
	private String workout;
	private String modifications;
	@Column(name="cool_down")
	private String coolDown;
	
	private String tags;
	private String description;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAccountIdentifier() {
		return accountIdentifier;
	}
	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getFullWarmUp() {
		return fullWarmUp;
	}
	public void setFullWarmUp(String fullWarmUp) {
		this.fullWarmUp = fullWarmUp;
	}
	public String getStrength() {
		return strength;
	}
	public void setStrength(String strength) {
		this.strength = strength;
	}
	public String getWorkout() {
		return workout;
	}
	public void setWorkout(String workout) {
		this.workout = workout;
	}
	public String getModifications() {
		return modifications;
	}
	public void setModifications(String modifications) {
		this.modifications = modifications;
	}
	public String getCoolDown() {
		return coolDown;
	}
	public void setCoolDown(String coolDown) {
		this.coolDown = coolDown;
	}
	
	
	
	public String getExercises() {
		return exercises;
	}
	public void setExercises(String exercises) {
		this.exercises = exercises;
	}
	@Override
	public int hashCode() {
		return Objects.hash(date, source, type);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Program other = (Program) obj;
		return Objects.equals(date, other.date) && Objects.equals(source, other.source)
				&& Objects.equals(type, other.type);
	}

	
}
