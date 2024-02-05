package uk.codersden.hr.profiles;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "contracts")
public class Contract {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String identifier;
	
	@Column(name="length_of_service")
	private String lengthOfService;
	
	@Column(name="notice_period")
	private String noticePeriod;
	
	@Column(name="hours_per_week")
	private Integer hoursPerWeek;
	
	@Column(name="days_per_week")
	private Integer daysPerWeek;
	
	private Integer fte;
	
	@Column(name="my_line_manager")
	private String myLineManager;
	
	@Column(name="work_pattern")
	private String workPattern;
	
	public String getLengthOfService() {
		return lengthOfService;
	}

	public void setLengthOfService(String lengthOfService) {
		this.lengthOfService = lengthOfService;
	}

	public String getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public Integer getHoursPerWeek() {
		return hoursPerWeek;
	}

	public void setHoursPerWeek(Integer hoursPerWeek) {
		this.hoursPerWeek = hoursPerWeek;
	}

	public Integer getDaysPerWeek() {
		return daysPerWeek;
	}

	public void setDaysPerWeek(Integer daysPerWeek) {
		this.daysPerWeek = daysPerWeek;
	}

	public Integer getFte() {
		return fte;
	}

	public void setFte(Integer fte) {
		this.fte = fte;
	}

	public String getMyLineManager() {
		return myLineManager;
	}

	public void setMyLineManager(String myLineManager) {
		this.myLineManager = myLineManager;
	}

	public String getWorkPattern() {
		return workPattern;
	}

	public void setWorkPattern(String workPattern) {
		this.workPattern = workPattern;
	}

	public Integer getHolidayEntitlement() {
		return holidayEntitlement;
	}

	public void setHolidayEntitlement(Integer holidayEntitlement) {
		this.holidayEntitlement = holidayEntitlement;
	}

	public Integer getHolidayBroughtForward() {
		return holidayBroughtForward;
	}

	public void setHolidayBroughtForward(Integer holidayBroughtForward) {
		this.holidayBroughtForward = holidayBroughtForward;
	}

	public void setRightToWorkExpires(Date rightToWorkExpires) {
		this.rightToWorkExpires = rightToWorkExpires;
	}

	@Column(name="holiday_entitlement")
	private Integer holidayEntitlement;
	
	@Column(name="holiday_brought_forward")
	private Integer holidayBroughtForward;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@Column(name="start_date")
	private Timestamp startDate;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@Column(name="cont_service")
	private Timestamp contService;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="contract_end_date")
	private Date contractEndDate;

	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="onboard_date")
	private Date onBoardDate;
	
	@Column(name="onboard_contract")
	private String onBoardContract;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	@Column(name="benefits_start")
	private Date benefitsStart;
	
	@JsonFormat(pattern="dd/MM/yyyy")
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
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="right_to_work_expires")
	private Date rightToWorkExpires;

	
	@OneToOne(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "profile_identifier", referencedColumnName = "identifier")
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

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getContService() {
		return contService;
	}

	public void setContService(Timestamp contService) {
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

	public Date getRightToWorkExpires() {
		return rightToWorkExpires;
	}

	public void setRightToWorkExpiry(Date rightToWorkExpires) {
		this.rightToWorkExpires = rightToWorkExpires;
	}
	
	
	
}
