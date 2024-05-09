package uk.codersden.hr.profiles.documents;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import uk.codersden.hr.profiles.Holiday;
import uk.codersden.hr.profiles.Profile;

public interface DocumentDao extends JpaRepository<Document, String> {
    @Query("SELECT d FROM Document d JOIN d.sharedWith p WHERE p.identifier = :profileIdentifier")
    List<Document> findAllSharedWithUser(@Param("profileIdentifier") String profileIdentifier);
    List<Document> findAllByProfile(Profile p);
}
