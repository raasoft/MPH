package mph.beans.exceptions;

public class PasswordMismatchException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9184425625882769844L;

	public PasswordMismatchException(String theCause) {
		super(theCause);
	}

}
