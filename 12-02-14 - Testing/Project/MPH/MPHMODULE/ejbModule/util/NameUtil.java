package util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class NameUtil {
	
	public static String encode(String s) {
		try {
			s = URLEncoder.encode(s, "UTF-8").replaceAll("\\*", "%2A");
			if(s.equals(".")) {
				s = "%2E";
			} else if(s.equals("..")) {
				s = "%2E%2E";
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return s;
	}

}
