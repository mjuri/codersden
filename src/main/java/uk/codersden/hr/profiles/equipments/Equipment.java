package uk.codersden.hr.profiles.equipments;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import uk.codersden.hr.profiles.Profile;

@Entity
@Table(name="equipments")
public class Equipment {
	
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
    private String identifier;
	
	@Column(name="asset_reference")
	private String assetReference;
	
	@Column(name="serial_number")
	private String serialNumber;
	
	private String item;
	
	private String accountIdentifier;
	
	@Column(name="profile_identifier")
	private String profileIdentifier;
	

	public String getIdentifier() {
		return identifier;
	}

	
	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getAssetReference() {
		return assetReference;
	}

	public void setAssetReference(String assetReference) {
		this.assetReference = assetReference;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getProfileIdentifier() {
		return profileIdentifier;
	}

	public void setProfileIdentifier(String profileIdentifier) {
		this.profileIdentifier = profileIdentifier;
	}
	

}
