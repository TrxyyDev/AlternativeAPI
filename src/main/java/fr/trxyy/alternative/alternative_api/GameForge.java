package fr.trxyy.alternative.alternative_api;

import fr.trxyy.alternative.alternative_api.utils.Forge;

/**
 * @author Trxyy
 */
public class GameForge {

	/**
	 * The forge launch target
	 */
	public static String launchTarget = "";
	/**
	 * The forge version
	 */
	public static String forgeversion = "";
	/**
	 * The minecraft version
	 */
	public static String mcVersion = "";
	/**
	 * The forge group
	 */
	public static String forgeGroup = "";
	/**
	 * The mcp version
	 */
	public static String mcpVersion = "";
	
	/**
	 * The Constructor
	 * @param launchTarget2 The launch target
	 * @param forgeVersion2 The forge version
	 * @param mcVersion2 The minecraft version
	 * @param forgeGroup2 The forge group
	 * @param mcpVersion2 The mcp version
	 */
	public GameForge(Forge forgeDefault, String mcVersion2, String forgeVersion2, String mcpVersion2) {
		launchTarget = forgeDefault.getTarget();
		forgeversion = forgeVersion2;
		mcVersion = mcVersion2;
		forgeGroup = forgeDefault.getGroup();
		mcpVersion = mcpVersion2;
	}

	/**
	 * @return The forge launch target
	 */
	public static String getLaunchTarget() {
		return launchTarget;
	}

	/**
	 * @return The forge version number
	 */
	public static String getForgeVersion() {
		return forgeversion;
	}

	/**
	 * @return The minecraft version
	 */
	public static String getMcVersion() {
		return mcVersion;
	}
	
	/**
	 * @return The forge group
	 */
	public static String getForgeGroup() {
		return forgeGroup;
	}
	
	/**
	 * @return The mcp version
	 */
	public static String getMcpVersion() {
		return mcpVersion;
	}
}
