package uk.codersden.wod.templates;

import java.time.DayOfWeek;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "wod_class_template_days")
public class ClassTemplateDay {

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
    private String identifier;

	@Enumerated(EnumType.STRING)
	@Column(name="day_of_week")
	private DayOfWeek dayOfWeek;

	private String coach;

	private String room;

	@Column(name="max_capacity")
	private Integer maxCapacity;

	private String color;

	@Column(name="cancellation_policy_hours")
	private Integer cancellationPolicyHours;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "template_identifier")
	@JsonIgnore
	private Template classTemplate;


	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public DayOfWeek getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(DayOfWeek dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getCoach() {
		return coach;
	}

	public void setCoach(String coach) {
		this.coach = coach;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
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

	public Template getClassTemplate() {
		return classTemplate;
	}

	public void setClassTemplate(Template classTemplate) {
		this.classTemplate = classTemplate;
	}

}
