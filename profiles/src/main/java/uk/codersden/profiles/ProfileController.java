package uk.codersden.profiles;

import java.util.List;

import javax.websocket.server.PathParam;

import org.apache.catalina.connector.Response;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import uk.codersden.profiles.documents.DocumentPayload;


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
	public ResponseEntity<?> retrieveProfile(@PathVariable("id") String id) throws ProfileNotFoundException {
		Profile p = profileService.findProfileByIdentifier(id);
		return ResponseEntity.ok(p);
		
	}
	@GetMapping("/token/{token}")
	@CrossOrigin
	public ResponseEntity<?> retrieveProfileByToken(@PathVariable("token") String token) throws ProfileNotFoundException {
		Profile p = profileService.findProfileByToken(token);
		return ResponseEntity.ok(p);
	}
	
	@PostMapping
	@CrossOrigin
	public ResponseEntity<?> createProfile(@RequestParam("profile") String profilePayload, @RequestParam("file") String fileBase64) 
	{
		Profile profile = null;
		Profile p = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			profile = mapper.readValue(profilePayload, Profile.class);
			p = profileService.create(profile);
			
			if(null != fileBase64 ) {
				String fileName = this.profileService.saveAvatar(p.getIdentifier(), fileBase64);
				p.setAvatar(fileName);
				p = profileService.update(p.getIdentifier(), p);
			}			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(p);
	}
	@PostMapping("/signup")
	@CrossOrigin
	public ResponseEntity<?> createProfileWithoutAvatar(@RequestBody RegistrationPayload payload) 
	{
		User user = new User();
		
		user.setUserName(payload.getEmail());
		user.setPassword(payload.getPassword());
		
		Profile p = new Profile();
		p.setFirstName(payload.getFirstName());
		p.setEmail(payload.getEmail());
		
		
		try {
			profileService.createUser(user);
			p = profileService.create(p);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(p);
	}
	@PutMapping("/{id}")
	@CrossOrigin
	public ResponseEntity<?> updateProfileWithAvatar(@RequestParam("profile") String profilePayload,
			@RequestParam("file") String fileBase64, 
			@PathVariable("id") String id){

		Profile profile = null;
		Profile p = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			profile = mapper.readValue(profilePayload, Profile.class);
			profileService.findProfileByIdentifier(id);
		} catch (ProfileNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(null != fileBase64 ) {
			String fileName = this.profileService.saveAvatar(id, fileBase64);
			profile.setAvatar(fileName);
		}
		p = profileService.update(id, profile);
		return ResponseEntity.ok(p);
	}


	@PutMapping("/old/{id}")
	@CrossOrigin
	public ResponseEntity<?> updateProfile(@PathVariable("id") String id, @RequestBody Profile profile) throws ProfileNotFoundException {
		Profile p = profileService.findProfileByIdentifier(id);
		p = profileService.update(id, profile);
		return ResponseEntity.ok(p);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProfile(@PathVariable("id") String id) throws ProfileNotFoundException {
		Profile p = profileService.findProfileByIdentifier(id);
		p.setDeleted(true);
		profileService.update(id, p);
	}
	
	
}
