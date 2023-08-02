package uk.codersden.hr.profiles.documents;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.codersden.hr.profiles.Holiday;

public interface DocumentDao extends JpaRepository<Document, String> {

}
