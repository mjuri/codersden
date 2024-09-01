package uk.codersden.hr.login;

public class PasswordsDoesNotMatchException extends Exception {
	private static final long serialVersionUID = 1L;

	public PasswordsDoesNotMatchException(String userName) {
		super("Wrong password for username: " + userName);
	}
}
