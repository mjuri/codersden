package uk.codersden.profiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
	@Autowired
	private EventDao eventDao;
	private static Role ROLE_HR_ADMIN = new Role("HR-ADMIN");
	@Autowired
	private ProfileDao profileDao;
	
	public List<Holiday> findAllEventsByProfileIdentifier(String profileIdentifier) throws ProfileNotFoundException {
		Optional<Profile> optional = profileDao.findById(profileIdentifier);
		if(optional.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		
		Profile p = optional.get();
		
		List<Holiday> list = this.eventDao.findAllByProfileIdentifier(profileIdentifier);
		
		
		return list;
	}

	
}
