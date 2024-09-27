package uk.codersden.hr.profiles.equipments;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;

@Service
public class EquipmentService {
	
	@Autowired
	private EquipmentDao dao;

	public Equipment createEquipment(Equipment equipment) {
		return this.dao.save(equipment);
	}

	public Equipment updateEquipment(Equipment equipment) throws NotFoundException {
		Optional<Equipment> op = this.dao.findById(equipment.getIdentifier());
		if(op.isEmpty()) {
			throw new NotFoundException(equipment.getIdentifier());
		}
		
		return this.dao.save(equipment);
	}

	public List<Equipment> retrieveEquipmentsByAccount(String accountIdentifier) {
		return this.dao.findAllByAccountIdentifier(accountIdentifier);
	}

	public Equipment retrieveEquipment(String identifier) throws NotFoundException {

		Optional<Equipment> op = this.dao.findById(identifier);
		if(op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		
		return op.get();
	}

	public List<Equipment> retrieveEquipmentsByProfile(String profileIdentifier) {
		return this.dao.findAllByProfileIdentifier(profileIdentifier);
	}

}
