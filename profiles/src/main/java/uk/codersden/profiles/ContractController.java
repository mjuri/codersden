package uk.codersden.profiles;

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
@RequestMapping("/contract")
public class ContractController {
	
	@Autowired
	private ContractService contractService;

	@PostMapping("/profile/{profileIdentifier}")
	@CrossOrigin
	public ResponseEntity<?> createContract(@PathVariable("profileIdentifier") String profileIdentifier, 
			@RequestBody Contract contract) 
	{
		Contract newContract = null;
		try {
			newContract = this.contractService.createContract(profileIdentifier, contract);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		return ResponseEntity.ok(newContract);
	}
	@PutMapping("/profile/{profileIdentifier}")
	@CrossOrigin
	public ResponseEntity<?> updateContract(@PathVariable("profileIdentifier") String profileIdentifier, @RequestBody Contract contract) 
	{
		Contract updatedContract = null;
		try {
			updatedContract = this.contractService.updateContract(profileIdentifier, contract);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		return ResponseEntity.ok(updatedContract);
	}
	
	@GetMapping
	@CrossOrigin
	public ResponseEntity<?> retrieveAllContracts(){
		List<Contract> contracts = null;
		
		try {
			contracts = this.contractService.findAllContracts();
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
			
		}
		
		return ResponseEntity.ok(contracts);
		
	}
	
	@GetMapping("/{contractIdentifier}")
	@CrossOrigin
	public ResponseEntity<?> retrieveContract(@PathVariable("contractIdentifier") String contractIdentifier){
		Contract contract = null;
		try {
			contract = this.contractService.findContractByIdentifier(contractIdentifier);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
			
		}
		return ResponseEntity.ok(contract);
		
	}
}
