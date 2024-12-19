package uk.codersden.hr.profiles.equipments;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uk.codersden.hr.profiles.Goal;

@RestController
@RequestMapping("/equipment")
public class EquipmentController {

	@Autowired
	private EquipmentService service;
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<?> createEquipment(@RequestBody Equipment equipment) 
	{
		Equipment newEquipment = null;
		try {
			newEquipment = service.createEquipment(equipment);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(newEquipment);

		
	}
	
	@GetMapping("/account/{accountIdentifier}")
	@CrossOrigin
	public ResponseEntity<?> retrieveEquipmentsByAccount(@PathVariable("accountIdentifier") String accountIdentifier) 
	{
		List<Equipment> equipments = new ArrayList<>();
		try {
			equipments = service.retrieveEquipmentsByAccount(accountIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(equipments);

		
	}
	@GetMapping("/profile/{profileIdentifier}")
	@CrossOrigin
	public ResponseEntity<?> retrieveEquipmentsByProfile(@PathVariable("profileIdentifier") String profileIdentifier) 
	{
		List<Equipment> equipments = new ArrayList<>();
		try {
			equipments = service.retrieveEquipmentsByProfile(profileIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(equipments);

		
	}
	@GetMapping("/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> retrieveAccount(@PathVariable("identifier") String identifier) 
	{
		Equipment updatedEquipment = null;
		try {
			updatedEquipment = service.retrieveEquipment(identifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(updatedEquipment);

		
	}
	
	@PutMapping("/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> updateEquipment(@PathVariable("identifier") String identifier, @RequestBody Equipment equipment) 
	{
		Equipment updatedEquipment = null;
		try {
			equipment.setIdentifier(identifier);
			updatedEquipment = service.updateEquipment(equipment);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		return ResponseEntity.ok(updatedEquipment);

		
	}
}
