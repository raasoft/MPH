package mph.beans.exceptions;

public class UserNotLoggedInException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9184425625882769844L;

	public UserNotLoggedInException(String theCause) {
		super(theCause);
	}

}
