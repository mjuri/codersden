package uk.codersden.hr.leads;

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

import uk.codersden.hr.profiles.ProfileNotFoundException;

@RestController
@RequestMapping("/lead")
public class LeadController {

	@Autowired
	private LeadService leadService;
	
	@CrossOrigin
	@GetMapping("/profile/{profileIdentifier}")
	public ResponseEntity<?> retrieveLeadsByProfile(@PathVariable("profileIdentifier") String profileIdentifier){
		List<Lead> list = new ArrayList<>();
		try {
			list = leadService.findAllLeadsByProfileIdentifier(profileIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(list);
		
	}
	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createLead(@RequestBody Lead lead) {
		Lead e = this.leadService.createLead(lead);
		
		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updateLead(@PathVariable("identifier") String leadIdentifier, @RequestBody Lead lead) {
		Lead updatedLead;
		try {
			updatedLead = this.leadService.updateLead(leadIdentifier, lead);
		} catch ( LeadNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(updatedLead);
	}
	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveLead(@PathVariable("identifier") String identifier) {
		Lead e;
		try {
			e = this.leadService.findByLeadIdentifier(identifier);
		} catch (LeadNotFoundException e1) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(e);
	}
}
