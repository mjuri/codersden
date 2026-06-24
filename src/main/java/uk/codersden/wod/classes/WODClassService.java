package uk.codersden.wod.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.profiles.Account;
import uk.codersden.hr.profiles.AccountDao;
import uk.codersden.hr.profiles.AccountNotFoundException;
import uk.codersden.hr.profiles.Profile;
import uk.codersden.hr.profiles.ProfileDao;
import uk.codersden.hr.profiles.ProfileNotFoundException;

@Service
public class WODClassService {
	@Autowired
	private WODClassDao dao;

	@Autowired
	private AccountDao accountDao;

	@Autowired
	private ProfileDao profileDao;

	public List<WODClass> findAllByAccountIdentifier(String accountIdentifier) throws AccountNotFoundException {
		Optional<Account> op = accountDao.findById(accountIdentifier);
		if (op.isEmpty()) {
			throw new AccountNotFoundException(accountIdentifier + " not found");
		}

		List<WODClass> list = dao.findAllByAccountIdentifier(accountIdentifier);

		return list;
	}

	public List<WODClass> findBookingsByProfileIdentifier(String profileIdentifier) throws AccountNotFoundException {

		List<WODClass> list = dao.findByAttendeesIdentifier(profileIdentifier);

		return list;
	}

	public WODClass createWODClass(WODClass obj) {
		return dao.save(obj);
	}

	public WODClass updateWODClass(String identifier, WODClass obj) throws NotFoundException {
		Optional<WODClass> op = dao.findById(identifier);
		if (op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		obj.setIdentifier(identifier);

		return dao.save(obj);
	}

	public List<WODClass> createWODClassFromTemplate(List<WODClass> classes) {
		List<WODClass> wodClassesSaved = new ArrayList<WODClass>();
		WODClass wodClassSaved;
		for (WODClass wodClass : classes) {
			wodClassSaved = dao.save(wodClass);
			wodClassesSaved.add(wodClassSaved);
		}
		return wodClassesSaved;
	}

	public WODClass findByIdentifier(String identifier) throws NotFoundException {
		Optional<WODClass> op = dao.findById(identifier);
		if (op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		return op.get();
	}

	public WODClass cancelWODClass(String identifier, String profileIdentifier)
			throws ProfileNotFoundException, NotFoundException, MaxCapacityReachedException {
		Optional<Profile> opProfile = profileDao.findById(profileIdentifier);
		Optional<WODClass> opClass = dao.findById(identifier);

		if (opProfile.isEmpty()) {
			throw new ProfileNotFoundException();

		}
		if (opClass.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		Profile profile = opProfile.get();
		WODClass wodClass = opClass.get();

		List<Profile> attendees = wodClass.getAttendees();
		List<Profile> cancellations = wodClass.getCancellations();

		if (attendees == null) {
			attendees = new ArrayList<Profile>();
		}
		attendees.remove(profile);
		cancellations.add(profile);
		wodClass.setAttendees(attendees);
		wodClass.setCancellations(cancellations);

		return dao.save(wodClass);

	}

	public WODClass bookWODClass(String identifier, String profileIdentifier)
			throws ProfileNotFoundException, NotFoundException, MaxCapacityReachedException {

		Optional<Profile> opProfile = profileDao.findById(profileIdentifier);
		Optional<WODClass> opClass = dao.findById(identifier);

		if (opProfile.isEmpty()) {
			throw new ProfileNotFoundException();

		}
		if (opClass.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		Profile profile = opProfile.get();
		WODClass wodClass = opClass.get();
		if (wodClass.getAttendees().size() == wodClass.getMaxCapacity()) {
			throw new MaxCapacityReachedException();
		}
		List<Profile> attendees = wodClass.getAttendees();
		List<Profile> cancellations = wodClass.getCancellations();

		if (attendees == null) {
			attendees = new ArrayList<Profile>();
		}
		cancellations.remove(profile);
		attendees.add(profile);
		wodClass.setCancellations(cancellations);
		wodClass.setAttendees(attendees);

		return dao.save(wodClass);

	}

	public WODClass didNotShowToWODClass(String identifier, String profileIdentifier)
			throws ProfileNotFoundException, NotFoundException, MaxCapacityReachedException {

		Optional<Profile> opProfile = profileDao.findById(profileIdentifier);
		Optional<WODClass> opClass = dao.findById(identifier);

		if (opProfile.isEmpty()) {
			throw new ProfileNotFoundException();

		}
		if (opClass.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		Profile profile = opProfile.get();
		WODClass wodClass = opClass.get();

		List<Profile> absents = wodClass.getAbsents();
		absents.add(profile);
		wodClass.setAbsents(absents);

		return dao.save(wodClass);

	}

	public WODClass deleteWODClass(String identifier) throws NotFoundException {
		Optional<WODClass> op = dao.findById(identifier);

		if (op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		WODClass obj = op.get();

		dao.delete(obj);

		return obj;
	}

	public List<Rank> retrieveRankingByAccountAndMonth(String accountIdentifier, Integer year, Integer month) {
		LocalDate startDate = LocalDate.of(year, month, 1);
		LocalDate endDate = startDate.withDayOfMonth(startDate.lengthOfMonth());
		Map<String, List<WODClass>> map = new HashMap<>();
		List<Rank> ranking = new ArrayList<>();
		Profile profile;
		Rank rank;
		List<WODClass> wodClasses = dao.findAllByAccountIdentifierAndDateBetween(accountIdentifier, startDate, endDate);

		for (WODClass wodClass : wodClasses) {
			for (Profile p : wodClass.getAttendees()) {
				if (!map.containsKey(p.getIdentifier())) {
					map.put(p.getIdentifier(), new ArrayList<>());
				}
				map.get(p.getIdentifier()).add(wodClass);
			}
		}
		for (String identifier : map.keySet()) {
			profile = profileDao.findById(identifier).orElseThrow();
			rank = new Rank(profile, map.get(identifier));
			ranking.add(rank);
		}

		ranking.sort(Comparator.comparing(Rank::getTotalClasses).reversed());

		return ranking;
	}

}
