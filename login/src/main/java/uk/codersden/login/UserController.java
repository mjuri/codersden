package uk.codersden.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public User loginUser(@RequestBody Login login) {
		
		User u = loginService.loginUser(login);

		
			
		return u;
	}
}
