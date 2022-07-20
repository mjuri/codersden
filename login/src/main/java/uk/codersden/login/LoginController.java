package uk.codersden.login;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	@CrossOrigin
	public ResponseEntity<?> loginUser(@RequestBody Login login) {
		
		AccountAccess access;
		try {
			access = loginService.loginUser(login);
		} catch (NotFoundUserException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(login);

		} catch (PasswordsDoesNotMatchException e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(login);
		}
		return ResponseEntity.ok(access);
		
	}
	@PostMapping("/logout")
	@CrossOrigin
	public ResponseEntity<?> logoutUser(@RequestBody String token){
		AccountAccess access = this.loginService.logoutUser(token);
		return ResponseEntity.ok(access);
	}
}
