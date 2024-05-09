package uk.codersden.hr.profiles;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ToDoDao extends JpaRepository<ToDo, String>{
	public List<ToDo> findAllByProfile(Profile profile);
	
	
	@Query("SELECT t FROM ToDo t WHERE t.profile.identifier = :profileIdentifier AND t.done = false OR (t.profile.identifier = :profileIdentifier AND t.done = true AND t.modDate > :xDaysAgo)")
    List<ToDo> findAllByProfileAndStatus(@Param("profileIdentifier") String profileIdentifier, @Param("xDaysAgo") Date xDaysAgo);
}
