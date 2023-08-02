package uk.codersden.login;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



public interface LoginDao extends JpaRepository<Login, String>{
}
