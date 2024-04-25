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

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.groups.Group;
import uk.codersden.hr.groups.GroupDao;
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
	private GroupDao groupDao;
	
	@Autowired
	private ModelMapper	 modelMapper;

	public Annoucement createAnnoucement(Annoucement annoucement) {
		
		Optional<Profile> opProfile = profileDao.findById(annoucement.getProfileIdentifier());
		
		annoucement.setProfile(opProfile.get());
		
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
		
		List<Map<String, String>> groupsValues = annoucement.getGroupsValues();
		Group group;
		Set<Group> groups = new HashSet<>();
		
		for (Map<String, String> map : groupsValues) {
			try {
				Optional<Group> g = this.groupDao.findById(map.get("value"));
				if (g.isEmpty()) {
					throw new NotFoundException(map.get("value"));
				}
				group = g.get();
				groups.add(group);
				
			} catch (NotFoundException e) {
				System.out.println(e);
			}
		}
		annoucement.setGroups(groups);
		

		
		Annoucement a = dao.save(annoucement);
		
		
		// Send email
		this.sendNotification(a);
		return a;
	}
	private void sendNotification(Annoucement a) {
		Set<Profile> attendees = a.getAudience();
		Set<Group> groups = a.getGroups();
		
		for (Group group : groups) {
			attendees.addAll(group.getMembers());
		}
		
		
		for (Profile profile : attendees) {
			Notification n = new Notification();
			n.setOwner(a.getProfile());
			n.setMessage(" has created a new annoucement!");
			n.setTime(new Timestamp(System.currentTimeMillis() ));
			n.setProfile(profile);
			n.setProfileIdentifier(profile.getIdentifier());
			n.setOwnerIdentifier(a.getProfileIdentifier());
			n.setOwner(n.getOwner());
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
		
		return dao.save(this.validateGroups(validateAudience(annoucement, op.get()), op.get()) );
	}
	private Annoucement validateGroups(Annoucement updatedAnnoucement, Annoucement existentAnnoucement) {
		Group group;
	    Set<Group> updatedGroups = new HashSet<>();
		for (Map<String, String> map : updatedAnnoucement.getGroupsValues()) {
			try {
				Optional<Group> g = this.groupDao.findById(map.get("value"));
				if (g.isEmpty()) {
					throw new NotFoundException(map.get("value"));
				}
				group = g.get();
				if(!existentAnnoucement.getGroups().contains(group)) {
					existentAnnoucement.addGroup(group);
				}	
				updatedGroups.add(group);
				
			} catch (NotFoundException e) {
				System.out.println(e);
			}
		}

		try {
			for(Group g: existentAnnoucement.getGroups()) {
				if(!updatedGroups.contains(g)) {
					existentAnnoucement.getGroups().remove(g);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		modelMapper.map(updatedAnnoucement, existentAnnoucement); // To copy all the attributes from event to the existing one.
		
		return existentAnnoucement;
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
		List<Annoucement> shareList = dao.findAllSharedWithUser(profileIdentifier);
		List<Annoucement> createdBy = dao.findAllByProfileIdentifier(profileIdentifier);
		
		shareList.removeAll(createdBy);
		createdBy.addAll(shareList);
		
		return createdBy;
	}
}
