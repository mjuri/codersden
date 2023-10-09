package uk.codersden.hr.profiles;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import uk.codersden.hr.login.LoginService;

@Service
public class ProfileService {
    //@Autowired
    //private RestTemplate restTemplate;

    @Autowired
    private StaticResourceService resourceService;
    
	@Autowired
	private ProfileDao profileDao;

	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private LoginService loginService;
	
	public Profile create(Profile profile) {
		if(profile.getIdentifier() != null) {
			profile.setIdentifier(generateIdentifier());
		}
		Profile newProfile = this.profileDao.save(profile);
		return newProfile;
	}

	private String generateIdentifier() {
		return UUID.randomUUID().toString();
	}

	public List<Profile> findAllProfiles() {
		List<Profile> profiles = profileDao.findAllByDeleted(false);
		
		return profiles;
	}

	public Profile findProfileByIdentifier(String id) throws ProfileNotFoundException {
		Optional<Profile> optional = profileDao.findById(id);
		if(optional.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		
		return optional.get();
		
	}
	public Role findRoleByKey(String key) throws Exception{
		Optional<Role> op = roleDao.findById(key);
		if(op.isEmpty()) {
			throw new ProfileNotFoundException();
			
		}
		return op.get();
	}
	public Profile findProfileByToken(String token) throws ProfileNotFoundException {
		/*Application application = eurekaClient.getApplication("login-service");
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort()
        + "/access/" + token;
        System.out.println("URL: " + url);
        ResponseEntity<AccountAccess> responseAccess = restTemplate.getForEntity(url, AccountAccess.class);
		
        AccountAccess access = responseAccess.getBody();*/
        uk.codersden.hr.login.AccountAccess access = loginService.findAccountAccessByToken(token);
        
        Optional<Profile> op = profileDao.findByEmail(access.getUserName());
        if(op.isEmpty()) {
        	throw new ProfileNotFoundException();
        }
        Profile p = op.get();
        
        return p;
        
	}
	public Profile update(Profile profile) {
		Profile p = this.profileDao.save(profile);
		return p;
	}
	
	public String saveAvatar(String profileIdentifier, MultipartFile fileBase64) {
		Path pathFolder = Paths.get(resourceService.getStaticDirectoryPath() + "/" + profileIdentifier.toString());
		File file = null;
		String fileName = "";
		try {
			Files.createDirectories(pathFolder);
			byte[] imageByte= fileBase64.getBytes();
			fileName = pathFolder.toString() + "/" + profileIdentifier + ".jpg";
			file = new File(fileName);
			if(file.exists()) {
				file.delete();
			}
			FileOutputStream fos = new FileOutputStream(fileName);
			fos.write(imageByte);
			fos.close();
			file = new File(fileName);
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}		
		String returnURL = fileName.replaceAll("static", "");
		return returnURL;
		
	}

	public User createUser(User user) {
		User u = this.userDao.save(user);
		return u;
		
	}

	public String generateInitialPassword() {
		return PasswordGenerator.generatePassword();

	}

	public List<Profile> findProfilesByAccount(String accountIdentifier) {
		return this.profileDao.findAllByAccountIdentifier(accountIdentifier);
	}
	
}
