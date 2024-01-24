package uk.codersden.hr.annoucements;

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
@RequestMapping("/annoucement")
public class AnnoucementController {

	@Autowired
	private AnnoucementService annoucementService;
	
	@CrossOrigin
	@GetMapping("/profile/{profileIdentifier}")
	public ResponseEntity<?> retrieveAnnoucementsByProfile(@PathVariable("profileIdentifier") String profileIdentifier){
		List<Annoucement> list = new ArrayList<>();
		try {
			list = annoucementService.findAllAnnoucementsByProfileIdentifier(profileIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(list);
		
	}
	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createAnnoucement(@RequestBody Annoucement annoucement) {
		Annoucement e = this.annoucementService.createAnnoucement(annoucement);
		
		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updateAnnoucement(@PathVariable("identifier") String annoucementIdentifier, @RequestBody Annoucement annoucement) {
		Annoucement updatedAnnoucement;
		try {
			updatedAnnoucement = this.annoucementService.updateAnnoucement(annoucementIdentifier, annoucement);
		} catch ( AnnoucementNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(updatedAnnoucement);
	}
	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveAnnoucement(@PathVariable("identifier") String identifier) {
		Annoucement e;
		try {
			e = this.annoucementService.findByAnnoucementIdentifier(identifier);
		} catch (AnnoucementNotFoundException e1) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(e);
	}
	
}
