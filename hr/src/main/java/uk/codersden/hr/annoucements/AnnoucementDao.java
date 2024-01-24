package uk.codersden.hr.annoucements;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;




public interface AnnoucementDao extends JpaRepository<Annoucement, String> {
	List<Annoucement> findAllByProfileIdentifier(String id);
}
