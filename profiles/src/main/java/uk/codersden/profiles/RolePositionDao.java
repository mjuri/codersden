package uk.codersden.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolePositionDao extends JpaRepository<RolePosition, String>{

	List<RolePosition> findByAssignedIdentifierOrRequestorIdentifier(String assignedIdentifier, String requestorIdentifier);

}
