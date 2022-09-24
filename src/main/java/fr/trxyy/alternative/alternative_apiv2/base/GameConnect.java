package fr.trxyy.alternative.alternative_apiv2.base;

/**
 * @author Trxyy
 */
public class GameConnect {
	
	/**
	 * The Ip adress of the server in question
	 */
	public String ip;
	
	/**
	 * The Port of the server in question
	 */
	public String port;
	
	/**
	 * The Constructor
	 * @param i The server Ip
	 * @param p The server Port
	 */
	public GameConnect(String i, String p) {
		this.ip = i;
		this.port = p;
	}
	
	/**
	 * @return The Ip for direct connect to server
	 */
	public String getIp() {
		return this.ip;
	}

	/**
	 * @return The Port for direct connect to server
	 */
	public String getPort() {
		return this.port;
	}

}
