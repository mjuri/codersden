package uk.codersden.profiles;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping
	public List<Profile> retrieveAllProfiles(){
		return profileService.findAllProfiles();
		
	}
	
	@GetMapping("/{id}")
	@CrossOrigin
	public Profile retrieveProfile(@PathVariable("id") String id) throws ProfileNotFoundException {
		return profileService.findProfileByIdentifier(id);
		
	}
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<?> createProfile(@RequestBody Profile profile) 
	{
		Profile p = profileService.create(profile);
		return ResponseEntity.ok(p);
	}
	
	@PutMapping("/{id}")
	public void updateProfile(@PathVariable("id") String id, @RequestBody Profile profile) throws ProfileNotFoundException {
		Profile p = profileService.findProfileByIdentifier(id);
		profileService.update(id, profile);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProfile(@PathVariable("id") String id) throws ProfileNotFoundException {
		Profile p = profileService.findProfileByIdentifier(id);
		p.setDeleted(true);
		profileService.update(id, p);
	}
	
	
}
