package uk.codersden.wod.pr;

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
@RequestMapping("/wod/personal-record")
public class PersonalRecordController {

	@Autowired
	private PersonalRecordService service;

	@CrossOrigin
	@GetMapping("/athlete/{profileIdentifier}")
	public ResponseEntity<?> retrievePersonalRecordsByProfile(@PathVariable("profileIdentifier") String profileIdentifier) {
		List<PersonalRecord> list = new ArrayList<>();
		try {
			list = service.findAllByProfileIdentifier(profileIdentifier);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}

		return ResponseEntity.ok(list);

	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createPersonalRecord(@RequestBody PersonalRecord obj) {

		PersonalRecord e = this.service.createPersonalRecord(obj);

		return ResponseEntity.ok(e);
	}

	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updatePersonalRecord(@PathVariable("identifier") String identifier, @RequestBody PersonalRecord obj) {
		PersonalRecord updatedPersonalRecord;
		try {
			updatedPersonalRecord = this.service.updatePersonalRecord(identifier, obj);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(updatedPersonalRecord);
	}

	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrievePersonalRecord(@PathVariable("identifier") String identifier) throws NotFoundException {
		PersonalRecord obj;
		try {
			obj = this.service.findByIdentifier(identifier);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj);
	}

	@CrossOrigin
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deletePersonalRecord(@PathVariable("identifier") String identifier) {
		PersonalRecord obj = null;
		try {
			obj = this.service.deletePersonalRecord(identifier);

		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(obj);
	}

}
