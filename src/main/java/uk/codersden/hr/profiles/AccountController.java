package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService accountService;

	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveAccountByIdentifier(@PathVariable("identifier") String identifier){
		Account account = null;
		try {
			account = accountService.retrieveAccountByIdentifier(identifier);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(account);
		
	}
	
	@CrossOrigin
	@GetMapping("/profile/{identifier}")
	public ResponseEntity<?> retrieveAccounts(@PathVariable("identifier") String identifier){
		List<Account> accounts = null;
		try {
			accounts = accountService.retrieveAccounts(identifier);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(accounts);
		
	}
}
