package uk.codersden.wod.achievements;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.profiles.Account;
import uk.codersden.hr.profiles.AccountDao;
import uk.codersden.hr.profiles.AccountNotFoundException;

@Service
public class AchievementService {
	@Autowired
	private AchievementDao dao;
	
	@Autowired
	private AthleteAchievementDao athleteAchievementDao;

	@Autowired
	private AccountDao accountDao;

	public List<Achievement> findAllByAccountIdentifier(String accountIdentifier) throws AccountNotFoundException {
		Optional<Account> op = accountDao.findById(accountIdentifier);
		if (op.isEmpty()) {
			throw new AccountNotFoundException(accountIdentifier + " not found");
		}

		List<Achievement> list = dao.findAll();

		return list;
	}

	public Achievement createAchievement(Achievement obj) {
		return dao.save(obj);
	}

	public Achievement updateAchievement(String identifier, Achievement obj) throws NotFoundException {
		Optional<Achievement> op = dao.findById(identifier);
		if (op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		obj.setIdentifier(identifier);

		return dao.save(obj);
	}

	public Achievement findByIdentifier(String identifier) throws NotFoundException {
		Optional<Achievement> op = dao.findById(identifier);
		if (op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		return op.get();
	}

	public Achievement deleteAchievement(String identifier) throws NotFoundException {
		Optional<Achievement> op = dao.findById(identifier);

		if (op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		Achievement obj = op.get();

		dao.delete(obj);

		return obj;
	}

	public AthleteAchievement createAthleteAchievement(AthleteAchievement obj) {
		// Check if athlete has already this achievement;
		obj.setUnlocked(true);
		
		return athleteAchievementDao.save(obj);
	}

	public List<AthleteAchievement> findAllByAthleteAndUnlock(String profileIdentifier, Boolean isUnlocked) {
		return athleteAchievementDao.findAllByProfileIdentifierAndUnlocked(profileIdentifier, isUnlocked);
	}
}
