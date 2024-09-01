package uk.codersden.hr.settings;

public class SettingsNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public SettingsNotFoundException(String identifier) {
		super("Settings not found for account " + identifier);
	}

}
