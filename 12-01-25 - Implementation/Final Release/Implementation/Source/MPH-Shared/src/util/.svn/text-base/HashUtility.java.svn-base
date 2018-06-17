package util;

import mph.beans.exceptions.InvalidArgumentException;

/**
 * This class provides a static method to generate the MD5 hash of a string.
 */
public class HashUtility {
	
	/**
	 * @param md5 the string for which will be calculated the MD5 hash
	 * @return the MD5 hash of the string given as parameter
	 * @throws InvalidArgumentException if the string given as parameter is not valid
	 */
	static public String MD5(String md5) throws InvalidArgumentException {
		
		try {
			
			ObjectUtility.isNull(md5);
			
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		} catch (java.security.NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
