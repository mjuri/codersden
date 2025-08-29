package uk.codersden.hr.profiles;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import uk.codersden.hr.notifications.Notification;
import uk.codersden.hr.notifications.NotificationService;

@Service
public class FeedbackService {
	
	@Autowired
	private FeedbackDao dao;
	
	@Autowired
	private NotificationService notificationService;
	
	public Feedback createFeedback(Feedback feedback) {
		Notification n = buildNotification(feedback);
		
		notificationService.sendNotification(n);
		return dao.save(feedback);
	}
	private Notification buildNotification(Feedback feedback) {
		Notification n = new Notification();
		
		n.setOwner(feedback.getEmployee());
		n.setMessage(" is waiting for your feedback");
		n.setTime(new Timestamp(System.currentTimeMillis() ));
		n.setProfile(feedback.getReviewer());
		n.setProfileIdentifier(feedback.getReviewer().getIdentifier());
		n.setOwnerIdentifier(feedback.getEmployee().getIdentifier());
		
		return n;

	}
	public List<Feedback> retrieveAllFeedbacksByProfileIdentifier(String profileIdentifier) {
		List<Feedback> feedbacksByEmployee =  dao.findAllByEmployeeIdentifier(profileIdentifier);
		List<Feedback> feedbacksByReviewer = dao.findAllByReviewerIdentifier(profileIdentifier);
		
        Set<Feedback> mergedSet = new HashSet<>(feedbacksByEmployee);
        mergedSet.addAll(feedbacksByReviewer);
        
        List<Feedback> feedbacks = new ArrayList<>(mergedSet);
        
        return feedbacks;
	}
	
	public Feedback retrieveFeedbackByIdentifier(String feedbackIdentifier) throws NotFoundException {
		Optional<Feedback> op = dao.findById(feedbackIdentifier);
		if(op.isEmpty()) {
			throw new NotFoundException();
		}
		
		return op.get();
	}
	
	public Feedback updateFeedback(Feedback feedback) throws NotFoundException {
		Optional<Feedback> op = dao.findById(feedback.getIdentifier());
		if(op.isEmpty()) {
			throw new NotFoundException();
		}
		
		Feedback oldVersionFeedback = op.get();
		// here I need to update
		
		return dao.save(feedback);
	}
	public Feedback deleteFeedback(String identifier) throws NotFoundException {
		Optional<Feedback> op = dao.findById(identifier);
		if(op.isEmpty()) {
			throw new NotFoundException();
		}
		
		Feedback feedback = op.get();
		feedback.setDeleted(true);
		
		return dao.save(feedback);
	}

}
