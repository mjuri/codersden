package uk.codersden.login;

import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class LoginService {

	public User loginUser(Login login) {
		User user = new User();
		user.setUserName(login.getUserName());
		user.setPassword(login.getPassword());
		user.setFirstName("Mariano");
		user.setLastName("Juri");
		user.setDOB(new Date());
		
		
		return user;
	}

}
