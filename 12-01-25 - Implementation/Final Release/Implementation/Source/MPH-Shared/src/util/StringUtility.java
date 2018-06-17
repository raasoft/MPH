package util;

import mph.beans.exceptions.InvalidArgumentException;

/**
 * This class provides static methods to validate strings.
 */
public class StringUtility {

	/**
	 * Check whether the string given as parameter is null or empty
	 * @param theString the string to check
	 * @param theLabel the label of the attribute represented by the string
	 * @throws InvalidArgumentException InvalidArgumentException if the given string is null or empty
	 */
	public static void isNullOrEmpty(String theString, String theLabel) throws InvalidArgumentException {

		ObjectUtility.isNull(theString);
		ObjectUtility.isNull(theLabel);

		if (theString.length() == 0) {
			throw new InvalidArgumentException(theLabel + " must not be empty");
		}
	}

	/**
	 * Check whether the string given as parameter respect the desired maximum length
	 * @param theString the string to check
	 * @param theLabel the label of the attribute represented by the string
	 * @param theMaxSize the maximum length the string should have
	 * @throws InvalidArgumentException if the string given as parameter exceeds the desired maximum size
	 */
	public static void checkSize(String theString, String theLabel, int theMaxSize) throws InvalidArgumentException {

		if (theString == null)
			return;

		if (theString.length() > theMaxSize) {
			throw new InvalidArgumentException("Length of '" + theLabel + "' attribute must not exceed " + theMaxSize + " characters");
		}
	}

}
