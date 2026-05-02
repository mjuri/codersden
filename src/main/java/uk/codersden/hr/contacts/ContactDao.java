package uk.codersden.hr.contacts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactDao extends JpaRepository<Contact, String>{

	public List<Contact> findAllByAccountIdentifier(String accountIdentifier);

}
