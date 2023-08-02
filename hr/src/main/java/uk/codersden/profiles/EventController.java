package uk.codersden.profiles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/event")
public class EventController {
	@Autowired
	private EventService eventService;
	
	@CrossOrigin
	@GetMapping("/profile/{profileIdentifier}")
	public ResponseEntity<?> retrieveEventsByProfile(@PathVariable("profileIdentifier") String profileIdentifier){
		List<Event> list = new ArrayList<>();
		try {
			list = eventService.findAllEventsByProfileIdentifier(profileIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(list);
		
	}
	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createEvent(@RequestBody Event event) {
		Event e = this.eventService.createEvent(event);
		
		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updateEvent(@PathVariable("identifier") String eventIdentifier, @RequestBody Event event) {
		Event e;
		try {
			e = this.eventService.updateEvent(eventIdentifier, event);
		} catch ( EventNotFoundException | ProfileNotFoundException e1) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveEvent(@PathVariable("identifier") String eventIdentifier) {
		Event e;
		try {
			e = this.eventService.findEventIdentifier(eventIdentifier);
		} catch (EventNotFoundException e1) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(e);
	}
	
	
}
