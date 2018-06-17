package mph.beans.exceptions;

import java.io.Serializable;

public class InvalidArgumentException extends Exception implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9184425625882769844L;

	public InvalidArgumentException(String theCause) {
		super(theCause);
	}

}
