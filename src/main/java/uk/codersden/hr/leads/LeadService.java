package uk.codersden.hr.leads;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.groups.Group;
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
	private LeadContactHistoryDao contactHistoryDao;
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private AccountDao accountDao;
	
	public Lead createLead(Lead lead) {
		lead.setModDate(new Timestamp(System.currentTimeMillis()));
		return dao.save(lead);
	}

	public Lead updateLead(String leadIdentifier, Lead lead) throws LeadNotFoundException {
		Optional<Lead> op = dao.findById(leadIdentifier);
		if(op.isEmpty()){
			throw new LeadNotFoundException(leadIdentifier + " not found");
		}
		lead.setIdentifier(leadIdentifier);
		lead.setModDate(new Timestamp(System.currentTimeMillis()));
		return dao.save(lead);
	}

	public List<Lead> findAllLeadsByProfileIdentifier(String profileIdentifier) throws ProfileNotFoundException {
		Optional<Profile> op = profileDao.findById(profileIdentifier);
		if(op.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		
		List<Lead> list = dao.findAllByProfileIdentifierOrderByModDateDesc(profileIdentifier);
		
		return list;
	}
	public List<Lead> findAllLeadsByAccountIdentifier(String accountIdentifier) throws AccountNotFoundException {
		Optional<Account> op = accountDao.findById(accountIdentifier);
		if(op.isEmpty()) {
			throw new AccountNotFoundException(accountIdentifier + " not found");
		}
		
		List<Lead> list = dao.findAllByAccountIdentifierOrderByModDateDesc(accountIdentifier);
		
		return list;
	}
	public Lead findByLeadIdentifier(String leadIdentifier) throws LeadNotFoundException {
		Optional<Lead> op = dao.findById(leadIdentifier);
		if(op.isEmpty()) {
			throw new LeadNotFoundException(leadIdentifier + " not found");
		}
		return op.get();
	}
	
	public Lead deleteLead(String identifier) throws NotFoundException {
		Optional<Lead> op = dao.findById(identifier);

		if(op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		Lead group = op.get();
		//TODO Delete also LeadCommentHistory
		dao.delete(group);
		
		
		return group;
	}
	/** Lead Contact History methods **/
	public LeadContactHistory findContactHistoryByIdentifier(String identifier) throws LeadNotFoundException {
		Optional<LeadContactHistory> op = contactHistoryDao.findById(identifier);
		if(op.isEmpty()) {
			throw new LeadNotFoundException("lead contact history" + identifier + " not found");
		}
		return op.get();
		
	}
	public LeadContactHistory createContactHistory(LeadContactHistory contactHistory) {
		
		return contactHistoryDao.save(contactHistory);
	}

	public LeadContactHistory updateContactHistory(String identifier, LeadContactHistory contactHistory) throws LeadNotFoundException {
		Optional<LeadContactHistory> op = contactHistoryDao.findById(identifier);
		if(op.isEmpty()){
			throw new LeadNotFoundException("lead contact history" + identifier + " not found");
		}
		contactHistory.setIdentifier(identifier);
		return contactHistoryDao.save(contactHistory);
	}

	public List<LeadContactHistory> findAllContactHistoryByLeadIdentifier(String leadIdentifier) throws ProfileNotFoundException {
		Optional<Lead> op = dao.findById(leadIdentifier);
		if(op.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		
		List<LeadContactHistory> list = contactHistoryDao.findAllByLeadIdentifier(leadIdentifier);
		
		return list;
	}	
	public LeadContactHistory deleteContactHistory(String identifier) throws NotFoundException {
		Optional<LeadContactHistory> op = contactHistoryDao.findById(identifier);

		if(op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		LeadContactHistory contactHistory = op.get();
		
		contactHistoryDao.delete(contactHistory);
		
		
		return contactHistory;
	}

	public LeadFound findLeadByName(String firstName, String lastName) {
		Optional<Lead> op = dao.findByFirstNameAndLastName(firstName, lastName);
		LeadFound leadFound = new LeadFound();
		leadFound.setLeadFound(false);
		if(!op.isEmpty()) {
			Lead lead = op.get();
			leadFound.setLeadFound(true);
			leadFound.setLead(lead);
			leadFound.setIdentifier(lead.getIdentifier());
			
			List<LeadContactHistory> contacts = contactHistoryDao.findAllByLeadIdentifier(lead.getIdentifier());
			leadFound.setAttempts(contacts.size());
		}
		return leadFound;
	}
}
