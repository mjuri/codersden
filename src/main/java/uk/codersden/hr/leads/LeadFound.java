package uk.codersden.hr.leads;

import java.util.Objects;

public class LeadFound {

	private boolean leadFound;
	private String identifier;
	private Lead lead;
	private int attempts;
	
	
	public LeadFound () {
		attempts = 0;
	}
	
	public Lead getLead() {
		return lead;
	}

	public void setLead(Lead lead) {
		this.lead = lead;
	}

	public boolean isLeadFound() {
		return leadFound;
	}
	public void setLeadFound(boolean leadFound) {
		this.leadFound = leadFound;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public int getAttempts() {
		return attempts;
	}
	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}
	public int getNextAttempt() {
		return attempts++;
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
		LeadFound other = (LeadFound) obj;
		return Objects.equals(identifier, other.identifier);
	}


}
