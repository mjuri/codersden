package uk.codersden.hr;


public class NotFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public NotFoundException(String identifier) {
		super(identifier);
	}
}
