package uk.codersden.accountancy;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity(name = "invoices")
public class Invoice {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
	private String identifier;
	
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp date;
	
	@Column(name="due_date")
	@JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
	private Timestamp dueDate;
	
	public Invoice() {

	}
	
	private double outstanding = 0;
	
	private String status;
		
	public String getStatus() {
		return this.outstanding == 0 ? "paid" : "o/s";
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "invoice_identifier", referencedColumnName = "identifier")
    private List<InvoiceItem> items = new ArrayList<>();
    
	public List<InvoiceItem> getItems() {
		return items;
	}

	public void setItems(List<InvoiceItem> items) {
		this.items = items;
	}
	private String ref;
	
	@Column(name="invoice_number")
	private String invoiceNumber;
	
	private String contact;
	
	private String project;
	
	@Transient
	private double amount;
	
	
	@Transient
	public double getAmount() {
		//double amount = 0;
		this.items.forEach(item -> {
			this.amount += item.getAmount();
		});
		
		this.amount = Math.round(this.amount * 100.0) / 100.0;
		return this.amount;
	}



	@Column(name="account_identifier")
	private String accountIdentifier;

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
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

	@JsonFormat(pattern="dd/MM/yyyy")
	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public String getRef() {
		return ref;
	}

	public void setRef(String ref) {
		this.ref = ref;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	private double calculateOutstanding(){
		double paymentsAmount = 0.00;
		double itemsAmount = 0.00;
		for(Payment p: this.payments) {
			paymentsAmount += p.getAmount();
		}
		for(InvoiceItem i: this.items) {
			itemsAmount += i.getAmount();
		}
		
		return itemsAmount - paymentsAmount;
				
	}
	public double getOutstanding() {
		if(this.outstanding == 0) {
			this.outstanding = this.calculateOutstanding();
		}
		double value = Math.round(this.outstanding * 100.0) / 100.0;
		return value;
	}

	public void setOutstanding(double outstanding) {
		this.outstanding = outstanding;
	}
	
	@ManyToMany(mappedBy="invoices")
	List<Payment> payments;

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	
}
