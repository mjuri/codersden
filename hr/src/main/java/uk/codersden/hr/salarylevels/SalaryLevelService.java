package uk.codersden.hr.salarylevels;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;

@Service
public class SalaryLevelService {
	
	@Autowired
	private SalaryLevelDao dao;
	
	public List<SalaryLevel> findAllSalaryLevelsByAccountIdentifier(String accountIdentifier) {
		return this.dao.findAllByAccountIdentifier(accountIdentifier);
	}

	public SalaryLevel createSalaryLevel(SalaryLevel salaryLevel) {
		return dao.save(salaryLevel);
	}

	public SalaryLevel updateSalaryLevel(String identifier, SalaryLevel salaryLevel) throws NotFoundException {
		Optional<SalaryLevel> op = dao.findById(identifier);
		if(op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		salaryLevel.setIdentifier(identifier);
		return dao.save(salaryLevel);
	}
}
