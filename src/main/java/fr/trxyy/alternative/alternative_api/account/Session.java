package fr.trxyy.alternative.alternative_api.account;

/**
 * @author Trxyy
 */
public class Session {

	/**
	 * The username
	 */
	public String username;
	/**
	 * The token
	 */
	public String token;
	/**
	 * The UUID
	 */
	public String uuId;

	/**
	 * The Constructor
	 */
	public Session() {}

	/**
	 * The Constructor
	 * @param user The username
	 * @param tken The token
	 * @param uid The uuid
	 */
	public Session(String user, String tken, String uid) {
		this.username = user;
		this.token = tken;
		this.uuId = uid;
	}

	/**
	 * The Constructor
	 * @param same The Session
	 */
	public Session(Session same) {
		this.username = same.username;
		this.token = same.token;
		this.uuId = same.uuId;
	}

	/**
	 * @return The username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * @return The token
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * @return The UUID
	 */
	public String getUuid() {
		return uuId;
	}
	/**
	 * Set the username
	 * @param name The username
	 */
	public void setUsername(String name) {
		this.username = name;
	}
	
	/**
	 * Set the token
	 * @param tkn The token
	 */
	public void setToken(String tkn) {
		this.token = tkn;
	}
	
	/**
	 * Set the UUID
	 * @param id The UUID
	 */
	public void setUuid(String id) {
		this.uuId = id;
	}

}
