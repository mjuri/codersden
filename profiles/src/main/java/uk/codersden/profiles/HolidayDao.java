package uk.codersden.profiles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayDao extends JpaRepository<Holiday, String>{
	
}
