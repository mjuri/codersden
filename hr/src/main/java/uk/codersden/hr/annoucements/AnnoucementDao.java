package uk.codersden.hr.annoucements;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uk.codersden.hr.profiles.documents.Document;




public interface AnnoucementDao extends JpaRepository<Annoucement, String> {
    @Query("SELECT d FROM Annoucement d JOIN d.audience p WHERE p.identifier = :profileIdentifier")
    List<Annoucement> findAllSharedWithUser(@Param("profileIdentifier") String profileIdentifier);
	List<Annoucement> findAllByProfileIdentifier(String id);
}
