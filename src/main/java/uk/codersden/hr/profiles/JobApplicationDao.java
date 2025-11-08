package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobApplicationDao  extends JpaRepository<JobApplication, JobVaccancyCandidate>{

    @Query("SELECT j FROM JobApplication j WHERE j.id.jobVaccancyIdentifier = :roleIdentifier AND status = :status")
	List<JobApplication> findByJobVaccancyIdentifierAndStatus(String roleIdentifier, String status);

}
