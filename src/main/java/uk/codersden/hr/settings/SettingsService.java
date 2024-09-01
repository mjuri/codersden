package uk.codersden.hr.settings;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uk.codersden.hr.leads.Lead;
import uk.codersden.hr.leads.LeadNotFoundException;

@Service
public class SettingsService {
	
	@Autowired
	private SettingsDao dao;
	
	public Settings retrieveSettingsByAccount(String identifier) throws SettingsNotFoundException {
		Optional<Settings> op = dao.findById(identifier);
		if(op.isEmpty()) {
			throw new SettingsNotFoundException(identifier);
		}
		
		return op.get();
	}

	public Settings saveSettings(Settings settings) {
		return dao.save(settings);
	}

	public Settings updateSettings(String identifier, Settings settings) throws SettingsNotFoundException {
		Optional<Settings> op = dao.findById(identifier);
		if(op.isEmpty()) {
			throw new SettingsNotFoundException(identifier);
		}
		settings.setIdentifier(identifier);
		return dao.save(settings);
	}
}
