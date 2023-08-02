package uk.codersden.login;

import java.sql.Timestamp;
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
	public AccountAccess logoutUser(String token) {
		Optional<AccountAccess> op = this.accountAccessDao.findById(token);
		if(op.isEmpty()) {	
			throw new NullPointerException();
			
		}
		AccountAccess access = op.get();
		access.setEnd(new Timestamp(System.currentTimeMillis()));
		return this.accountAccessDao.save(access);
		
	}
	public AccountAccess findAccountAccessByToken(String token) {
		Optional<AccountAccess> op = this.accountAccessDao.findById(token);
		if(op.isEmpty()) {	
			throw new NullPointerException();
			
		}
		AccountAccess access = op.get();
		return access;
	}	

}
