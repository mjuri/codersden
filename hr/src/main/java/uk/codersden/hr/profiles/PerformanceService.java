package uk.codersden.hr.profiles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PerformanceService {
	
	@Autowired
	private GoalDao goalDao;
	
	@Autowired
	private PerformanceDao performanceDao;
	
	public Goal createGoal(Goal goal) {
		Goal newGoal = goalDao.save(goal);
		return newGoal;
	}

	public List<Goal> retrieveGoalsByProfileIdentifier(String profileIdentifier) {
		List<Goal> goals = goalDao.findAllByProfileIdentifier(profileIdentifier);
		return goals;
	}

	public Goal retrieveGoalByIdentifier(String goalIdentifier) throws NotFoundException {
		Optional<Goal> op = goalDao.findById(goalIdentifier);
		if(op.isEmpty()) {
			throw new NotFoundException();
		}
		return op.get();
	}

	public Goal updateGoal(String goalIdentifier, Goal goal) throws NotFoundException {
		Optional<Goal> op = goalDao.findById(goalIdentifier);
		if(op.isEmpty()) {
			throw new NotFoundException();
		}
		goal.setIdentifier(goalIdentifier);
		Goal goalUpdated = goalDao.save(goal);
		
		return goalUpdated;
	}

	public PerformanceReview createPerformance(PerformanceReview performance) {
		PerformanceReview p = performanceDao.save(performance);
		return p;
	}

}
