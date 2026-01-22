package uk.codersden.hr.leads;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LeadDao extends JpaRepository<Lead, String> {
	List<Lead> findAllByProfileIdentifier(String id);
	
	List<Lead> findAllByProfileIdentifierOrderByModDateDesc(String id);
	
	List<Lead> findAllByAccountIdentifier(String id);
	
	List<Lead> findAllByAccountIdentifierOrderByModDateDesc(String id);

	Optional<Lead> findByFirstNameAndLastName(String firstName, String lastName);

}
