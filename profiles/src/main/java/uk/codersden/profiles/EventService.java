package uk.codersden.profiles;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventService {
	@Autowired
	private EventDao eventDao;
	private static Role ROLE_HR_ADMIN = new Role("HR-ADMIN");
	@Autowired
	private ProfileDao profileDao;
	
	public List<Event> findAllEventsByProfileIdentifier(String profileIdentifier) throws ProfileNotFoundException {
		Optional<Profile> optional = profileDao.findById(profileIdentifier);
		if(optional.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		
		Profile p = optional.get();
		
		List<Event> list = this.eventDao.findAllByProfileIdentifier(profileIdentifier);
		
		
		return list;
	}

	public Event createEvent(Event event) {

		Set<Profile> attendees = new HashSet<Profile>();
		List<Map<String, String>> attendeesValues = event.getAttendeesValues();
		Profile profile;
		for (Map<String, String> map : attendeesValues) {
			try {
				Optional<Profile> p = this.profileDao.findById(map.get("value"));
				if (p.isEmpty()) {
					throw new ProfileNotFoundException();
				}
				profile = p.get();
				event.addAttendee(profile);

			} catch (ProfileNotFoundException e) {
				System.out.println(e);
			}
		}


		Event newEvent = this.eventDao.save(event);
		return newEvent;
	}


	
	public Event updateEvent(String eventIdentifier, Event event) throws EventNotFoundException {
		Optional<Event> optional = eventDao.findById(eventIdentifier);
		if(optional.isEmpty()) {
			throw new EventNotFoundException();
		}
		
		Event newEvent = this.eventDao.save(event);
		
		return newEvent;
	}

	public Event findEventIdentifier(String eventIdentifier) throws EventNotFoundException {
		Optional<Event> optional = eventDao.findById(eventIdentifier);
		if(optional.isEmpty()) {
			throw new EventNotFoundException();
			
		}
		
		return optional.get();
	}

	
}
