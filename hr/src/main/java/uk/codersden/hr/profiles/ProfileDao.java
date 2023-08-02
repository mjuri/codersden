package uk.codersden.hr.profiles;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends JpaRepository<Profile, String>{
	public List<Profile> findAllByDeleted(boolean deleted);
	public Optional<Profile> findByEmail(String email);
	public List<Profile> findAllByAccountIdentifier(String accountIdentifier);
}
