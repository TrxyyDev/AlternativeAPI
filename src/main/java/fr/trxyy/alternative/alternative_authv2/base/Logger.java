package fr.trxyy.alternative.alternative_authv2.base;

/**
 * @author Trxyy
 */
public class Logger {

	/**
	 * Log a text
	 * @param s The text to log
	 */
	public static void log(String s) {
		System.out.println(getName() + s);
	}

	/**
	 * Log a text with error
	 * @param s The text to log
	 */
	public static void err(String s) {
		System.err.println(getName() + s);
	}
	
	private static String getName() {
		return "[AAuth]";
	}

}
