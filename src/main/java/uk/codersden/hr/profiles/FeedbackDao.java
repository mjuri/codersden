package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackDao  extends JpaRepository<Feedback, String>{
	List<Feedback> findAllByReviewerIdentifier(String reviewerIdentifier);
	List<Feedback> findAllByEmployeeIdentifier(String employeeIdentifier);
}
