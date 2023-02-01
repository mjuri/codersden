package uk.codersden.accountancy;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name = "invoice_item")
public class InvoiceItem {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
	private String identifier;
	
	@Column(name="nominal_code")
	private String nominalCode;
	
	@Column(name="line_ref")
	private String lineRef;
	
	private double quantity;
	
	private double amount;
	
	private String vat;
	

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	@Column(name="amount_type")
	private String amountType;

	public String getAmountType() {
		return amountType;
	}

	public void setAmountType(String amountType) {
		this.amountType = amountType;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getNominalCode() {
		return nominalCode;
	}

	public void setNominalCode(String nominalCode) {
		this.nominalCode = nominalCode;
	}

	public String getLineRef() {
		return lineRef;
	}

	public void setLineRef(String lineRef) {
		this.lineRef = lineRef;
	}

	public double getQuantity() {
		return quantity;
	}


	public double getAmount() {
		return  Math.round(this.amount * 100.0) / 100.0;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
