package uk.codersden.hr.profiles;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class JobVaccancyCandidate implements Serializable {
	@Column(name="role_position_identifier")
	private String jobVaccancyIdentifier;
	
	@Column(name="candidate_identifier")
	private String candidateIdentifier;
	
	public JobVaccancyCandidate(String rolePositionIdentifier, String candidateIdentifier) {
		this.setCandidateIdentifier(candidateIdentifier);
		this.setJobVaccancyIdentifier(rolePositionIdentifier);
	}
	protected JobVaccancyCandidate() {
		
	}

	public String getJobVaccancyIdentifier() {
		return jobVaccancyIdentifier;
	}

	public void setJobVaccancyIdentifier(String jobVaccancyIdentifier) {
		this.jobVaccancyIdentifier = jobVaccancyIdentifier;
	}

	public String getCandidateIdentifier() {
		return candidateIdentifier;
	}

	public void setCandidateIdentifier(String candidateIdentifier) {
		this.candidateIdentifier = candidateIdentifier;
	}

}