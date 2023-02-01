package uk.codersden.accountancy;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface InvoiceDao extends JpaRepository<Invoice, String>{
	public List<Invoice> findAllByAccountIdentifier(String accountIdentifier);
}
