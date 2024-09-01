package uk.codersden.hr.profiles.equipments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import uk.codersden.hr.annoucements.Annoucement;

public interface EquipmentDao extends JpaRepository<Equipment, String>{
	List<Annoucement> findAllByProfileIdentifier(String id);
}
