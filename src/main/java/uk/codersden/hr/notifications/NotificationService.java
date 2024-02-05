package uk.codersden.hr.notifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.profiles.Profile;
import uk.codersden.hr.profiles.ProfileDao;
import uk.codersden.hr.profiles.ProfileNotFoundException;
import uk.codersden.hr.profiles.ProfileService;

@Service
public class NotificationService {
	@Autowired
	private NotificationDao dao;
	
	@Autowired
	private ProfileDao profileDao;
	
	public Notification sendNotification(Notification notification) {
		// First I save the notification
		Notification newNotification = null;
		
		newNotification = dao.save(notification);

		// Secondly, I'll send an email to the user.
		//TODO
		return newNotification;
	}
	public List<Notification> retrieveAllNotificationsByProfile(String profileIdentifier) throws ProfileNotFoundException{
		List<Notification> list = new ArrayList<>();
		
		Optional<Profile> op = profileDao.findById(profileIdentifier);
		
		if(op.isEmpty()) {
			throw new ProfileNotFoundException();
		}
		list = dao.findAllByProfile(op.get());
		
		return list;
	}
	public Notification removeNotification(String identification) throws NotificationNotFoundException {
		Optional<Notification> op = dao.findById(identification);
		if(op.isEmpty()) {
			throw new NotificationNotFoundException(identification + " Not found!");
		}
		Notification n = op.get();
		
		dao.delete(n);
		return n;
	}
}
