package uk.codersden.hr.profiles;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;



@Entity
@Table(name="job_applications")
public class JobApplication {
	@EmbeddedId
	private JobVaccancyCandidate id = new JobVaccancyCandidate();
	
	@Column(nullable = false)
	private String status;
	

	public JobVaccancyCandidate getId() {
		return id;
	}


	public void setId(JobVaccancyCandidate id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
