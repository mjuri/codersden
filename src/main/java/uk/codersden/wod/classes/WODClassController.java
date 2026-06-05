package uk.codersden.wod.classes;

import java.util.ArrayList;
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

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.profiles.ProfileNotFoundException;

@RestController
@RequestMapping("/wod/wodclass")
public class WODClassController {

	@Autowired
	private WODClassService service;

	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> retrieveWODClasssByAccount(@PathVariable("accountIdentifier") String accountIdentifier) {
		List<WODClass> list = new ArrayList<>();
		try {
			list = service.findAllByAccountIdentifier(accountIdentifier);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}

		return ResponseEntity.ok(list);

	}
	@CrossOrigin
	@GetMapping("/profile/{profileIdentifier}")
	public ResponseEntity<?> retrieveWODClasssByProfile(@PathVariable("profileIdentifier") String profileIdentifier) {
		List<WODClass> list = new ArrayList<>();
		try {
			list = service.findBookingsByProfileIdentifier(profileIdentifier);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}

		return ResponseEntity.ok(list);

	}
	@CrossOrigin
	@GetMapping("/bookings/profile/{profileIdentifier}")
	public ResponseEntity<?> retrieveWODClasssBookedByProfile(@PathVariable("profileIdentifier") String profileIdentifier) {
		List<WODClass> list = new ArrayList<>();
		try {
			list = service.findBookingsByProfileIdentifier(profileIdentifier);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}

		return ResponseEntity.ok(list);

	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createWODClass(@RequestBody WODClass obj) {
		WODClass e = this.service.createWODClass(obj);

		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@PostMapping("/all")
	public ResponseEntity<List<?>> createWODClassesFromTemplate(@RequestBody List<WODClass> classes) {
		List<WODClass> e = this.service.createWODClassFromTemplate(classes);

		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@PostMapping("/{identifier}/{profileIdentifier}")
	public ResponseEntity<?> bookWODClass(@PathVariable("identifier") String identifier, @PathVariable("profileIdentifier") String profileIdentifier) 
			throws ProfileNotFoundException, NotFoundException, MaxCapacityReachedException {
		
		WODClass e = this.service.bookWODClass(identifier, profileIdentifier);

		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@PostMapping("/cancel/{identifier}/{profileIdentifier}")
	public ResponseEntity<?> cancelWODClass(@PathVariable("identifier") String identifier, @PathVariable("profileIdentifier") String profileIdentifier) 
			throws ProfileNotFoundException, NotFoundException, MaxCapacityReachedException {
		
		WODClass e = this.service.cancelWODClass(identifier, profileIdentifier);

		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@PostMapping("/did-not-show/{identifier}/{profileIdentifier}")
	public ResponseEntity<?> didNotShowTolWODClass(@PathVariable("identifier") String identifier, @PathVariable("profileIdentifier") String profileIdentifier) 
			throws ProfileNotFoundException, NotFoundException, MaxCapacityReachedException {
		
		WODClass e = this.service.didNotShowToWODClass(identifier, profileIdentifier);

		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updateWODClass(@PathVariable("identifier") String identifier, @RequestBody WODClass obj) {
		WODClass updatedWODClass;
		try {
			updatedWODClass = this.service.updateWODClass(identifier, obj);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(updatedWODClass);
	}

	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveWODClass(@PathVariable("identifier") String identifier) throws NotFoundException {
		WODClass obj;
		try {
			obj = this.service.findByIdentifier(identifier);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj);
	}

	@CrossOrigin
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deleteWODClass(@PathVariable("identifier") String identifier) {
		WODClass obj = null;
		try {
			obj = this.service.deleteWODClass(identifier);

		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(obj);
	}

}
