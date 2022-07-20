package uk.codersden.login;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
	@Autowired
	private AccountAccessDao accountAccessDao;
	
	@Autowired
	private LoginDao loginDao;
	public AccountAccess loginUser(Login login) throws NotFoundUserException, PasswordsDoesNotMatchException {
		Optional<Login> op = this.loginDao.findById(login.getUserName());
		List<Login> users = this.loginDao.findAll();
		if(op.isEmpty()) {
			throw new NotFoundUserException(login.getUserName());
		}
		Login l = op.get();
		if(!l.getPassword().equals(login.getPassword() )){
			throw new PasswordsDoesNotMatchException(login.getUserName());
		}
		AccountAccess access = this.accountAccessDao.save(new AccountAccess(login.getUserName()));
		
		return access;
	}

}
