package uk.codersden.hr.profiles.documents;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;

import uk.codersden.hr.profiles.Profile;

@Entity
@Table(name="documents")
public class Document {
	public String getIdentifier() {
		return identifier;
	}

	
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	/*
	 *     
	id: 1,
    img: product5,
    name: 'apple-smart-watch.png',
    user: 'Antony',
    time: 'Just Now',
    border: true
	 */
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator")
	private String identifier;
	
	private String img;
	private String name;
	private String status;
	
	
	
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(name="date_created")
	
	private Date dateCreated;
	
    @ManyToOne
    @JoinColumn(name="profile_identifier",  nullable=true)
	private Profile profile;
    

   
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
        name = "shareddocument_profile", 
        joinColumns = { @JoinColumn(name = "document_identifier") }, 
        inverseJoinColumns = { @JoinColumn(name = "profile_identifier") }
    )
    Set<Profile> sharedWith = new HashSet<>();

    public void addSharedWith(Profile p) {
    	sharedWith.add(p);
    }

	public Set<Profile> getSharedWith() {
		return sharedWith;
	}

	public void setSharedWith(Set<Profile> sharedWith) {
		this.sharedWith = sharedWith;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
