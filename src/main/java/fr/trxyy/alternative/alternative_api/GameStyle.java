package fr.trxyy.alternative.alternative_api;

/**
 * @author Trxyy
 */
public enum GameStyle {
	
	VANILLA("net.minecraft.client.main.Main", "", ""),
	VANILLA_PLUS("net.minecraft.client.main.Main", "", ""),
	FORGE("Will be chosen after.", "", ""),
	OPTIFINE("net.minecraft.launchwrapper.Launch", "optifine.OptiFineTweaker", ""),
	FORGE_1_7_10_OLD("net.minecraft.launchwrapper.Launch", "cpw.mods.fml.common.launcher.FMLTweaker", ""),
	FORGE_1_8_TO_1_12_2("net.minecraft.launchwrapper.Launch", "net.minecraftforge.fml.common.launcher.FMLTweaker", ""),
	FORGE_1_13_HIGHER("cpw.mods.modlauncher.Launcher", "", "--launchTarget ${launch_target_fml} --fml.forgeVersion ${forge_version_fml} --fml.mcVersion ${mc_version_fml} --fml.forgeGroup ${forge_group_fml} --fml.mcpVersion ${mcp_version_fml}");
	/**
	 * The main class
	 */
	private String mainClass;
	/**
	 * The tweak arguments
	 */
	private String tweakArgument;
	/**
	 * The GameStyle specifics arguments to use
	 */
	private String specificsArguments;

	/**
	 * The Constructor
	 * @param name The style name
	 * @param main The main class
	 * @param tweak The tweak argument
	 * @param args The GameStyle specifics arguments
	 */
	private GameStyle(String main, String tweak, String args) {
		this.mainClass = main;
		this.tweakArgument = tweak;
		this.specificsArguments = args;
	}
	/**
	 * @return The main class
	 */
	public String getMainClass() {
		return this.mainClass;
	}

	/**
	 * @return The tweak argument
	 */
	public String getTweakArgument() {
		return this.tweakArgument;
	}

	/**
	 * @return The specifics arguments
	 */
	public String getSpecificsArguments() {
		return this.specificsArguments;
	}

}
