package uk.codersden.hr.profiles;

import java.util.Arrays;
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

import uk.codersden.hr.profiles.documents.DocumentPayload;


@RestController
@RequestMapping("/profile")
public class ProfileController {

    
	private static final String HR_ROLE_KEY = "HR-ADMIN";

	@Autowired
	private ProfileService profileService;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping("/account/{accountIdentifier}")
	@CrossOrigin
	public List<Profile> retrieveAllProfiles(@PathVariable("accountIdentifier") String accountIdentifier){
		return profileService.findProfilesByAccount(accountIdentifier);
		
	}
	@PutMapping("/password/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> updatePassword(@PathVariable("identifier") String identifier, @RequestBody User user) 
	{	
		User userUpdated = profileService.updatePasswordForUser(user);
		// Add validations here
		return ResponseEntity.ok(userUpdated);
	}
	
	@PostMapping("/password/request/{identifier}")
	@CrossOrigin
	public ResponseEntity<?> requestChangePassword(@PathVariable("identifier") String identifier){
		//TODO Send Email to user
		return ResponseEntity.ok(identifier);
	}
	
	@GetMapping("/account/{accountIdentifier}/off")
	@CrossOrigin
	public List<Profile> retrieveOutOfOfficeProfiles(@PathVariable("accountIdentifier") String accountIdentifier){
		return profileService.findProfilesOutOfOffice(accountIdentifier);
		
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
	
	@PutMapping
	@CrossOrigin
	public ResponseEntity<?> updateProfile(@RequestBody Profile profile) 
	{	
		try {
			Profile oldProfile = profileService.findProfileByIdentifier(profile.getIdentifier());
			profile.setAccountIdentifier(oldProfile.getAccountIdentifier());
			Profile p = profileService.update(profile);
			
			return ResponseEntity.ok(p);
		}catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}	
	@PostMapping("/account/{accountIdentifier}")
	@CrossOrigin
	public ResponseEntity<?> createProfile(@PathVariable("accountIdentifier") String accountIdentifier, 
			@RequestBody Profile profile) 
	{	

		Profile p = null;
		User user = new User();
		user.setUserName(profile.getEmail());
		user.setPassword(profileService.generateInitialPassword());
		
		try {
			user = this.profileService.createUser(user);
			profile.setAccountIdentifier(accountIdentifier);
			p = profileService.create(profile);
					
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok(p);
	}
	
	@PostMapping("/profile-with-avatar")
	@CrossOrigin
	public ResponseEntity<?> createProfile(@RequestParam("profile") String profilePayload, 
			@RequestParam("accountIdentifier") String accountIdentifier,
			@RequestParam("file") MultipartFile fileBase64) throws ProfileNotFoundException 
	{
		Profile profile = null;
		Profile p = null;
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			profile = mapper.readValue(profilePayload, Profile.class);
			p = profileService.create(profile);
			
			User user = new User();
			user.setUserName(p.getEmail());
			user.setPassword(profileService.generateInitialPassword());
			try {
				user = this.profileService.createUser(user);
				profile.setAccountIdentifier(accountIdentifier);
				p = profileService.create(profile);
						
				
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			
			if(null != fileBase64 ) {
				String fileName = this.profileService.saveAvatar(p.getIdentifier(), fileBase64);
				p.setAvatar(fileName);
				p = profileService.update(p);
			}			
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(p);
	}
	@PostMapping("/signup")
	@CrossOrigin
	public ResponseEntity<?> createProfileWithoutAvatar(@RequestBody OneRegistrationPayload payload) 
	{
		// 1) Create Account
		Account account = new Account();
		account.setName(payload.getCompanyName());
		account.setNumberOfEmployees(payload.getNumberOfEmployees());
		
		Account newAccount = accountService.createAccount(account);
		
		// 2) Create User
		User user = new User();
		
		user.setUserName(payload.getEmail());
		user.setPassword(payload.getPassword());
		
		Profile p = new Profile();
		
		try {
			profileService.createUser(user);
			
			// 3) Create Profile

			p.setLastName(payload.getLastName());
			p.setFirstName(payload.getFirstName());
			
			p.setEmail(payload.getEmail()); // Link User with Profile
			
			p.setAccountIdentifier(newAccount.getIdentifier()); // Link Account with Profile
			p.setRoles(Arrays.asList(this.profileService.findRoleByKey(HR_ROLE_KEY)));
			p = profileService.create(p);
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		return ResponseEntity.ok(p);
	}
	@PutMapping("/{id}")
	@CrossOrigin
	public ResponseEntity<?> updateProfileWithAvatar(@RequestParam("profile") String profilePayload,
			@RequestParam("file") MultipartFile fileBase64, 
			@PathVariable("id") String id){

		Profile oldProfile = null;
		Profile profileUpdated = null;
		Profile profile = null;
		ObjectMapper mapper = new ObjectMapper();
		try {
			profile = mapper.readValue(profilePayload, Profile.class);
			oldProfile = profileService.findProfileByIdentifier(id);
			profile.setAccountIdentifier(oldProfile.getAccountIdentifier());
			profile.setRoles(oldProfile.getRoles());
			if(null != fileBase64 ) {
				String fileName = this.profileService.saveAvatar(id, fileBase64);
				profile.setAvatar(fileName);
			}
			profileUpdated = profileService.update(profile);
		} catch (ProfileNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ResponseEntity.ok(profileUpdated);
	}



	
	@DeleteMapping("/{id}")
	public void deleteProfile(@PathVariable("id") String id) throws ProfileNotFoundException {
		Profile p = profileService.findProfileByIdentifier(id);
		p.setDeleted(true);
		profileService.update(p);
	}
	
	
}