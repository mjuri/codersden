package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PerformanceDao extends JpaRepository<PerformanceReview, String>{

	List<PerformanceReview> findAllByEmployeeIdentifier(String profileIdentifier);

}
