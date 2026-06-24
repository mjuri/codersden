package uk.codersden.wod.pr;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRecordDao extends JpaRepository<PersonalRecord, String> {
	List<PersonalRecord> findAllByProfileIdentifierOrderByDateDesc(String id);
	List<PersonalRecord> findAllByProfileIdentifierAndTypeOrderByDateDesc(String id, String type);
}
