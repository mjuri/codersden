package uk.codersden.hr.profiles;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidateDao extends JpaRepository<Candidate, String>{

    @Query("SELECT p FROM Profile p WHERE p.accountIdentifier = :accountIdentifier AND deleted = false AND type='candidate'")
	public List<Candidate> findAllByAccountIdentifier(@Param("accountIdentifier") String accountIdentifier);
}
