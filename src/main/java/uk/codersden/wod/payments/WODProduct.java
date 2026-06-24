package uk.codersden.wod.payments;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="wod_membership_plan")
public class WODProduct {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
	private String identifier;
	
	@Column(name="account_identifier")
	private String accountIdentifier;
	
	@Column(name="stripe_product_id")
	private String stripeProductId;
	
	@Column(name="gym_identifier")
	private String wodGymIdentifier;

	private String description;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal price;
	
	@Column(name="billing_cyrcle")
	private String billingCyrcle;
	
	@Column(name="stripe_price_id")
	private String stripePriceId;
	
	@Column(name="max_classes")
	private Integer maxClasses;
	
	@Column(name="more_information")
	private String moreInformation;
	
	private Boolean active;
	
	
	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public String getStripeProductId() {
		return stripeProductId;
	}

	public void setStripeProductId(String stripeProductId) {
		this.stripeProductId = stripeProductId;
	}

	public String getWodGymIdentifier() {
		return wodGymIdentifier;
	}

	public void setWodGymIdentifier(String wodGymIdentifier) {
		this.wodGymIdentifier = wodGymIdentifier;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}



	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getBillingCyrcle() {
		return billingCyrcle;
	}

	public void setBillingCyrcle(String billingCyrcle) {
		this.billingCyrcle = billingCyrcle;
	}

	public String getStripePriceId() {
		return stripePriceId;
	}

	public void setStripePriceId(String stripePriceId) {
		this.stripePriceId = stripePriceId;
	}

	public Integer getMaxClasses() {
		return maxClasses;
	}

	public void setMaxClasses(Integer maxClasses) {
		this.maxClasses = maxClasses;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getMoreInformation() {
		return moreInformation;
	}

	public void setMoreInformation(String moreInformation) {
		this.moreInformation = moreInformation;
	}
	
	
}
