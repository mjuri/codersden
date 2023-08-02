package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDao extends JpaRepository<Event, String> {

	List<Event> findAllByProfileIdentifier(String id);
}
