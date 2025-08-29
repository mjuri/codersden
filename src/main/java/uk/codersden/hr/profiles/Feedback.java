package uk.codersden.hr.profiles;

import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="feedbacks")
public class Feedback {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
	private String identifier;
	
	@ManyToOne
    @JoinColumn(name="employee_identifier",  nullable=false, insertable=false, updatable=false)
	private Profile employee;
    
    @ManyToOne
    @JoinColumn(name="reviewer_identifier",  nullable=false, insertable=false, updatable=false)
    private Profile reviewer;
    
	@Column(name="employee_identifier")
	private String employeeIdentifier;
	
	@Column(name="reviewer_identifier")
	private String reviewerIdentifier;
    
    
	@Column(name="modDate")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp modDate;

	private boolean shared;
	
	private String knowledge;
	
	private String collaborations;
	
	private String trends;
	
	private boolean deleted;
	
	private boolean draft;
	
	@Column(name="due_date")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Timestamp dueDate;
	
	@Column(name="date_created")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp dateCreated;
	
	@Column(name="performance_evaluation")
	private String performanceEvaluation;
	
	private String comments;
	
	private String status;

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Profile getEmployee() {
		return employee;
	}

	public void setEmployee(Profile employee) {
		this.employee = employee;
	}

	public Profile getReviewer() {
		return reviewer;
	}

	public void setReviewer(Profile reviewer) {
		this.reviewer = reviewer;
	}

	public Timestamp getModDate() {
		return modDate;
	}

	public void setModDate(Timestamp modDate) {
		this.modDate = modDate;
	}

	public boolean isShared() {
		return shared;
	}

	public void setShared(boolean shared) {
		this.shared = shared;
	}

	public String getKnowledge() {
		return knowledge;
	}

	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}

	public String getCollaborations() {
		return collaborations;
	}

	public void setCollaborations(String collaborations) {
		this.collaborations = collaborations;
	}

	public String getTrends() {
		return trends;
	}

	public void setTrends(String trends) {
		this.trends = trends;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public Timestamp getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getPerformanceEvaluation() {
		return performanceEvaluation;
	}

	public void setPerformanceEvaluation(String performanceEvaluation) {
		this.performanceEvaluation = performanceEvaluation;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	public String getEmployeeIdentifier() {
		return employeeIdentifier;
	}

	public void setEmployeeIdentifier(String employeeIdentifier) {
		this.employeeIdentifier = employeeIdentifier;
	}

	public String getReviewerIdentifier() {
		return reviewerIdentifier;
	}

	public void setReviewerIdentifier(String reviewerIdentifier) {
		this.reviewerIdentifier = reviewerIdentifier;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		Feedback other = (Feedback) obj;
		return Objects.equals(identifier, other.identifier);
	}
	
}
