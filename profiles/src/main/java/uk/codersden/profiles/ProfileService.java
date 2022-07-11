package uk.codersden.profiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
	
	@Autowired
	private ProfileDao profileDao;

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
		if(optional == null) {
			throw new ProfileNotFoundException();
		}
		
		return optional.get();
		
	}
	
	public Profile update(String id, Profile profile) {
		Profile p = this.profileDao.save(profile);
		return p;
	}
	
	
	
}
