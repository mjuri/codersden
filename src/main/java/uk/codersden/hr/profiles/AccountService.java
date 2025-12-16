package uk.codersden.hr.profiles;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;

@Service
public class AccountService {
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private ProfileDao profileDao;
	
	public Account createAccount(Account account) {
		Account newAccount = this.accountDao.save(account);
		return newAccount;
	}
	
	public Account retrieveAccountByIdentifier(String identifier) throws NotFoundException {
		Optional<Account> op = accountDao.findById(identifier);
		if(op.isEmpty()) {
			throw new NotFoundException("Identifier: " + identifier  + " is not found");
		}
		return op.get();
	}
	
	public List<Account> retrieveAccounts(String identifier) 
			throws NotFoundException {
		Optional<Profile> op = profileDao.findById(identifier);
		// Check if this account as Root for security reasons.
		if(op.isEmpty()) {
			throw new NotFoundException("Identifier: " + identifier  + " is not found");
		}
		List<Account> accounts = accountDao.findAll();
		if(op.isEmpty()) {
			throw new NotFoundException("Identifier: " + identifier  + " is not found");
		}
		return accounts;
	}
}
