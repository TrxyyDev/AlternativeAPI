package fr.trxyy.alternative.alternative_api.utils.config;

public class ConfigVersion {

	/**
	 * The username
	 */
	public String username;
	/**
	 * The RAM
	 */
	public String allocatedram;
	/**
	 * The game size
	 */
	public String gamesize;
	/**
	 * Is auto Login
	 */
	public boolean autologin;
	/**
	 * The VM arguments
	 */
	public String vmarguments;
	/**
	 * Use custom vm arguments 
	 */
	public boolean usevmarguments;

	/**
	 * The Constructor
	 */
	public ConfigVersion(ConfigVersion o) {
		this.username = o.username;
		this.allocatedram = o.allocatedram;
		this.gamesize = o.gamesize;
		this.autologin = o.autologin;
		this.vmarguments = o.vmarguments;
		this.usevmarguments = o.usevmarguments;
	}

	/**
	 * Update multiple values in the config json
	 */
	public String getAllocatedRam() {
		return this.allocatedram;
	}

	/**
	 * Get the game size
	 */
	public String getGameSize() {
		return this.gamesize;
	}

	/**
	 * Is using autoLogin
	 */
	public boolean isAutoLogin() {
		return this.autologin;
	}

	/**
	 * Get the username
	 */
	public String getUsername() {
		return this.username;
	}
	
	/**
	 * Get the VM arguments
	 */
	public String getVMArguments() {
		return this.vmarguments;
	}
	
	/**
	 * Is using custom vm arguments
	 */
	public boolean useVMArguments() {
		return this.usevmarguments;
	}

}
