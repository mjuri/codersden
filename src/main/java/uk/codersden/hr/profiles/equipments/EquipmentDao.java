package uk.codersden.hr.profiles.equipments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.codersden.hr.annoucements.Annoucement;

@Repository
public interface EquipmentDao extends JpaRepository<Equipment, String>{
	List<Equipment> findAllByProfileIdentifier(String profileIdentifier );
	
	List<Equipment> findAllByAccountIdentifier(String accountIdentifier);
}
