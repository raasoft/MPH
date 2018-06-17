package util;

import java.util.Calendar;

/**
 * This class provides static methods to compare dates.<br/>
 * It fixes the default behavior of {@link java.sql.Date#before(java.util.Date)} which returns true if applied to the same date
 */
public class CalendarUtility {

	
	/**
	 * @param theStartDate
	 * @param theEndDate
	 * @return the number of days between the dates given as parameters
	 */
	public static long daysBetween(final java.sql.Date theStartDate, final java.sql.Date theEndDate) {  
	
		if (theStartDate.equals(theEndDate))
			return 0;

		
		Calendar startDate = Calendar.getInstance(); 
		Calendar endDate = Calendar.getInstance(); 
		
		int sign = 1;
		
		if (theStartDate.after(theEndDate)) {
			startDate.setTime(new java.util.Date(theEndDate.getTime()));
			endDate.setTime(new java.util.Date(theStartDate.getTime()));
			sign = -1;
		} else {
			endDate.setTime(new java.util.Date(theEndDate.getTime()));
			startDate.setTime(new java.util.Date(theStartDate.getTime()));
		}
		
		Calendar sDate = (Calendar) startDate.clone();  
	    	
		long daysBetween = 0;  
	  
	    int y1 = sDate.get(Calendar.YEAR);  
	    int y2 = endDate.get(Calendar.YEAR);  
	    int m1 = sDate.get(Calendar.MONTH);  
	    int m2 = endDate.get(Calendar.MONTH);  
	  
	    //**year optimization**  
	    while (((y2 - y1) * 12 + (m2 - m1)) > 12) {  
	  
	        //move to Jan 01  
	        if ( sDate.get(Calendar.MONTH) == Calendar.JANUARY  
	             && sDate.get(Calendar.DAY_OF_MONTH) == sDate.getActualMinimum(Calendar.DAY_OF_MONTH)) {  
	  
	            daysBetween += sDate.getActualMaximum(Calendar.DAY_OF_YEAR);  
	            sDate.add(Calendar.YEAR, 1);  
	        } else {  
	            int diff = 1 + sDate.getActualMaximum(Calendar.DAY_OF_YEAR) - sDate.get(Calendar.DAY_OF_YEAR);  
	            sDate.add(Calendar.DAY_OF_YEAR, diff);  
	            daysBetween += diff;  
	        }  
	        y1 = sDate.get(Calendar.YEAR);  
	    }  
	  
	    //** optimize for month **  
	    //while the difference is more than a month, add a month to start month  
	    while ((m2 - m1) % 12 > 1) {  
	        daysBetween += sDate.getActualMaximum(Calendar.DAY_OF_MONTH);  
	        sDate.add(Calendar.MONTH, 1);  
	        m1 = sDate.get(Calendar.MONTH);  
	    }
	    	    
	    // process remainder date  
	    while (sDate.before(endDate)) {  
	        sDate.add(Calendar.DAY_OF_MONTH, 1);  
	        daysBetween++;  
	    }  
	 
		  return daysBetween * sign;  
	}  
	
	/**
	 * @param theFirstDate
	 * @param theLastDate
	 * @return true if theFirstDate follows theLastDate
	 */
	public static boolean after(final java.sql.Date theFirstDate, final java.sql.Date theLastDate) {
		return daysBetween(theFirstDate, theLastDate) > 0;
	}
	
	/**
	 * @param theFirstDate
	 * @param theLastDate
	 * @return true if theFirstDate follows or is equal theLastDate
	 */
	public static boolean afterOrEqual(final java.sql.Date theFirstDate, final java.sql.Date theLastDate) {
		return daysBetween(theFirstDate, theLastDate) >= 0;
	}
	
	
	/**
	 * @param theFirstDate
	 * @param theLastDate
	 * @return true if theFirstDate precedes theLastDate
	 */
	public static boolean before(final java.sql.Date theFirstDate, final java.sql.Date theLastDate) {
		return daysBetween(theFirstDate, theLastDate) < 0;
	}
	
	/**
	 * @param theFirstDate
	 * @param theLastDate
	 * @return true if theFirstDate precedes or is equal theLastDate
	 */
	public static boolean beforeOrEqual(final java.sql.Date theFirstDate, final java.sql.Date theLastDate) {
		return daysBetween(theFirstDate, theLastDate) <= 0;
	}
	
	/**
	 * @param theFirstDate
	 * @param theLastDate
	 * @return true if theFirstDate and theLastDate refer to the same day
	 */
	public static boolean equal(final java.sql.Date theFirstDate, final java.sql.Date theLastDate) {
		return daysBetween(theFirstDate, theLastDate) == 0;
	}
	
	
}
