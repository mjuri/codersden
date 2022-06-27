package uk.codersden.profiles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileDao extends JpaRepository<Profile, String>{

}
