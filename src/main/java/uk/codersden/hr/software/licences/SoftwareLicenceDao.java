package uk.codersden.hr.software.licences;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface SoftwareLicenceDao extends JpaRepository<SoftwareLicence, String> {
	List<SoftwareLicence> findAllByAccountIdentifier(String id);
}
