package fr.trxyy.alternative.alternative_api.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * @author Trxyy
 */
public class Logger {
	
	/**
	 * Log Lines for console
	 */
	private static ArrayList<String> logLines = new ArrayList<String>();

	/**
	 * Log a text
	 * @param s The text to log
	 */
	public static void log(String s) {
		System.out.println(getTime() + s);
		logLines.add(getTime() + s);
	}

	/**
	 * Log a text with error
	 * @param s The text to log
	 */
	public static void err(String s) {
		System.err.println(getTime() + s);
		logLines.add(getTime() + "[ERROR]" + s);
	}

	/**
	 * @return The current time
	 */
	private static String getTime() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return "[" + sdf.format(cal.getTime()) + "]";
	}
	
	/**
	 * @return The lines for the console
	 */
	public static String getLines() {
		String s = "";
		for (int i = 0; i < logLines.size(); i++) {
			s = s + "\n" + logLines.get(i);
		}
		return s;
	}

}
