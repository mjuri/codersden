package uk.codersden.hr.leads;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LeadDao extends JpaRepository<Lead, String> {
	List<Lead> findAllByProfileIdentifier(String id);
	
	List<Lead> findAllByAccountIdentifier(String id);
}
