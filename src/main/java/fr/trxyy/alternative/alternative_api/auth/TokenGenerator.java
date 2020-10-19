package fr.trxyy.alternative.alternative_api.auth;

import java.security.SecureRandom;

/**
 * @author Trxyy
 */
public class TokenGenerator {

	/**
	 * The SecureRandom
	 */
	protected static SecureRandom random = new SecureRandom();

	/**
	 * Generate a token
	 * @param username The username
	 * @return The username with a random
	 */
	public synchronized static String generateToken(String username) {
		long longToken = Math.abs(random.nextLong());
		String random = Long.toString(longToken, 16);
		return (username + ":" + random);
	}
}