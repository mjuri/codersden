package uk.codersden.wod;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramDao extends JpaRepository<Program, String>{
	List<Program> findAllByAccountIdentifier(String id);
}
