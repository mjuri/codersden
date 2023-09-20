package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PerformanceService {
	
	@Autowired
	private GoalDao goalDao;
	
	public Goal createGoal(Goal goal) {
		Goal newGoal = goalDao.save(goal);
		return newGoal;
	}

	public List<Goal> retrieveGoalsByProfileIdentifier(String profileIdentifier) {
		List<Goal> goals = goalDao.findAllByProfileIdentifier(profileIdentifier);
		return goals;
	}

}
