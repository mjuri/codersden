package uk.codersden.login;

import org.springframework.data.jpa.repository.JpaRepository;



public interface AccountAccessDao extends JpaRepository<AccountAccess, String>{

}
