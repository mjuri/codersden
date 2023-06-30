package uk.codersden.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDao extends JpaRepository<Event, String> {

	List<Holiday> findAllByProfileIdentifier(String id);
}
