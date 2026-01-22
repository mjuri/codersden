package uk.codersden.hr.leads;

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
@RequestMapping("/lead/history")
public class LeadContactHistoryController {

	@Autowired
	private LeadService leadService;
	
	@CrossOrigin
	@GetMapping("/{leadIdentifier}/all")
	public ResponseEntity<?> retrieveLeadsByProfile(@PathVariable("leadIdentifier") String leadIdentifier){
		List<LeadContactHistory> list = new ArrayList<>();
		try {
			list = leadService.findAllContactHistoryByLeadIdentifier(leadIdentifier);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(list);
		
	}
	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createLeadContactHistory(@RequestBody LeadContactHistory contactHistory) {
		LeadContactHistory e = this.leadService.createContactHistory(contactHistory);
		
		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updateLead(@PathVariable("identifier") String identifier, @RequestBody LeadContactHistory contactHistory) {
		LeadContactHistory updatedLeadHistory;
		try {
			updatedLeadHistory = this.leadService.updateContactHistory(identifier, contactHistory);
		} catch ( LeadNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(updatedLeadHistory);
	}
	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveContactHistory(@PathVariable("identifier") String identifier) {
		LeadContactHistory e;
		try {
			e = this.leadService.findContactHistoryByIdentifier(identifier);
		} catch (LeadNotFoundException e1) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deleteContactHistory(@PathVariable("identifier") String identifier){
		LeadContactHistory contactHistory = null;
		try {
			contactHistory = leadService.deleteContactHistory(identifier);
			
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(contactHistory);
	}
}
