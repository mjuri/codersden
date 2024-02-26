package uk.codersden.hr.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/settings")
public class SettingsController {

	@Autowired
	private SettingsService service;
	
	@GetMapping("/{accountIdentifier}")
	public ResponseEntity<?> retrieveSettingsForAccount(@PathVariable("accountIdentifier") String accountIdentifier){
		Settings settings;
		try {
			settings = service.retrieveSettingsByAccount(accountIdentifier);
		}catch(SettingsNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
		}
		return ResponseEntity.ok(settings);
	}
	
	@CrossOrigin
	@PutMapping("/{identifier}")
	public ResponseEntity<?> updateLead(@PathVariable("identifier") String identifier, @RequestBody Settings settings) {
		Settings udatedSettings;
		try {
			udatedSettings = service.updateSettings(identifier, settings);
		} catch ( SettingsNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
		}
		
		return ResponseEntity.ok(udatedSettings);
	}
	@CrossOrigin
	@PostMapping
	public ResponseEntity<?> createSettings(@RequestBody Settings settings) {
		Settings e = service.saveSettings(settings);
		
		return ResponseEntity.ok(e);
	}
}



