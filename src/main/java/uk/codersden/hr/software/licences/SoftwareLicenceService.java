package uk.codersden.hr.software.licences;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.leads.Lead;
import uk.codersden.hr.leads.LeadNotFoundException;
import uk.codersden.hr.profiles.Account;
import uk.codersden.hr.profiles.AccountDao;
import uk.codersden.hr.profiles.AccountNotFoundException;

@Service
public class SoftwareLicenceService {

	@Autowired
	private SoftwareLicenceDao dao;
	
	@Autowired
	private AccountDao accountDao;
	
	public List<SoftwareLicence> findAllByAccountIdentifier(String accountIdentifier) throws AccountNotFoundException {
		Optional<Account> op = accountDao.findById(accountIdentifier);
		if(op.isEmpty()) {
			throw new AccountNotFoundException(accountIdentifier + " not found");
		}
		
		List<SoftwareLicence> list = dao.findAllByAccountIdentifier(accountIdentifier);
		
		return list;
	}
	public SoftwareLicence createSoftwareLicence(SoftwareLicence softwareLicence) {
		return dao.save(softwareLicence);
	}

	public SoftwareLicence updateSoftwareLicence(String softwareLicenceIdentifier, SoftwareLicence softwareLicence) throws NotFoundException {
		Optional<SoftwareLicence> op = dao.findById(softwareLicenceIdentifier);
		if(op.isEmpty()){
			throw new NotFoundException(softwareLicenceIdentifier + " not found");
		}
		softwareLicence.setIdentifier(softwareLicenceIdentifier);
		
		return dao.save(softwareLicence);
	}
	
	public SoftwareLicence findByIdentifier(String identifier) throws NotFoundException {
		Optional<SoftwareLicence> op = dao.findById(identifier);
		if(op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		return op.get();
	}
	
	public SoftwareLicence deleteSoftwareLicence(String identifier) throws NotFoundException {
		Optional<SoftwareLicence> op = dao.findById(identifier);

		if(op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		SoftwareLicence softwareLicence = op.get();
		
		dao.delete(softwareLicence);
		
		
		return softwareLicence;
	}
}
