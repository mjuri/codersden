package uk.codersden.wod;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.profiles.Account;
import uk.codersden.hr.profiles.AccountDao;
import uk.codersden.hr.profiles.AccountNotFoundException;

@Service
public class ProgramService {
	@Autowired
	private ProgramDao dao;

	@Autowired
	private AccountDao accountDao;

	public List<Program> findAllByAccountIdentifier(String accountIdentifier) throws AccountNotFoundException {
		Optional<Account> op = accountDao.findById(accountIdentifier);
		if (op.isEmpty()) {
			throw new AccountNotFoundException(accountIdentifier + " not found");
		}

		List<Program> list = dao.findAllByAccountIdentifier(accountIdentifier);

		return list;
	}

	public Program createProgram(Program obj) {
		List<Program> preSavedProgram = dao.findAllByDateAndSourceAndType(obj.getDate(), obj.getSource(), obj.getType());
		String identifier;
		if(preSavedProgram.size() > 0) {
			identifier = preSavedProgram.get(0).getIdentifier();
			obj.setIdentifier(identifier);
		}
		return dao.save(obj);
	}

	public Program updateProgram(String identifier, Program obj) throws NotFoundException {
		Optional<Program> op = dao.findById(identifier);
		if (op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		obj.setIdentifier(identifier);

		return dao.save(obj);
	}

	public Program findByIdentifier(String identifier) throws NotFoundException {
		Optional<Program> op = dao.findById(identifier);
		if (op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		return op.get();
	}

	public Program deleteProgram(String identifier) throws NotFoundException {
		Optional<Program> op = dao.findById(identifier);

		if (op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		Program obj = op.get();

		dao.delete(obj);

		return obj;
	}
}
