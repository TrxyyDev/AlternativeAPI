package fr.trxyy.alternative.alternative_api;

/**
 * @author Trxyy
 */
public class GameConnect {
	
	/**
	 * The Ip adress of the server in question
	 */
	public String IP_ADRESS;
	
	/**
	 * The Port of the server in question
	 */
	public String PORT;
	
	/**
	 * The Constructor
	 * @param ip The server Ip
	 * @param p The server Port
	 */
	public GameConnect(String ip, String p) {
		this.IP_ADRESS = ip;
		this.PORT = p;
	}
	
	/**
	 * @return The Ip for direct connect to server
	 */
	public String getIp() {
		return this.IP_ADRESS;
	}

	/**
	 * @return The Port for direct connect to server
	 */
	public String getPort() {
		return this.PORT;
	}

}
