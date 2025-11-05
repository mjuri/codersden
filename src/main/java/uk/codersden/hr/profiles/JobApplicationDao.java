package uk.codersden.hr.profiles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationDao  extends JpaRepository<JobApplication, JobVaccancyCandidate>{

}
