package uk.codersden.hr.profiles;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.codersden.hr.payroll.EmployeePayment;

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
	
	@Column(name="holiday_total_this_year")
	private Double holidayTotalThisYear;
	
	@Column(name="days_per_week")
	private Integer daysPerWeek;
	
	@Transient
	private Double salaryPerHour; 
	
	private Integer fte;
	
	@Column(name="my_line_manager")
	private String myLineManager;
	
	@Column(name="work_pattern")
	private String workPattern;
	
	@Column(name="brought_forward_scheme")
	private Double broughtForwardScheme;
	
	@Column(name="national_insurance_no")
	private String nationalInsuranceNo;
	
	@Column(name="salary_type")
	private String salaryType;
	
	@Column(name="gross_salary")
	private Double grossSalary;
	
	@Column(name="tax_code")
	private String taxCode;
	
	@Column(name="paye_deduction")
	private Double payeDeduction;
	
	@Column(name="national_insurance")
	private Double nationalInsurance;
	
	@Column(name="pension_contribution")
	private Double pensionContribution;
	
	@Column(name="other_deductions")
	private Double otherDeductions;
	
	@Column(name="net_pay")
	private Double netPay;
	
	
	public String getNationalInsuranceNo() {
		return nationalInsuranceNo;
	}

	public void setNationalInsuranceNo(String nationalInsuranceNo) {
		this.nationalInsuranceNo = nationalInsuranceNo;
	}

	public String getSalaryType() {
		return salaryType;
	}

	public void setSalaryType(String salaryType) {
		this.salaryType = salaryType;
	}

	public Double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(Double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public Double getPayeDeduction() {
		return payeDeduction;
	}

	public void setPayeDeduction(Double payeDeduction) {
		this.payeDeduction = payeDeduction;
	}

	public Double getNationalInsurance() {
		return nationalInsurance;
	}

	public void setNationalInsurance(Double nationalInsurance) {
		this.nationalInsurance = nationalInsurance;
	}

	public Double getPensionContribution() {
		return pensionContribution;
	}

	public void setPensionContribution(Double pensionContribution) {
		this.pensionContribution = pensionContribution;
	}

	public Double getOtherDeductions() {
		return otherDeductions;
	}

	public void setOtherDeductions(Double otherDeductions) {
		this.otherDeductions = otherDeductions;
	}

	public Double getNetPay() {
		return netPay;
	}

	public void setNetPay(Double netPay) {
		this.netPay = netPay;
	}

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

	public Double getHolidayEntitlement() {
		return holidayEntitlement;
	}

	public void setHolidayEntitlement(Double holidayEntitlement) {
		this.holidayEntitlement = holidayEntitlement;
	}

	public Double getHolidayBroughtForward() {

		return holidayBroughtForward;
	
	}

	public void setHolidayBroughtForward(Double holidayBroughtForward) {
		this.holidayBroughtForward = holidayBroughtForward;
	}

	public void setRightToWorkExpires(String rightToWorkExpires) {
		this.rightToWorkExpires = rightToWorkExpires;
	}

	@Column(name="holiday_entitlement")
	private Double holidayEntitlement;
	
	@Column(name="holiday_brought_forward")
	private Double holidayBroughtForward;
	
	@Column(name="start_date")
	private String startDate;
	
	@Column(name="cont_service")
	private String contService;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="contract_end_date")
	private Date contractEndDate;

	@JsonFormat(pattern="dd/MM/yyyy")
	@Column(name="onboard_date")
	private Date onBoardDate;
	
	@Column(name="onboard_contract")
	private String onBoardContract;
	
	@Column(name="benefits_start")
	private String benefitsStart;
	
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
	

	@Column(name="right_to_work_expires")
	private String rightToWorkExpires;

	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "contract")
    private Set<EmployeePayment> payments = new HashSet<>();
	
	public Set<EmployeePayment> getPayments() {
		return payments;
	}

	public void setPayments(Set<EmployeePayment> payments) {
		this.payments = payments;
	}

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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getContService() {
		return contService;
	}

	public void setContService(String contService) {
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

	public String getBenefitsStart() {
		return benefitsStart;
	}

	public void setBenefitsStart(String benefitsStart) {
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

	public String getRightToWorkExpires() {
		return rightToWorkExpires;
	}

	public void setRightToWorkExpiry(String rightToWorkExpires) {
		this.rightToWorkExpires = rightToWorkExpires;
	}

	public Double getBroughtForwardScheme() {
		return broughtForwardScheme;
	}

	public void setBroughtForwardScheme(Double broughtForwardScheme) {
		this.broughtForwardScheme = broughtForwardScheme;
	}

	public Double getHolidayTotalThisYear() {
		return holidayTotalThisYear;
	}

	public void setHolidayTotalThisYear(Double holidayTotalThisYear) {
		this.holidayTotalThisYear = holidayTotalThisYear;
	}

	public Double getSalaryPerHour() {
		return salaryPerHour;
	}

	public void setSalaryPerHour(Double salaryPerHour) {
		this.salaryPerHour = salaryPerHour;
	}
	
	
}
