package uk.codersden.hr.login;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.codersden.hr.login.AccountAccess;


@Repository
public interface AccountAccessDao extends JpaRepository<AccountAccess, String>{

}
