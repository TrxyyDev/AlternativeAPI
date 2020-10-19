package fr.trxyy.alternative.alternative_api;

/**
 * @author Trxyy
 */
public enum GameStyle {
	
	VANILLA("Vanilla", "net.minecraft.client.main.Main", "", ""),
	VANILLA_PLUS("VanillaPlus", "net.minecraft.client.main.Main", "", ""),
	OPTIFINE("OptiFine", "net.minecraft.launchwrapper.Launch", "optifine.OptiFineTweaker", ""),
	FORGE_1_7_10_OLD("Forge 1.7.10-", "net.minecraft.launchwrapper.Launch", "cpw.mods.fml.common.launcher.FMLTweaker", ""),
	FORGE_1_8_TO_1_12_2("Forge 1.8 -> 1.12.2", "net.minecraft.launchwrapper.Launch", "net.minecraftforge.fml.common.launcher.FMLTweaker", ""),
	FORGE_1_13_HIGHER("Forge 1.13+", "cpw.mods.modlauncher.Launcher", "", "--launchTarget ${launch_target_fml} --fml.forgeVersion ${forge_version_fml} --fml.mcVersion ${mc_version_fml} --fml.forgeGroup ${forge_group_fml} --fml.mcpVersion ${mcp_version_fml}"),
	ALTERNATIVE("OptiFine", "fr.trxyy.alternativewrapper.Launch", "fr.trxyy.alternative.AlternativeTweaker", "");
	
	/**
	 * The name
	 */
	private String name;
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
	private GameStyle(String name, String main, String tweak, String args) {
		this.name = name;
		this.mainClass = main;
		this.tweakArgument = tweak;
		this.specificsArguments = args;
	}
	
	/**
	 * @return The tweak name
	 */
	public String getTweakName() {
		return this.name;
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
