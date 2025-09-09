package uk.codersden.hr.profiles;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.sql.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.notifications.Notification;
import uk.codersden.hr.notifications.NotificationService;

@Service
public class EventService {
	@Autowired
	private EventDao eventDao;
	private static Role ROLE_HR_ADMIN = new Role("HR-ADMIN");
	@Autowired
	private ProfileDao profileDao;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private NotificationService notificationService;

	public List<Event> findAllEventsByProfileIdentifier(String profileIdentifier) throws ProfileNotFoundException {
		Optional<Profile> optional = profileDao.findById(profileIdentifier);
		if (optional.isEmpty()) {
			throw new ProfileNotFoundException();
		}

		Profile p = optional.get();

		List<Event> list = this.eventDao.findAllByProfileIdentifierAndStatus(profileIdentifier, "ACTIVE");

		return list;
	}

	public Event createEvent(Event event) {

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
				event.setStatus("ACTIVE");

			} catch (ProfileNotFoundException e) {
				System.out.println(e);
			}
		}

		Event newEvent = this.eventDao.save(event);
		try {
			sendNotification(event);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return newEvent;
	}

	private void sendNotification(Event event) {
		Set<Profile> attendees = event.getAttendees();
		for (Profile profile : attendees) {
			Notification n = new Notification();
			n.setOwner(event.getProfile());
			n.setMessage(" has created an event!");
			n.setTime(new Timestamp(System.currentTimeMillis()));
			n.setProfile(profile);
			n.setProfileIdentifier(profile.getIdentifier());
			n.setOwnerIdentifier(event.getProfileIdentifier());
			notificationService.sendNotification(n);

		}

	}

	public Event updateEvent(String eventIdentifier, Event event)
			throws EventNotFoundException, ProfileNotFoundException {
		Optional<Event> optional = eventDao.findById(eventIdentifier);
		if (optional.isEmpty()) {
			throw new EventNotFoundException();
		}
		Event existingEvent = optional.get();
		Optional<Profile> opProfile = profileDao.findById(event.getProfileIdentifier());

		if (opProfile.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		event.setProfile(opProfile.get());
		event.setProfileIdentifier(event.getProfile().getIdentifier());

		Set<Profile> updatedAttendees = new HashSet<Profile>();
		List<Map<String, String>> attendeesValues = event.getAttendeesValues();
		Profile profile;
		if (attendeesValues != null) {
			for (Map<String, String> map : attendeesValues) {
				try {
					Optional<Profile> p = this.profileDao.findById(map.get("value"));
					if (p.isEmpty()) {
						throw new ProfileNotFoundException();
					}
					profile = p.get();
					if (!existingEvent.getAttendees().contains(profile)) {
						event.addAttendee(profile);
					}
					updatedAttendees.add(profile);

				} catch (ProfileNotFoundException e) {
					System.out.println(e);
				}
			}
		}
		// Set<Profile> updatedList = existingEvent.getAttendees();
		try {
			for (Profile p : existingEvent.getAttendees()) {
				if (!updatedAttendees.contains(p)) {
					existingEvent.getAttendees().remove(p);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		modelMapper.getConfiguration().setAmbiguityIgnored(true);
		event.setDateCreated(existingEvent.getDateCreated());
		event.setModDate(new Date(System.currentTimeMillis()));

		modelMapper.map(event, existingEvent); // To copy all the attributes from event to the existing one.
		Event newEvent = this.eventDao.save(existingEvent);

		return newEvent;
	}

	public Event findEventIdentifier(String eventIdentifier) throws EventNotFoundException {
		Optional<Event> optional = eventDao.findById(eventIdentifier);
		if (optional.isEmpty()) {
			throw new EventNotFoundException();

		}

		return optional.get();
	}

	public Event archiveEvent(String identifier) throws EventNotFoundException, ProfileNotFoundException {
		Optional<Event> op = eventDao.findById(identifier);
		if (op.isEmpty()) {
			throw new EventNotFoundException();
		}
		Event event = op.get();
		event.setStatus("ARCHIVED");
		return this.updateEvent(identifier, event);
	}

	public Double calculateCost(String identifier) throws EventNotFoundException {
		Optional<Event> op = eventDao.findById(identifier);
		if (op.isEmpty()) {
			throw new EventNotFoundException();
		}
		Event event = op.get();

		Double cost = 0.0;
		for(Profile employee : event.getAttendees()) {
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
		}
;		return cost;
	}

}
