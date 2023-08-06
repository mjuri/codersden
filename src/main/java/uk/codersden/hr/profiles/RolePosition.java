package uk.codersden.hr.profiles;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * It's nice to have the different states of the roles, but this could be developed in the future
 * @author mvelasco
 *
 */
@Entity(name = "role_positions")
public class RolePosition {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String identifier;
	
	@ManyToOne
	@JoinColumn(name = "requested_by", referencedColumnName = "identifier", insertable = false, updatable = false)
	private Profile requestedBy;
	private String grade;
	private String salaryLevel;
	private String jobDescription;
	
	@Column(name="requested_by")
	private String requestorIdentifier;
	
	@Column(name="assigned")
	private String assignedIdentifier;
	
	
	public String getAssignedIdentifier() {
		return assignedIdentifier;
	}
	public void setAssignedIdentifier(String assignedIdentifier) {
		this.assignedIdentifier = assignedIdentifier;
	}
	public String getRequestorIdentifier() {
		return requestorIdentifier;
	}
	public void setRequestorIdentifier(String requestorIdentifier) {
		this.requestorIdentifier = requestorIdentifier;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Profile getAssigned() {
		return assigned;
	}
	public void setAssigned(Profile assigned) {
		this.assigned = assigned;
	}
	private String contractType;
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "assigned", nullable=true, referencedColumnName = "identifier", insertable = false, updatable = false)
	private Profile assigned;
	
	private String header;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="date_created")
	private Date dateCreated;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="start_date")
	private Date startDate;
	
	
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public Profile getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(Profile requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getSalaryLevel() {
		return salaryLevel;
	}
	public void setSalaryLevel(String salaryLevel) {
		this.salaryLevel = salaryLevel;
	}
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	
	
	
}
