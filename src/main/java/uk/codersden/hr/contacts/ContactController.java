package uk.codersden.hr.contacts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.contacts.Contact;
import uk.codersden.hr.contacts.ContactService;

@RestController
@RequestMapping("/contacts")
public class ContactController {
	@Autowired
	private ContactService contactService;
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<?> createContact(@RequestBody Contact contact) 
	{
		Contact newContact = null;
		try {
			newContact = this.contactService.createContact(contact);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		return ResponseEntity.ok(newContact);
	}
	@PutMapping("/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> updateContact(@PathVariable("identifier") String identifier, @RequestBody Contact contact) 
	{
		Contact updatedContact = null;
		try {
			updatedContact = this.contactService.updateContact(identifier, contact);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		return ResponseEntity.ok(updatedContact);
	}
	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> retrieveContactsByProfile(@PathVariable("accountIdentifier") String accountIdentifier){
		List<Contact> list = new ArrayList<>();
		try {
			list = contactService.findAllContactsByAccountIdentifier(accountIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(list);
		
	}

	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveContact(@PathVariable("identifier") String identifier){
		Contact g = null;
		try {
			g = contactService.findContactByIdentifier(identifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(g);
		
	}
	@CrossOrigin
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deleteContact(@PathVariable("identifier") String identifier){
		Contact g = null;
		try {
			g = contactService.deleteContact(identifier);
			
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(g);
	}
}
