package uk.codersden.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayDao extends JpaRepository<Holiday, String>{

	List<Holiday> findAllByProfileIdentifier(String id);

	
}
