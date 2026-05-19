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
		if(op.isEmpty()) {
			throw new AccountNotFoundException(accountIdentifier + " not found");
		}
		
		List<Program> list = dao.findAllByAccountIdentifier(accountIdentifier);
		
		return list;
	}
	public Program createProgram(Program program) {
		return dao.save(program);
	}

	public Program updateProgram(String programIdentifier, Program program) throws NotFoundException {
		Optional<Program> op = dao.findById(programIdentifier);
		if(op.isEmpty()){
			throw new NotFoundException(programIdentifier + " not found");
		}
		program.setIdentifier(programIdentifier);
		
		return dao.save(program);
	}
	
	public Program findByIdentifier(String identifier) throws NotFoundException {
		Optional<Program> op = dao.findById(identifier);
		if(op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		return op.get();
	}
	
	public Program deleteProgram(String identifier) throws NotFoundException {
		Optional<Program> op = dao.findById(identifier);

		if(op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		Program program = op.get();
		
		dao.delete(program);
		
		
		return program;
	}
}
