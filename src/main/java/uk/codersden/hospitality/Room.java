package uk.codersden.hospitality;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="room")
public class Room {

		@Id
	    @GeneratedValue(generator = "UUID")
	    @GenericGenerator(
	        name = "UUID",
	        strategy = "org.hibernate.id.UUIDGenerator")
	    private String identifier;
		private String name;
		private String type;
		
		@Column(name="account_identifier")
		private String accountIdentifier;
		
		private Integer capacity;
		
		private String facilities;

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

		public String getAccountIdentifier() {
			return accountIdentifier;
		}

		public void setAccountIdentifier(String accountIdentifier) {
			this.accountIdentifier = accountIdentifier;
		}

		public Integer getCapacity() {
			return capacity;
		}

		public void setCapacity(Integer capacity) {
			this.capacity = capacity;
		}

		public String getFacilities() {
			return facilities;
		}

		public void setFacilities(String facilities) {
			this.facilities = facilities;
		}

		@Override
		public int hashCode() {
			return Objects.hash(identifier);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Room other = (Room) obj;
			return Objects.equals(identifier, other.identifier);
		}

		
}
