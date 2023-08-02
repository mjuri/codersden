package uk.codersden.profiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	@Autowired
	private AccountDao accontDao;
	
	public Account createAccount(Account account) {
		Account newAccount = this.accontDao.save(account);
		return newAccount;
	}
}
