package util;

import mph.beans.exceptions.InvalidArgumentException;

/**
 * This class provides static methods to validate objects.
 */
public class ObjectUtility {
	
	/**
	 * Check whether the object given as parameter is null
	 * @param theObject the object to check
	 * @throws InvalidArgumentException if the object given as parameter is null
	 */
	public static void isNull(Object theObject) throws InvalidArgumentException {
		if (theObject == null) {
			System.out.println("THROWN INVALIDARGUMENTEXCEPTION - The argument passed must not be null.");
			throw new InvalidArgumentException("The argument passed must not be null");
		}
	}

}
