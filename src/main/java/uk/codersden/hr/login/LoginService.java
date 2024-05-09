package uk.codersden.hr.login;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.profiles.Profile;
import uk.codersden.hr.profiles.ProfileDao;
import uk.codersden.hr.profiles.User;
@Service
public class LoginService {
	@Autowired
	private AccountAccessDao accountAccessDao;
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private LoginDao loginDao;
	public AccountAccess loginUser(User login) throws NotFoundUserException, PasswordsDoesNotMatchException {
		Optional<User> op = this.loginDao.findById(login.getUserName());
		if(op.isEmpty()) {
			throw new NotFoundUserException(login.getUserName());
		}
		User l = op.get();
		if(!l.getPassword().equals(login.getPassword() )){
			throw new PasswordsDoesNotMatchException(login.getUserName());
		}
		Optional<Profile> opProfile = this.profileDao.findByEmail(login.getUserName());
		
		//TODO Needs re-factoring...
		if(opProfile.isEmpty()) {
			throw new NotFoundUserException(login.getUserName());
		}
		Profile profile = opProfile.get();
		profile.setOnline(true);
		profileDao.save(profile);
		AccountAccess access = accountAccessDao.save(new AccountAccess(login.getUserName()));

		return access;
	}
	public AccountAccess logoutUser(String token) throws NotFoundUserException {
		Optional<AccountAccess> op = this.accountAccessDao.findById(token);
		if(op.isEmpty()) {	
			throw new NullPointerException();
			
		}
		AccountAccess access = op.get();
		access.setEnd(new Timestamp(System.currentTimeMillis()));
		Optional<Profile> opProfile = this.profileDao.findByEmail(access.getUserName());
		if(opProfile.isEmpty()) {
			throw new NotFoundUserException(access.getUserName());
		}
		Profile profile = opProfile.get();
		profile.setOnline(false);
		profileDao.save(profile);
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
