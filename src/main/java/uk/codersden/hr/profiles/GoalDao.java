package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalDao  extends JpaRepository<Goal, String>{
	List<Goal> findAllByProfileIdentifier(String profileIdentifier);
}
