package uk.codersden.hr.annoucements;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.notifications.Notification;
import uk.codersden.hr.notifications.NotificationService;
import uk.codersden.hr.profiles.AccountDao;
import uk.codersden.hr.profiles.Event;
import uk.codersden.hr.profiles.Profile;
import uk.codersden.hr.profiles.ProfileDao;
import uk.codersden.hr.profiles.ProfileNotFoundException;

@Service
public class AnnoucementService {

	@Autowired
	private AnnoucementDao dao;

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private AccountDao accountDao;
	
	@Autowired
	private ModelMapper	 modelMapper;

	public Annoucement createAnnoucement(Annoucement annoucement) {
		// Check audience
		List<Map<String, String>> audienceValues = annoucement.getAudienceValues();
		Profile profile;
		Set<Profile> profiles = new HashSet<>();
		
		for (Map<String, String> map : audienceValues) {
			try {
				Optional<Profile> p = this.profileDao.findById(map.get("value"));
				if (p.isEmpty()) {
					throw new ProfileNotFoundException();
				}
				profile = p.get();
				profiles.add(profile);
				
			} catch (ProfileNotFoundException e) {
				System.out.println(e);
			}
		}
		annoucement.setAudience(profiles);
		Annoucement a = dao.save(annoucement);
		
		
		// Send email
		this.sendNotification(a);
		return a;
	}
	private void sendNotification(Annoucement a) {
		Set<Profile> attendees = a.getAudience();
		for (Profile profile : attendees) {
			Notification n = new Notification();
			n.setOwner(a.getProfile());
			n.setMessage(" has created a new annoucement!");
			n.setTime(new Timestamp(System.currentTimeMillis() ));
			n.setProfile(profile);
			n.setProfileIdentifier(profile.getIdentifier());
			n.setOwnerIdentifier(a.getProfileIdentifier());
			
			notificationService.sendNotification(n);
			
		}
		
	}
	public Annoucement updateAnnoucement(String identifier, Annoucement annoucement) throws AnnoucementNotFoundException{
		Optional<Annoucement> op = dao.findById(identifier);
		// Check audience
		if(op.isEmpty()) {
			throw new AnnoucementNotFoundException();
		}
		annoucement.setIdentifier(identifier);
		modelMapper.getConfiguration().setAmbiguityIgnored(true);

		return dao.save(this.validateAudience(annoucement, op.get()));
	}

	private Annoucement validateAudience(Annoucement updatedAnnoucement, Annoucement existentAnnoucement) {

		Profile profile;
	    Set<Profile> updatedAudience = new HashSet<>();
		for (Map<String, String> map : updatedAnnoucement.getAudienceValues()) {
			try {
				Optional<Profile> p = this.profileDao.findById(map.get("value"));
				if (p.isEmpty()) {
					throw new ProfileNotFoundException();
				}
				profile = p.get();
				if(!existentAnnoucement.getAudience().contains(profile)) {
					existentAnnoucement.addAudience(profile);
				}	
				updatedAudience.add(profile);
				
			} catch (ProfileNotFoundException e) {
				System.out.println(e);
			}
		}

		try {
			for(Profile p: existentAnnoucement.getAudience()) {
				if(!updatedAudience.contains(p)) {
					existentAnnoucement.getAudience().remove(p);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		modelMapper.map(updatedAnnoucement, existentAnnoucement); // To copy all the attributes from event to the existing one.
		
		return existentAnnoucement;
	}

	public Annoucement findByAnnoucementIdentifier(String identifier) throws AnnoucementNotFoundException{
		Optional<Annoucement> op = dao.findById(identifier);
		
		if(op.isEmpty()) {
			throw new AnnoucementNotFoundException();
		}
		return op.get();
	}

	public List<Annoucement> findAllAnnoucementsByProfileIdentifier(String profileIdentifier) throws ProfileNotFoundException{
		Optional<Profile> op = profileDao.findById(profileIdentifier);
		if(op.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		List<Annoucement> list = dao.findAllSharedWithUser(profileIdentifier);
		return list;
	}
}
