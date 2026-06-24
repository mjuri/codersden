package uk.codersden.wod.classes;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
@Repository
public interface WODClassDao extends JpaRepository<WODClass, String> {
	List<WODClass> findAllByAccountIdentifier(String id);
	List<WODClass> findByAttendeesIdentifier(String profileIdentifier);
    List<WODClass> findAllByAccountIdentifierAndDateBetween(
            String accountIdentifier,
            LocalDate startDate,
            LocalDate endDate
        );
}
