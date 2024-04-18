package uk.codersden.hr.groups;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import uk.codersden.hr.profiles.Profile;

@Entity
@Table(name="groups")
public class Group {
	@Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
	private String identifier;
	private String name;
	private String type;
	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name="account_identifier")
	private String accountIdentifier;
	
    @ManyToMany(mappedBy = "groups")
    private Set<Profile> members = new HashSet<>();
	
    @Transient
    private List<Map<String, String>> membersValues;
    
	public String getAccountIdentifier() {
		return accountIdentifier;
	}

	public void setAccountIdentifier(String accountIdentifier) {
		this.accountIdentifier = accountIdentifier;
	}

	public List<Map<String, String>> getMembersValues() {
		return membersValues;
	}

	public void setMembersValues(List<Map<String, String>> membersValues) {
		this.membersValues = membersValues;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<Profile> getMembers() {
		return members;
	}
	public void addMember(Profile member) {
		this.members.add(member);
	}
	public void setMembers(Set<Profile> members) {
		this.members = members;
	}
	
}
