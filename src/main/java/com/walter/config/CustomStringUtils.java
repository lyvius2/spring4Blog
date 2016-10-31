package com.walter.config;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yhwang131 on 2016-10-31.
 */
public final class CustomStringUtils extends StringUtils {
	public static boolean isNumber(String str) {
		boolean result;
		try {
			Double.parseDouble(str);
			result = true;
		} catch(Exception e) {
			result = false;
		}
		return result;
	}

	public static int setDefaultNumber(String str, int def) {
		if(isNumber(str)) {
			return Integer.parseInt(str);
		} else {
			return def;
		}
	}

	public static String dateToString(Date date, String dateFormat) {
		try {
			DateFormat df = new SimpleDateFormat(dateFormat);
			return df.format(date);
		} catch(Exception e) {
			return null;
		}
	}
}
