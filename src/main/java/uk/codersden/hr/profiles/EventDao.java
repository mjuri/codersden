package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EventDao extends JpaRepository<Event, String> {

	List<Event> findAllByProfileIdentifierAndStatus(String profileIdentifier, String status);
}
