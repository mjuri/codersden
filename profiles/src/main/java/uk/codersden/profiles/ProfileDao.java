package uk.codersden.profiles;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDao extends JpaRepository<Profile, String>{
	public List<Profile> findAllByDeleted(boolean deleted);
	public Optional<Profile> findByEmail(String email);
}
