package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	private CandidateService service;
	
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<?> createCandidate(@RequestBody Candidate candidate) {
		Candidate c;
		try {
			c = service.create(candidate);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok(c);

	}
	@PutMapping("/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> updateCandidate(@PathVariable("identifier") String identifier, @RequestBody Candidate candidate) {
		Candidate c;
		try {
			candidate.setIdentifier(identifier);
			c = service.update(candidate);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok(c);

	}
	@GetMapping("/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> retrieveCandidates(@PathVariable("identifier") String identifier) {
		Candidate c;
		try {
			c = service.findCandidateByIdentifier(identifier);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

		return ResponseEntity.ok(c);
		
	}
	
	@GetMapping("/account/{accountIdentifier}")
	@CrossOrigin
	public List<Candidate> retrieveAllCandidates(@PathVariable("accountIdentifier") String accountIdentifier){
		return service.findCandidatesByAccount(accountIdentifier);
		
	}
}
