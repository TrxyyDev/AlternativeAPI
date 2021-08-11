package fr.trxyy.alternative.alternative_api.utils.config;

public class ConfigVersion {

	
	private static ConfigVersion instance;
	
	/**
	 * The username
	 */
	public String username;
	/**
	 * The password
	 */
	public String password;
	/**
	 * The password
	 */
	public boolean rememberme;
	/**
	 * The RAM
	 */
	public String allocatedram;
	/**
	 * The VERSION
	 */
	  public String version;
	/**
	 * 
	 /* Using forge ?
	  */
	  public boolean useforge;
	  /*
	   * 
	   */
	 /* The game size
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
		instance = o;
		this.username = o.username;
		this.allocatedram = o.allocatedram;
		this.version = o.version;
		this.useforge = o.useforge;
		this.gamesize = o.gamesize;
		this.autologin = o.autologin;
		this.vmarguments = o.vmarguments;
		this.usevmarguments = o.usevmarguments;
		this.password = o.password;
		this.rememberme = o.rememberme;

	}

	/**
	 * Update multiple values in the config json
	 */
	public String getAllocatedRam() {
		return this.allocatedram;
	}

	public String getVersion() {
		return version;
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
	
	public boolean useForge() 
	{
		return this.useforge;
	}
	
	public boolean isRememberme() {
		return rememberme;
	}

	public static ConfigVersion getInstance() {
		return instance;
	}
	
}
