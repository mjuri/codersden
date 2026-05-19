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
	private ProgramService programService;
	
	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> retrieveProgramsByProfile(@PathVariable("accountIdentifier") String accountIdentifier){
		List<Program> list = new ArrayList<>();
		try {
			list = programService.findAllByAccountIdentifier(accountIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(list);
		
	}
	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createProgram(@RequestBody Program program) {
		Program e = this.programService.createProgram(program);
		
		return ResponseEntity.ok(e);
	}
	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updateProgram(@PathVariable("identifier") String programIdentifier, @RequestBody Program program) {
		Program updatedProgram;
		try {
			updatedProgram = this.programService.updateProgram(programIdentifier, program);
		} catch ( NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(updatedProgram);
	}
	
	
	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveProgram(@PathVariable("identifier") String identifier) throws NotFoundException {
		Program program;
		try {
			program = this.programService.findByIdentifier(identifier);
		} catch (NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(program);
	}
	
	@CrossOrigin
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deleteProgram(@PathVariable("identifier") String identifier){
		Program program = null;
		try {
			program = programService.deleteProgram(identifier);
			
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(program);
	}
	
}
