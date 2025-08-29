package uk.codersden.hr.payroll;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PayrollDao extends JpaRepository<Payroll, String> {
	Optional<Payroll> findByAccountIdentifierAndFiscalYear(String accountIdentifier, String fiscalYear);

}
