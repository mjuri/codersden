package uk.codersden.wod.pr;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.profiles.Account;
import uk.codersden.hr.profiles.AccountDao;
import uk.codersden.hr.profiles.AccountNotFoundException;
import uk.codersden.hr.profiles.Profile;
import uk.codersden.hr.profiles.ProfileDao;

@Service
public class PersonalRecordService {
	@Autowired
	private PersonalRecordDao dao;

	@Autowired
	private ProfileDao profileDao;
	@Autowired
	private AccountDao accountDao;

	public List<PersonalRecord> findAllByProfileIdentifier(String profileIdentifier) throws AccountNotFoundException {
		Optional<Profile> op = profileDao.findById(profileIdentifier);
		if (op.isEmpty()) {
			throw new AccountNotFoundException(profileIdentifier + " not found");
		}

		List<PersonalRecord> list = dao.findAllByProfileIdentifierOrderByDateDesc(profileIdentifier);

		return list;
	}

	public PersonalRecord createPersonalRecord(PersonalRecord pb) {
		List<PersonalRecord> previousPBs = 
				dao.findAllByProfileIdentifierAndTypeOrderByDateDesc(pb.getProfileIdentifier(), pb.getType());
		if(previousPBs.size() > 0) {
			PersonalRecord previous = previousPBs.get(0);
			pb.setPreviousValue(previous.getValue());
		}
		return dao.save(pb);
	}

	public PersonalRecord updatePersonalRecord(String identifier, PersonalRecord obj) throws NotFoundException {
		Optional<PersonalRecord> op = dao.findById(identifier);
		if (op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		obj.setIdentifier(identifier);

		return dao.save(obj);
	}

	public PersonalRecord findByIdentifier(String identifier) throws NotFoundException {
		Optional<PersonalRecord> op = dao.findById(identifier);
		if (op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		return op.get();
	}

	public PersonalRecord deletePersonalRecord(String identifier) throws NotFoundException {
		Optional<PersonalRecord> op = dao.findById(identifier);

		if (op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		PersonalRecord obj = op.get();

		dao.delete(obj);

		return obj;
	}
}
