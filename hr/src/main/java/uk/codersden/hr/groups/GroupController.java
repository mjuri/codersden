package uk.codersden.hr.groups;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import uk.codersden.hr.profiles.Contract;
import uk.codersden.hr.profiles.Event;
import uk.codersden.hr.profiles.Profile;

@RestController
@RequestMapping("/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<?> createGroup(@RequestBody Group group) 
	{
		Group newGroup = null;
		try {
			newGroup = this.groupService.createGroup(group);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		return ResponseEntity.ok(newGroup);
	}
	@PutMapping("/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> updateGroup(@PathVariable("identifier") String identifier, @RequestBody Group group) 
	{
		Group updatedGroup = null;
		try {
			updatedGroup = this.groupService.updateGroup(identifier, group);
			
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.internalServerError().body(e);
		}
		return ResponseEntity.ok(updatedGroup);
	}
	@CrossOrigin
	@GetMapping("/account/{accountIdentifier}")
	public ResponseEntity<?> retrieveGroupsByProfile(@PathVariable("accountIdentifier") String accountIdentifier){
		List<Group> list = new ArrayList<>();
		try {
			list = groupService.findAllGroupsByAccountIdentifier(accountIdentifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(list);
		
	}

	@CrossOrigin
	@GetMapping("/{identifier}")
	public ResponseEntity<?> retrieveGroup(@PathVariable("identifier") String identifier){
		Group g = null;
		try {
			g = groupService.findGroupByIdentifier(identifier);
		}catch(Exception e) {
			return ResponseEntity.internalServerError().body(e);
		}
		
		return ResponseEntity.ok(g);
		
	}
}
