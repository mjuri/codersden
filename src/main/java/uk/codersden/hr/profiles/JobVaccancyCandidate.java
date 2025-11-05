package uk.codersden.hr.profiles;

import java.io.Serializable;

import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class JobVaccancyCandidate implements Serializable {

	private String jobVaccanyIdentifier;
	private String candidateIdentifier;

	public String getJobVaccanyIdentifier() {
		return jobVaccanyIdentifier;
	}

	public void setJobVaccanyIdentifier(String jobVaccanyIdentifier) {
		this.jobVaccanyIdentifier = jobVaccanyIdentifier;
	}

	public String getCandidateIdentifier() {
		return candidateIdentifier;
	}

	public void setCandidateIdentifier(String candidateIdentifier) {
		this.candidateIdentifier = candidateIdentifier;
	}

}