package util;


/**
 * This class provides a static method to organize the information about a caught exception.
 */
public class ExceptionUtility {
	
	/**
	 * Print on screen a "nice" visualization of a caught exception
	 * @param e the caught exception
	 * @throws IllegalArgumentException if the exception given as parameter is null
	 */
	public static void showCaughtException(Exception e) throws IllegalArgumentException {
		
		if (e == null) {
			throw new IllegalArgumentException("The passed argument must not be null");
		}
		
		String aClassName = "none";
		String aCause = "none";
		String aMessage = "none";
		
		if (e.getClass() != null) {
			if (e.getClass().getSimpleName() != null) {
				aClassName = e.getClass().getSimpleName();
			}
		}
		
		if (e.getCause() != null) {
			aCause = e.getCause().getClass().getSimpleName();
		}
		
		if (e.getMessage() != null) {
			aMessage = e.getMessage();
		}
		
		System.out.println("[CATCHED] " + aClassName + " exception." + " [CAUSE]: " + aCause + " [MESSAGE]: " + aMessage);
	}

}
