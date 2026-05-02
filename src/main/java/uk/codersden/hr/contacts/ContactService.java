package uk.codersden.hr.contacts;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.profiles.ProfileNotFoundException;

@Service
public class ContactService {
	@Autowired
	private ContactDao dao;
	

	
	public Contact createContact(Contact contact) {

		
		return this.dao.save(contact);
	}
	
	public List<Contact> findAllContactsByAccountIdentifier(String accountIdentifier){
		
		return this.dao.findAllByAccountIdentifier(accountIdentifier);
	}

	public Contact findContactByIdentifier(String identifier) throws ProfileNotFoundException {
		Optional<Contact> op = dao.findById(identifier);

		if(op.isEmpty()) {
			throw new ProfileNotFoundException();
		}

		
		return op.get();
		
	}

	public Contact updateContact(String contactIdentifier, Contact contact) throws NotFoundException {
		Optional<Contact> optional = dao.findById(contactIdentifier);
		if(optional.isEmpty()) {
			throw new NotFoundException(contactIdentifier);
		}

		Contact newContact = this.dao.save(contact);
		
		return newContact;
	}

	public Contact deleteContact(String identifier) throws NotFoundException {
		Optional<Contact> op = dao.findById(identifier);

		if(op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		Contact contact = op.get();
		
		dao.delete(contact);
		
		
		return contact;
		
	}
}
