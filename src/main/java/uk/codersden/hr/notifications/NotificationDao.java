package uk.codersden.hr.notifications;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uk.codersden.hr.profiles.Profile;

@Repository
public interface NotificationDao extends JpaRepository<Notification, String>{
	List<Notification> findAllByProfile(Profile profile);
}
