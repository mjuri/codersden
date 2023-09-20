package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performance")
public class PerformanceController {

	@Autowired
	private PerformanceService performanceService;
	
	@PostMapping("/goal")
	@CrossOrigin
	public ResponseEntity<?> createGoal(@RequestBody Goal goal) 
	{
		Goal newGoal = null;
		try {
			newGoal = performanceService.createGoal(goal);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(newGoal);

		
	}
	@GetMapping("/goal/profile/{profileIdentifier}")
	@CrossOrigin
	public ResponseEntity<?> retrieveGoals(@PathVariable("profileIdentifier") String profileIdentifier) 
	{
		List<Goal> goals = null;
		try {
			goals = performanceService.retrieveGoalsByProfileIdentifier(profileIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(goals);

		
	}
}
