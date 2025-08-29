package uk.codersden.hr.payroll;

import java.sql.Time;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import uk.codersden.hr.profiles.Contract;

@Entity(name = "employee_payments")
public class EmployeePayment {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String identifier;
	
	@Column(name="amount")
	private Double amount;
	
	@Column(name="fiscal_year")
	private String fiscalYear;
	
	@Column(name="tax_code")
	private String taxCode;
	
	@Column(name="status")
	private String status;
	
	@Column(name="payment_date")
	private Timestamp paymentDate;
	
	@Column(name="profile_identifier")
	private String profileIdentifier;
	
    @ManyToOne
    @JoinColumn(name="contract_identifier",  nullable=false, insertable=false, updatable=false)
	@JsonIgnore
	private Contract contract;

	public String getIdentifier() {
		return identifier;
	}
	
	@Column(name="contract_identifier")
	private String contractIdentifier;

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Timestamp paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getProfileIdentifier() {
		return profileIdentifier;
	}

	public void setProfileIdentifier(String profileIdentifier) {
		this.profileIdentifier = profileIdentifier;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public String getContractIdentifier() {
		return contractIdentifier;
	}

	public void setContractIdentifier(String contractIdentifier) {
		this.contractIdentifier = contractIdentifier;
	}
    
    
	
}
