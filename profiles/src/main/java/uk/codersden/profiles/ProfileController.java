package uk.codersden.profiles;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {
	
	@Autowired
	private ProfileService profileService;
	
	@GetMapping("/profile")
	public List<Profile> retrieveAllProfiles(){
		return profileService.findAllProfiles();
		
	}
	
	@GetMapping("/{id}")
	public Profile retrieveProfile(@PathParam("id") String id) {
		return null;
		
	}
	
	@PostMapping("/profile")
	public void createProfile(@RequestBody Profile profile) {
		profileService.create(profile);
	}
	
	@PutMapping("/{id}")
	public void updateProfile(@PathParam("id") String id, @RequestBody Profile profile) {
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteProfile(@PathParam("id") String id) {
		
	}
	
	
}
