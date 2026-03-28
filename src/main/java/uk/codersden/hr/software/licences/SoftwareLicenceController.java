package uk.codersden.hr.software.licences;

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
@RequestMapping("/software-licence")
public class SoftwareLicenceController {
	
	@Autowired
	private SoftwareLicenceService softwareLicenceService;
	
	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> retrieveSoftwareLicencesByProfile(@PathVariable("accountIdentifier") String accountIdentifier){
		List<SoftwareLicence> list = new ArrayList<>();
		try {
			list = softwareLicenceService.findAllByAccountIdentifier(accountIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(list);
		
	}
	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createSoftwareLicence(@RequestBody SoftwareLicence softwareLicence) {
		SoftwareLicence e = this.softwareLicenceService.createSoftwareLicence(softwareLicence);
		
		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updateSoftwareLicence(@PathVariable("identifier") String softwareLicenceIdentifier, @RequestBody SoftwareLicence softwareLicence) {
		SoftwareLicence updatedSoftwareLicence;
		try {
			updatedSoftwareLicence = this.softwareLicenceService.updateSoftwareLicence(softwareLicenceIdentifier, softwareLicence);
		} catch ( NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(updatedSoftwareLicence);
	}
	
	
	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveSoftwareLicence(@PathVariable("identifier") String identifier) throws NotFoundException {
		SoftwareLicence softwareLicence;
		try {
			softwareLicence = this.softwareLicenceService.findByIdentifier(identifier);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(softwareLicence);
	}
	
	@CrossOrigin
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deleteGroup(@PathVariable("identifier") String identifier){
		SoftwareLicence softwareLicence = null;
		try {
			softwareLicence = softwareLicenceService.deleteSoftwareLicence(identifier);
			
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(softwareLicence);
	}
	
}
