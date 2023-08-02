package uk.codersden.profiles.documents;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.codersden.profiles.Holiday;

public interface DocumentDao extends JpaRepository<Document, String> {

}
