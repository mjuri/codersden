package uk.codersden.hr.notifications;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import uk.codersden.hr.profiles.ProfileNotFoundException;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	
	@Autowired
	private NotificationService service;
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<?> createNotification(@RequestBody Notification notification) {
		Notification newNotification = service.sendNotification(notification);
		return ResponseEntity.ok(newNotification);
	}
	
	@GetMapping("/profile/{profileIdentifier}")
	@CrossOrigin
	public ResponseEntity<?> retrieveNotification(@PathVariable("profileIdentifier") String profileIdentifier) 
	{
		List<Notification> list = null;
		try {
			list = service.retrieveAllNotificationsByProfile(profileIdentifier);
		} catch (ProfileNotFoundException e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(list);
		
	}
	
	@DeleteMapping("/{identification}")
	@CrossOrigin
	public ResponseEntity<?> removeNotification(@PathVariable("identification") String identification) {
		Notification notification = null;
		try {
			notification = service.removeNotification(identification);
		} catch (NotificationNotFoundException e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(notification);
	}	
	
}
