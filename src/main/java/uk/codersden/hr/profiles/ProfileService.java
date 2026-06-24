package uk.codersden.hr.profiles;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import uk.codersden.hr.login.LoginService;
import uk.codersden.hr.mail.EmailSender;
import uk.codersden.hr.settings.Settings;
import uk.codersden.hr.settings.SettingsDao;

@Service
public class ProfileService {
    
	@Autowired
	private ProfileDao profileDao;

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private ContractDao contractDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SettingsDao settingsDao;
	
	@Autowired
	private HolidayDao holidayDao;
	
	@Autowired
	private HolidayHelper holidayHelper;
	
	@Autowired
	private LoginService loginService;
	
	public Profile create(Profile profile) {
		Contract contract = null;
		if(profile.getIdentifier() != null) {
			profile.setIdentifier(generateIdentifier());
		}
		if(profile.getContract()!=null) {
			contract = profile.getContract();
			profile.setContract(null);
		}

		
		Profile newProfile = this.profileDao.save(profile);
		if(contract != null) {
			contract.setProfile(newProfile);
			Contract newContract = this.contractDao.save(contract);
			
			newProfile.setContract(newContract);
		}

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
		Profile p = optional.get();
		Contract contract = p.getContract();
		if(null != contract) {
			Double holidayBroughtForward = contract.getHolidayEntitlement() - holidayHelper.calculateHolidaysTakenLastYear(contract);
			holidayBroughtForward = holidayBroughtForward > contract.getBroughtForwardScheme() ? contract.getBroughtForwardScheme() : holidayBroughtForward;
		
			contract.setHolidayBroughtForward(holidayBroughtForward);
		
			p.setContract(contract);
		}
		return p;
		
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
	public Profile update(Profile profile) throws ProfileNotFoundException {
		Profile oldProfile = this.findProfileByIdentifier(profile.getIdentifier());
		
		if(null == profile.getAvatar()) {
			profile.setAvatar(oldProfile.getAvatar());
		}
		Contract contract = profile.getContract();
		contract.setProfile(profile);
		profile.setContract(contract);
		return this.profileDao.save(profile);
	}
	
	public String saveAvatar(String profileIdentifier, MultipartFile fileBase64) {
		String returnURL;
		try {
			returnURL = storageService.save("avatars", profileIdentifier, fileBase64);
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
		}

		/*Path pathFolder = Paths.get(resourceService.getStaticDirectoryPath("avatars") + "/" + profileIdentifier.toString());
		File file = null;
		String fileName = "";

		try {
			Files.createDirectories(pathFolder);
			byte[] imageByte= fileBase64.getBytes();
			fileName = pathFolder.toString() + "/" + UUID.randomUUID() + ".jpg";
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
		return returnURL;*/
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
		
		List<Profile> profiles = this.profileDao.findAllByAccountIdentifier(accountIdentifier);
		
		for(Profile p : profiles) {
			if(null != p.getContract()) {
				p.getContract().setSalaryPerHour(this.calculateSalaryPerHour(p));
			}	
		}
		
		return profiles;
	}

	private Double calculateSalaryPerHour(Profile employee) {
		Double cost = 0.0;
		if(null != employee.getContract()) {
			if(null != employee.getContract().getGrossSalary()) {
				if("weekly".equals(employee.getContract().getSalaryType()) ){
					cost += employee.getContract().getGrossSalary() / 40;
				}
				if("monthly".equals(employee.getContract().getSalaryType()) ) {
					cost += employee.getContract().getGrossSalary() / 172;
				}
				if("annual".equals(employee.getContract().getSalaryType())) {
					cost += employee.getContract().getGrossSalary() / 2080;
				}
			}
		}
		
		return cost;
	}
	
	public List<Profile> findProfilesOutOfOffice(String accountIdentifier) {
		return holidayDao.findAllProfilesOutOfOffice(accountIdentifier);
	}

	public User updatePasswordForUser(User user) {
		return userDao.save(user);
	}

	public void sendCredentialsToUser(String identifier) throws ProfileNotFoundException {
		Optional<Profile> op = this.profileDao.findById(identifier);
		if(op.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		Profile p = op.get();
		String accountIdentifier = p.getAccountIdentifier();
		
		Optional<User> opUser = this.userDao.findById(p.getEmail());
		
		User user = opUser.get();
		

		
		String subject = String.format("[ BrightStaffTool ] Hello %s,here are your credentials", p.getFirstName() );
		String body = String.format("Your credentials are: \nUser: %s \nPassword: %s", user.getUserName(), user.getPassword() );
		
        // Create a single-thread executor
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        // Submit the email task to the executor service
        executorService.submit(() -> {
    		Optional<Settings> opSettings = this.settingsDao.findById(accountIdentifier);
    		
    		Settings settings = EmailSender.getDefaultSettings();
    		
    		if(!op.isEmpty()) {
    			settings = opSettings.get();
    		}
    		
            try {
                EmailSender.send(settings, p.getEmail(), subject, body);
                System.out.println("Email sent successfully to " + p.getEmail());
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Failed to send email to " + p.getEmail());
            }
        });

        // Shutdown the executor service
        executorService.shutdown();

        // Continue with other tasks in the main thread
        System.out.println("Email task submitted...");
	}
	
}
