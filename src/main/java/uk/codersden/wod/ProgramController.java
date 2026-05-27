package uk.codersden.wod;

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
@RequestMapping("/wod/program")
public class ProgramController {

	@Autowired
	private ProgramService service;

	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> retrieveProgramsByProfile(@PathVariable("accountIdentifier") String accountIdentifier) {
		List<Program> list = new ArrayList<>();
		try {
			list = service.findAllByAccountIdentifier(accountIdentifier);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}

		return ResponseEntity.ok(list);

	}

	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}/date/{date}/type/{type}")
	public ResponseEntity<?> retrieveProgramsByProfile(@PathVariable("accountIdentifier") String accountIdentifier,
			@PathVariable("date") String date, @PathVariable("type") String type) {

		Program obj;
		try {
			obj = this.service.findByAccountIdentifierAndDateAndType(accountIdentifier, date, type);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj);

	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createProgram(@RequestBody Program obj) {
		Program e = this.service.createProgram(obj);

		return ResponseEntity.ok(e);
	}

	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updateProgram(@PathVariable("identifier") String identifier, @RequestBody Program obj) {
		Program updatedProgram;
		try {
			updatedProgram = this.service.updateProgram(identifier, obj);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(updatedProgram);
	}

	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveProgram(@PathVariable("identifier") String identifier) throws NotFoundException {
		Program obj;
		try {
			obj = this.service.findByIdentifier(identifier);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(obj);
	}

	@CrossOrigin
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deleteProgram(@PathVariable("identifier") String identifier) {
		Program obj = null;
		try {
			obj = this.service.deleteProgram(identifier);

		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(obj);
	}

}
