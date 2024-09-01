package uk.codersden.hr.salarylevels;

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

import uk.codersden.hr.supporttickets.SupportTicket;

@RestController
@RequestMapping("/salarylevel")
public class SalaryLevelController {
	
	@Autowired
	private SalaryLevelService salaryLevelService;
	
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<?> createSalaryLevel(@RequestBody SalaryLevel salaryLevel) 
	{
		SalaryLevel newSalaryLevel = null;
		try {
			newSalaryLevel = this.salaryLevelService.createSalaryLevel(salaryLevel);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		return ResponseEntity.ok(newSalaryLevel);
	}
	@PutMapping("/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> updateSalaryLevel(@PathVariable("identifier") String identifier, @RequestBody SalaryLevel salaryLevel) 
	{
		SalaryLevel updatedSalaryLevel = null;
		try {
			updatedSalaryLevel = this.salaryLevelService.updateSalaryLevel(identifier, salaryLevel);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		return ResponseEntity.ok(updatedSalaryLevel);
	}
	
	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> retrieveSupportTicketsByProfile(@PathVariable("accountIdentifier") String accountIdentifier){
		List<SalaryLevel> list = new ArrayList<>();
		try {
			list = salaryLevelService.findAllSalaryLevelsByAccountIdentifier(accountIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(list);
		
	}
}
