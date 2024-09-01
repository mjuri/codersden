package uk.codersden.hr.profiles;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends JpaRepository<Profile, String>{
	public List<Profile> findAllByDeleted(boolean deleted);
	public Optional<Profile> findByEmail(String email);
	
    @Query("SELECT p FROM Profile p WHERE p.accountIdentifier = :accountIdentifier AND deleted = false")
	public List<Profile> findAllByAccountIdentifier(@Param("accountIdentifier") String accountIdentifier);
}
