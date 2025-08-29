package uk.codersden.hr.payroll;

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
@RequestMapping("/payroll")
public class PayrollController {

	@Autowired
	private PayrollService service;
	
	@CrossOrigin
	@GetMapping("/{accountIdentifier}")
	public ResponseEntity<?> retrieveLeadsByProfile(@PathVariable("accountIdentifier") String accountIdentifier){
		Payroll p = new Payroll();
		try {
			p = service.findPayrollByAccount(accountIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(p);
		
	}
	@PostMapping("/employeePayment")
	@CrossOrigin
	public ResponseEntity<?> createEmployeePayment(@RequestBody EmployeePayment payment) 
	{
		EmployeePayment newPayment = null;
		try {
			newPayment = this.service.createEmployeePayment(payment);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		return ResponseEntity.ok(newPayment);
	}
	
	@PutMapping("/employeePayment/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> updateEmployeePayment(@PathVariable("identifier") String identifier, @RequestBody EmployeePayment payment) 
	{
		EmployeePayment newPayment = null;
		try {
			newPayment = this.service.updateEmployeePayment(identifier, payment);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		return ResponseEntity.ok(newPayment);
	}	
}
