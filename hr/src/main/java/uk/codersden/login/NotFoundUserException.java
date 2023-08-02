package uk.codersden.login;

public class NotFoundUserException extends Exception {

	public NotFoundUserException(String userName) {
		super("Not found user: " + userName);
	}

	private static final long serialVersionUID = 1L;

}
