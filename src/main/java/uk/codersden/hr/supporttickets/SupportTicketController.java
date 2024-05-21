package uk.codersden.hr.supporttickets;

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


@RestController
@RequestMapping("/support/ticket")
public class SupportTicketController {
	@Autowired
	private SupportTicketService supportService;
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<?> createSupportTicket(@RequestBody SupportTicket ticket) 
	{
		SupportTicket newSupportTicket = null;
		try {
			newSupportTicket = this.supportService.createSupportTicket(ticket);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		return ResponseEntity.ok(newSupportTicket);
	}
	@PutMapping("/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> updateSupportTicket(@PathVariable("identifier") String identifier, @RequestBody SupportTicket ticket) 
	{
		SupportTicket updatedSupportTicket = null;
		try {
			updatedSupportTicket = this.supportService.updateSupportTicket(identifier, ticket);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		return ResponseEntity.ok(updatedSupportTicket);
	}
	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> retrieveSupportTicketsByProfile(@PathVariable("accountIdentifier") String accountIdentifier){
		List<SupportTicket> list = new ArrayList<>();
		try {
			list = supportService.findAllSupportTicketsByAccountIdentifier(accountIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(list);
		
	}

	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveSupportTicket(@PathVariable("identifier") String identifier){
		SupportTicket g = null;
		try {
			g = supportService.findSupportTicketByIdentifier(identifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(g);
		
	}
	@CrossOrigin
	@DeleteMapping("/{identifier}")
	public ResponseEntity<?> deleteSupportTicket(@PathVariable("identifier") String identifier){
		SupportTicket g = null;
		try {
			g = supportService.deleteSupportTicket(identifier);
			
		}catch(NotFoundException e) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(g);
	}
}
