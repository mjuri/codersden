package uk.codersden.profiles;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileDao profileDao;

	public void create(Profile profile) {
		if(profile.getIdentifier() != null) {
			profile.setIdentifier(generateIdentifier());
		}
		this.profileDao.save(profile);
	}

	private String generateIdentifier() {
		return UUID.randomUUID().toString();
	}

	public List<Profile> findAllProfiles() {
		List<Profile> profiles = profileDao.findAll();
		
		return profiles;
	}
	
	
	
}
