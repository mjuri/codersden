package uk.codersden.hr.profiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
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

	public List<PerformanceReview> retrievePerfomanceReviewsByProfileIdentifier(String profileIdentifier) {
		List<PerformanceReview> reviews = performanceDao.findAllByEmployeeIdentifier(profileIdentifier);
		return reviews;
	}

	public PerformanceReview retrievePerfomanceReviewByIdentifier(String identifier) throws NotFoundException {
		Optional<PerformanceReview> op = performanceDao.findById(identifier);
		if(op.isEmpty()) {
			throw new NotFoundException();
		}
		return op.get();
	}

	public PerformanceReview updatePerformance(String performanceIdentifier, PerformanceReview performanceReview) throws NotFoundException {
		Optional<PerformanceReview> op = performanceDao.findById(performanceIdentifier);
		if(op.isEmpty()) {
			throw new NotFoundException();
		}
		performanceReview.setIdentifier(performanceIdentifier);
		return performanceDao.save(performanceReview);
	}

	public List<Goal> retrieveGoalByAccountIdentifier(String accountIdentifier) {
		return goalDao.findAllByAccountIdentifier(accountIdentifier);
	}
	//TODO Review this method.
	public Goal deleteGoal(Goal goal) {
		 goalDao.delete(goal);
		 return goal;
		
	}


}
