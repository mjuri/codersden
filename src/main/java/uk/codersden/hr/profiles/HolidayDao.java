package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HolidayDao extends JpaRepository<Holiday, String>{

	List<Holiday> findAllByProfileIdentifier(String id);
	List<Holiday> findAllByAuthorizedByAndStatus(String managerIdentifier, String status);

	@Query("SELECT h.profile FROM Holiday h WHERE CURRENT_TIMESTAMP BETWEEN h.start AND h.end AND h.status = 'APPROVED' AND h.profile.accountIdentifier = :accountIdentifier")
	List<Profile> findAllProfilesOutOfOffice(@Param("accountIdentifier")  String accountIdentifier);
}
