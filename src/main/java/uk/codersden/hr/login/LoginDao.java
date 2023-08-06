package uk.codersden.hr.login;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.codersden.hr.profiles.User;

public interface LoginDao extends JpaRepository<User, String>{
}
