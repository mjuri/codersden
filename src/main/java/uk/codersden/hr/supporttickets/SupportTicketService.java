package uk.codersden.hr.supporttickets;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;

@Service
public class SupportTicketService {

	@Autowired
	private SupportTicketDao dao;
	public SupportTicket createSupportTicket(SupportTicket ticket) {
		ticket.setStatus("REQUESTED");
		Timestamp ts = new Timestamp((new Date()).getTime());
		ticket.setDateCreated(ts);
		ticket.setModDate(ts);
		return this.dao.save(ticket);
	}

	public SupportTicket updateSupportTicket(String identifier, SupportTicket ticket) throws NotFoundException {
		Optional<SupportTicket> op = this.dao.findById(identifier);
		if(op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		SupportTicket oldTicket = op.get();
		ticket.setIdentifier(identifier);
		ticket.setProfile(oldTicket.getProfile());
		// TODO add last mod
		return this.dao.save(ticket);
	}

	public List<SupportTicket> findAllSupportTicketsByAccountIdentifier(String accountIdentifier) {
		return this.dao.findAllByAccountIdentifier(accountIdentifier);
	}

	public List<SupportTicket> findAllSupportTicketsByProfileIdentifier(String profileIdentifier) {
		return this.dao.findAllByProfileIdentifier(profileIdentifier);
	}
	
	public SupportTicket findSupportTicketByIdentifier(String identifier) throws NotFoundException {
		Optional<SupportTicket> op = this.dao.findById(identifier);
		if(op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		return op.get();
	}

	public SupportTicket deleteSupportTicket(String identifier) throws NotFoundException{
		Optional<SupportTicket> op = this.dao.findById(identifier);
		if(op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		SupportTicket ticket = op.get();
		this.dao.delete(ticket);
		return ticket;
	}

}
