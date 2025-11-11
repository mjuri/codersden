package uk.codersden.hr.profiles;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.codersden.hr.profiles.documents.Document;
import uk.codersden.hr.profiles.documents.DocumentPayload;

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
	@CrossOrigin
	@PostMapping("/profile/{profileIdentifier}/with-attachment")
	public ResponseEntity<?> createCandidateWithAttachment(@RequestParam("candidatePayload") String candidatePayload,
			@RequestParam("file") MultipartFile file, 
			@PathVariable("profileIdentifier") String profileIdentifier) {
		Candidate candidateUploaded;
		String message = "";
		try {
		  ObjectMapper mapper = new ObjectMapper();
		  Candidate candidate = mapper.readValue(candidatePayload, Candidate.class);
		  
		  candidateUploaded = service.saveCandidate(candidate, file, profileIdentifier);
	      message = "File uploaded successfully: " + file.getOriginalFilename() + "identifier: " + candidate.getIdentifier();

		}catch(Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			e.printStackTrace();
		     return ResponseEntity.internalServerError().build();
		}
	      return ResponseEntity.ok(candidateUploaded);
	}
    @CrossOrigin
    @PutMapping("/{identifier}/profile/profileIdentifier/with-attachment")
	public ResponseEntity<Candidate> updateRolePosition(@PathVariable("identifier") String identifier,@PathVariable("profileIdentifier") String profileIdentifier, @RequestParam("payload") String candidatePayload,
			@RequestParam("fileName") String fileName, @RequestParam("file") MultipartFile file)
			throws ProfileNotFoundException, JsonMappingException, JsonProcessingException {
        Candidate c = service.findCandidateByIdentifier(identifier);
        
        
		ObjectMapper mapper = new ObjectMapper();
	    Candidate updatedCandidate = mapper.readValue(candidatePayload, Candidate.class);

        
        c.setFirstName(updatedCandidate.getFirstName());
        c.setLastName(updatedCandidate.getLastName());
        c.setEmail(updatedCandidate.getEmail());
        c.setPersonalMobile(updatedCandidate.getPersonalMobile());
        c.setLinkedinProfile(updatedCandidate.getLinkedinProfile());
        c.setWebsite(updatedCandidate.getWebsite());
        c.setCurrentPosition(updatedCandidate.getCurrentPosition());
        c.setCurrentCompany(updatedCandidate.getCurrentCompany());
        c.setYearsOfExperience(updatedCandidate.getYearsOfExperience());
        c.setExpectedSalary(updatedCandidate.getExpectedSalary());
        c.setSkills(updatedCandidate.getSkills());
        c.setNotes(updatedCandidate.getNotes());

    	c = service.saveCandidate(c, file, profileIdentifier);
    	
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
