package uk.codersden.hr.login;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import uk.codersden.hr.profiles.User;

@RestController("/login")
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<?> loginUser(@RequestBody User login) {
		
		AccountAccess access;
		try {
			access = loginService.loginUser(login);
		} catch (NotFoundUserException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(login);

		} catch (PasswordsDoesNotMatchException e) {
			
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.valueOf(403)).body(login);
		}
		return ResponseEntity.ok(access);
		
	}
	@PostMapping("/logout")
	@CrossOrigin
	public ResponseEntity<?> logoutUser(@RequestBody String token){
		AccountAccess access = this.loginService.logoutUser(token);
		return ResponseEntity.ok(access);
	}
	
	@GetMapping("/access/{token}")
	@CrossOrigin
	public ResponseEntity<?> retrieveAccessByToken(@PathVariable("token") String token){
		AccountAccess access = this.loginService.findAccountAccessByToken(token);
		return ResponseEntity.ok(access);
	}
}
