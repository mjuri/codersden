package uk.codersden.hr.supporttickets;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SupportTicketDao extends JpaRepository<SupportTicket, String>{
	List<SupportTicket> findAllByProfileIdentifier(String profileIdentifier);
	List<SupportTicket> findAllByAccountIdentifier(String accountIdentifier);
}
