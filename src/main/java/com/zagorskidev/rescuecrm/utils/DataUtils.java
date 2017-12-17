package com.zagorskidev.rescuecrm.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Utility class containing common operations on application data.
 * @author tomek
 *
 */
public class DataUtils {

	/**
	 * Translates native strings to English alphabet.
	 * @param input
	 * @return
	 */
	public static String removeDiacritics(String input) {
		
		String output = null;
		
		if(input == null)
			return output;
		
		output = StringUtils
				.stripAccents(input)
				.replace('ł', 'l')
				.replace('Ł', 'L');
		
		return output;
	}
}
