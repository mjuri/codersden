package uk.codersden.hr.leads;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeadContactHistoryDao extends JpaRepository<LeadContactHistory, String> {

	List<LeadContactHistory> findAllByLeadIdentifier(String leadIdentifier);
}
