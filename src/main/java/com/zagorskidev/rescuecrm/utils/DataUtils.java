package com.zagorskidev.rescuecrm.utils;

import org.apache.commons.lang3.StringUtils;

public class DataUtils {

	public static String removeDiactrics(String input) {
		
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
