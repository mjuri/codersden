package uk.codersden.profiles;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "contracts")
public class Contract {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String identifier;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="start_date")
	private Date startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="cont_service")
	private Date contService;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="contract_end_date")
	private Date contractEndDate;

	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="onboard_date")
	private Date onBoardDate;
	
	@Column(name="onboard_contract")
	private String onBoardContract;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="benefits_start")
	private Date benefitsStart;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="review_date")
	private Date reviewDate;
	
	@Column(name="job_type")
	private String jobType;
	
	private String country;
	private String location;
	
	@Column(name="office_role")
	private String officeRole;
	
	private String department;
	private String team;
	
	@Column(name="costCentre")
	private String costCentre;
	
	@Column(name="right_to_work")
	private String rightToWork;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="right_to_work_expiry")
	private Date rightToWorkExpiry;

	@OneToOne(mappedBy = "contract")
	private Profile profile;
	
	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getContService() {
		return contService;
	}

	public void setContService(Date contService) {
		this.contService = contService;
	}

	public Date getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(Date contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public Date getOnBoardDate() {
		return onBoardDate;
	}

	public void setOnBoardDate(Date onBoardDate) {
		this.onBoardDate = onBoardDate;
	}

	public String getOnBoardContract() {
		return onBoardContract;
	}

	public void setOnBoardContract(String onBoardContract) {
		this.onBoardContract = onBoardContract;
	}

	public Date getBenefitsStart() {
		return benefitsStart;
	}

	public void setBenefitsStart(Date benefitsStart) {
		this.benefitsStart = benefitsStart;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getOfficeRole() {
		return officeRole;
	}

	public void setOfficeRole(String officeRole) {
		this.officeRole = officeRole;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getCostCentre() {
		return costCentre;
	}

	public void setCostCentre(String costCentre) {
		this.costCentre = costCentre;
	}

	public String getRightToWork() {
		return rightToWork;
	}

	public void setRightToWork(String rightToWork) {
		this.rightToWork = rightToWork;
	}

	public Date getRightToWorkExpiry() {
		return rightToWorkExpiry;
	}

	public void setRightToWorkExpiry(Date rightToWorkExpiry) {
		this.rightToWorkExpiry = rightToWorkExpiry;
	}
	
	
	
}
