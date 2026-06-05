package uk.codersden.wod.classes;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import uk.codersden.hr.profiles.Profile;
@Entity
@Table(name="wod_classes")
public class WODClass {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
	private String identifier;
	

    @Column(name = "template_identifier")
    private String templateIdentifier;
	
    private LocalDate date;

    @Column(name="start_time")
    private LocalTime startTime;

    @Column(name="end_time")
    private LocalTime endTime;

    
    private String coach;
    
    @Column(name="account_identifier")
    private String accountIdentifier;
    
    private String name;
    


	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	@Column(name="max_capacity")
	private Integer maxCapacity;

	private String color;

	@Column(name="cancellation_policy_hours")
	private Integer cancellationPolicyHours;
	
	@ManyToMany
    @JoinTable(
            name = "wod_class_attendees",
            joinColumns = @JoinColumn(name = "class_identifier"),
            inverseJoinColumns = @JoinColumn(name = "profile_identifier")
        )
	private List<Profile> attendees;
	
    @ManyToMany
    @JoinTable(
        name = "wod_class_cancellations",
        joinColumns = @JoinColumn(name = "class_identifier"),
        inverseJoinColumns = @JoinColumn(name = "profile_identifier")
    )
	private List<Profile> cancellations;
	
    @ManyToMany
    @JoinTable(
        name = "wod_class_absents",
        joinColumns = @JoinColumn(name = "class_identifier"),
        inverseJoinColumns = @JoinColumn(name = "profile_identifier")
    )    
	private List<Profile> absents;

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

	public String getTemplateIdentifier() {
		return templateIdentifier;
	}

	public void setTemplateIdentifier(String templateIdentifier) {
		this.templateIdentifier = templateIdentifier;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public Integer getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(Integer maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getCancellationPolicyHours() {
		return cancellationPolicyHours;
	}

	public void setCancellationPolicyHours(Integer cancellationPolicyHours) {
		this.cancellationPolicyHours = cancellationPolicyHours;
	}



	public List<Profile> getAttendees() {
		return attendees;
	}

	public void setAttendees(List<Profile> attendees) {
		this.attendees = attendees;
	}

	public List<Profile> getCancellations() {
		return cancellations;
	}

	public void setCancellations(List<Profile> cancellations) {
		this.cancellations = cancellations;
	}

	public List<Profile> getAbsents() {
		return absents;
	}

	public void setAbsents(List<Profile> absents) {
		this.absents = absents;
	}

	@Override
	public int hashCode() {
		return Objects.hash(identifier);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WODClass other = (WODClass) obj;
		return Objects.equals(identifier, other.identifier);
	}
	
	
	
	
}
