package mph.beans.exceptions;

public class UsernameAlreadyExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9184425625882769844L;

	public UsernameAlreadyExistsException(String theCause) {
		super(theCause);
	}

}
