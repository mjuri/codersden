package uk.codersden.accountancy;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "payments")
public class Payment {
	@JsonIgnore
	public List<Invoice> getInvoices() {
		return invoices;
	}

	public void setInvoices(List<Invoice> invoices) {
		this.invoices = invoices;
	}

	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
	private String identifier;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp date;
	
	private double amount;
	private String contact;
	private String ref;
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	@Column(name = "bank_account")
	private String bankAccount;
	

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(
	  name = "invoice_payment", 
	  joinColumns = @JoinColumn(name = "payment_identifier"), 
	  inverseJoinColumns = @JoinColumn(name = "invoice_identifier"))
	List<Invoice> invoices;
	
	public double getAmount() {
		return amount;
	}
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	
	@JsonFormat(pattern="dd/MM/yyyy")
	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
