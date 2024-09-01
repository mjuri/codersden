package uk.codersden.hr.leads;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.profiles.Account;
import uk.codersden.hr.profiles.AccountDao;
import uk.codersden.hr.profiles.AccountNotFoundException;
import uk.codersden.hr.profiles.Profile;
import uk.codersden.hr.profiles.ProfileDao;
import uk.codersden.hr.profiles.ProfileNotFoundException;

@Service
public class LeadService {

	@Autowired
	private LeadDao dao;

	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private AccountDao accountDao;
	
	public Lead createLead(Lead lead) {
		
		return dao.save(lead);
	}

	public Lead updateLead(String leadIdentifier, Lead lead) throws LeadNotFoundException {
		Optional<Lead> op = dao.findById(leadIdentifier);
		if(op.isEmpty()){
			throw new LeadNotFoundException(leadIdentifier + " not found");
		}
		lead.setIdentifier(leadIdentifier);
		return dao.save(lead);
	}

	public List<Lead> findAllLeadsByProfileIdentifier(String profileIdentifier) throws ProfileNotFoundException {
		Optional<Profile> op = profileDao.findById(profileIdentifier);
		if(op.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		
		List<Lead> list = dao.findAllByProfileIdentifier(profileIdentifier);
		
		return list;
	}
	public List<Lead> findAllLeadsByAccountIdentifier(String accountIdentifier) throws AccountNotFoundException {
		Optional<Account> op = accountDao.findById(accountIdentifier);
		if(op.isEmpty()) {
			throw new AccountNotFoundException(accountIdentifier + " not found");
		}
		
		List<Lead> list = dao.findAllByAccountIdentifier(accountIdentifier);
		
		return list;
	}
	public Lead findByLeadIdentifier(String leadIdentifier) throws LeadNotFoundException {
		Optional<Lead> op = dao.findById(leadIdentifier);
		if(op.isEmpty()) {
			throw new LeadNotFoundException(leadIdentifier + " not found");
		}
		return op.get();
	}

}
