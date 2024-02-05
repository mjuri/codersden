package uk.codersden.hr.leads;

public class LeadNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public LeadNotFoundException(String message) {
		super(message);
	}

}
