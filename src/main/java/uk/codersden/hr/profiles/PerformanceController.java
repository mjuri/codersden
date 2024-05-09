package uk.codersden.hr.profiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performance")
public class PerformanceController {

	@Autowired
	private PerformanceService performanceService;
	@Autowired 
	private ProfileService profileService;
	
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
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<?> createPerformanceReview(@RequestBody PerformanceReviewPayload payload) {
	    try {
	        // Validate payload (e.g., check required fields)

	        // Fetch employee and reviewer profiles
	        Profile employee = profileService.findProfileByIdentifier(payload.getEmployee().get("value"));
	        Profile reviewer = profileService.findProfileByIdentifier(payload.getReviewer().get("value"));

	        // Fetch and validate goals
	        List<Goal> goals = new ArrayList<>();
	        for (Map<String, String> map : payload.getGoals()) {
	            Goal goal = performanceService.retrieveGoalByIdentifier(map.get("value"));
	            if (goal != null) {
	                goals.add(goal);
	            } else {
	                // Handle the case where a goal is not found
	                return ResponseEntity.badRequest().body("Invalid goal identifier: " + map.get("value"));
	            }
	        }

	        // Create and save the performance review
	        PerformanceReview performanceReview = new PerformanceReview();
	        performanceReview.setComments(payload.getComments());
	        performanceReview.setReviewDate(payload.getReviewDate());
	        performanceReview.setEmployee(employee);
	        performanceReview.setReviewer(reviewer);
	        // I don't know why I need to do this.
	        performanceReview.setEmployeeIdentifier(employee.getIdentifier());
	        performanceReview.setReviewerIdentifier(reviewer.getIdentifier());
	        
	        performanceReview.setGoals(goals);

	        performanceReview = performanceService.createPerformance(performanceReview);

	        return ResponseEntity.status(HttpStatus.CREATED).body(performanceReview);
	    } catch (ProfileNotFoundException e) {
	        // Handle the case where a profile is not found
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found");
	    } catch (Exception e) {
	        // Log and handle other exceptions
	        //logger.error("Error creating performance review", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating performance review");
	    }
	}
	@PutMapping("/goal/{goalIdentifier}")
	@CrossOrigin
	public ResponseEntity<?> createGoal(@PathVariable("goalIdentifier") String goalIdentifier, @RequestBody Goal goal) 
	{
		Goal goalUpdated = null;
		try {
			goalUpdated = performanceService.updateGoal(goalIdentifier, goal);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(goalUpdated);

		
	}
	
	@GetMapping("/profile/{profileIdentifier}")
	@CrossOrigin
	public ResponseEntity<?> retrievePerformanceReviewsByProfile(@PathVariable("profileIdentifier") String profileIdentifier) 
	{
		List<PerformanceReview> reviews = null;
		try {
			reviews = performanceService.retrievePerfomanceReviewsByProfileIdentifier(profileIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(reviews);

		
	}
	@GetMapping("/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> retrievePerformanceReview(@PathVariable("identifier") String identifier) 
	{
		PerformanceReview review = null;
		try {
			review = performanceService.retrievePerfomanceReviewByIdentifier(identifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(review);
		
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
	@GetMapping("/goal/{goalIdentifier}")
	@CrossOrigin
	public ResponseEntity<?> retrieveGoalByIdentfier(@PathVariable("goalIdentifier") String goalIdentifier) {
		Goal goal = null;
		try {
			goal = performanceService.retrieveGoalByIdentifier(goalIdentifier);
		} catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(goal);
	}
}
