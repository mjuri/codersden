package uk.codersden.hr.notifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.mail.EmailService;
import uk.codersden.hr.profiles.Profile;
import uk.codersden.hr.profiles.ProfileDao;
import uk.codersden.hr.profiles.ProfileNotFoundException;

@Service
public class NotificationService {
	@Autowired
	private NotificationDao dao;
	
	@Autowired
	private ProfileDao profileDao;
	
	@Autowired
	private EmailService emailService;
	
	private ExecutorService executorService = Executors.newSingleThreadExecutor();
	
	public Notification sendNotification(Notification notification) {
		// First I save the notification
		Notification newNotification = null;

		newNotification = dao.save(notification);
		
        // Runnable to execute the email sending part in a separate thread
        Runnable emailSendingTask = () -> {
		String accountIdentifier = notification.getOwner().getAccountIdentifier();
		String to = notification.getProfile().getEmail();
		String from = notification.getOwner().getEmail();
		
		emailService.sendMail(accountIdentifier, from, to, "You've received a new notification!", notification.getMessage());
        };

        // Execute the email sending task asynchronously
        executorService.execute(emailSendingTask);

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
