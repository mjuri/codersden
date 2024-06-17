package uk.codersden.hr.salarylevels;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.codersden.hr.supporttickets.SupportTicket;

public interface SalaryLevelDao extends JpaRepository<SalaryLevel, String>{
	List<SalaryLevel> findAllByAccountIdentifier(String accountIdentifier);
}
