package uk.codersden.wod.templates;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.NotFoundException;
import uk.codersden.hr.profiles.Account;
import uk.codersden.hr.profiles.AccountDao;
import uk.codersden.hr.profiles.AccountNotFoundException;

@Service
public class TemplateService {
	@Autowired
	private TemplateDao dao;

	@Autowired
	private AccountDao accountDao;

	public List<Template> findAllByAccountIdentifier(String accountIdentifier) throws AccountNotFoundException {
		Optional<Account> op = accountDao.findById(accountIdentifier);
		if (op.isEmpty()) {
			throw new AccountNotFoundException(accountIdentifier + " not found");
		}

		List<Template> list = dao.findAllByAccountIdentifier(accountIdentifier);

		return list;
	}

	public Template createTemplate(Template obj) {
		return dao.save(obj);
	}

	public Template updateTemplate(String identifier, Template obj) throws NotFoundException {
		Optional<Template> op = dao.findById(identifier);
		if (op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		obj.setIdentifier(identifier);

		return dao.save(obj);
	}

	public Template findByIdentifier(String identifier) throws NotFoundException {
		Optional<Template> op = dao.findById(identifier);
		if (op.isEmpty()) {
			throw new NotFoundException(identifier + " not found");
		}
		return op.get();
	}

	public Template deleteTemplate(String identifier) throws NotFoundException {
		Optional<Template> op = dao.findById(identifier);

		if (op.isEmpty()) {
			throw new NotFoundException(identifier);
		}
		Template obj = op.get();

		dao.delete(obj);

		return obj;
	}
}
