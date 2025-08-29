package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/performance/feedback")
public class FeedbackController {

	@Autowired
	private FeedbackService service;
	
	@Autowired
	private ProfileService profileService;
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<?> createFeedback(@RequestBody Feedback feedback) throws ProfileNotFoundException 
	{
        Profile employee = profileService.findProfileByIdentifier(feedback.getEmployeeIdentifier());
        Profile reviewer = profileService.findProfileByIdentifier(feedback.getReviewerIdentifier());
        feedback.setEmployee(employee);
        feedback.setReviewer(reviewer);
        
		Feedback newFeedback = null;
		try {
			newFeedback = service.createFeedback(feedback);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(newFeedback);
		
	}
	
	@PutMapping("{identifier}")
	@CrossOrigin
	public ResponseEntity<?> updateFeedback(@PathVariable("identifier") String identifier, @RequestBody Feedback feedback) throws ProfileNotFoundException 
	{
        Profile employee = profileService.findProfileByIdentifier(feedback.getEmployeeIdentifier());
        Profile reviewer = profileService.findProfileByIdentifier(feedback.getReviewerIdentifier());
        feedback.setEmployee(employee);
        feedback.setReviewer(reviewer);
        
		Feedback feedbackUpdated = null;
		try {
			feedbackUpdated = service.updateFeedback(feedback);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(feedbackUpdated);
		
	}
	
	@GetMapping("/profile/{profileIdentifier}")
	@CrossOrigin
	public ResponseEntity<?> retrieveFeedbacks(@PathVariable("profileIdentifier") String profileIdentifier) 
	{
		List<Feedback> feedbacks = null;
		try {
			feedbacks = service.retrieveAllFeedbacksByProfileIdentifier(profileIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(feedbacks);

		
	}
	@GetMapping("/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> retrieveFeedbackByIdentfier(@PathVariable("identifier") String identifier) {
		Feedback feedback = null;
		try {
			feedback = service.retrieveFeedbackByIdentifier(identifier);
		} catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(feedback);
	}
	
	@DeleteMapping("/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> deleteFeedback(@PathVariable("identifier") String identifier) {
		Feedback feedback = null;
		try {
			feedback = service.deleteFeedback(identifier);
		} catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		return ResponseEntity.ok(feedback);
	}
}
