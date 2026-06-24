package uk.codersden.wod.achievements;

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

@RestController
@RequestMapping("/wod/achievement")
public class AchievementController {

	@Autowired
	private AchievementService service;

	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> retrieveAchievementsByProfile(@PathVariable("accountIdentifier") String accountIdentifier) {
		List<Achievement> list = new ArrayList<>();
		try {
			list = service.findAllByAccountIdentifier(accountIdentifier);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}

		return ResponseEntity.ok(list);

	}
	@CrossOrigin
	@GetMapping("/unlocked/{unlocked}/athlete/{profileIdentifier}")
	public ResponseEntity<?> retrieveAchievementsByProfileAndUnlockedStatus(@PathVariable("profileIdentifier") String profileIdentifier,
			@PathVariable("unlocked") Boolean isUnlocked) {
		List<AthleteAchievement> list = new ArrayList<>();
		try {
			list = service.findAllByAthleteAndUnlocked(profileIdentifier, isUnlocked);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}

		return ResponseEntity.ok(list);

	}
	@CrossOrigin
	@GetMapping("/type/{type}")
	public ResponseEntity<?> retrieveAchievementsByType(@PathVariable("type") String type) {
		List<Achievement> list = new ArrayList<>();
		try {
			list = service.findAllByType(type);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}

		return ResponseEntity.ok(list);

	}
	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createAchievement(@RequestBody Achievement obj) {
		Achievement e = this.service.createAchievement(obj);

		return ResponseEntity.ok(e);
	}
	
	@CrossOrigin
	@PostMapping("/{achievementIdentifier}/athlete/{profileIdentifier}")
	public ResponseEntity<?> createAthleteAchievement(@PathVariable("achievementIdentifer") String achievementIdentifer, 
			@PathVariable("profileIdentifier") String profileIdentifier, 
			@RequestBody AthleteAchievement obj) {
		
		obj.setAchievementIdentifier(achievementIdentifer);
		obj.setProfileIdentifier(profileIdentifier);
		
		AthleteAchievement e = this.service.createAthleteAchievement(obj);

		return ResponseEntity.ok(e);
	}

	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updateAchievement(@PathVariable("identifier") String identifier, @RequestBody Achievement obj) {
		Achievement updatedAchievement;
		try {
			updatedAchievement = this.service.updateAchievement(identifier, obj);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(updatedAchievement);
	}

	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveAchievement(@PathVariable("identifier") String identifier) throws NotFoundException {
		Achievement obj;
		try {
			obj = this.service.findByIdentifier(identifier);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj);
	}

	@CrossOrigin
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deleteAchievement(@PathVariable("identifier") String identifier) {
		Achievement obj = null;
		try {
			obj = this.service.deleteAchievement(identifier);

		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(obj);
	}

}
