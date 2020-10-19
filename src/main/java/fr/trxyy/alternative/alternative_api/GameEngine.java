package fr.trxyy.alternative.alternative_api;

import fr.trxyy.alternative.alternative_api.maintenance.GameMaintenance;
import fr.trxyy.alternative.alternative_api.minecraft.json.MinecraftVersion;
import fr.trxyy.alternative.alternative_api.updater.GameUpdater;
import javafx.stage.Stage;

/**
 * @author Trxyy
 */
public class GameEngine {
	
	/**
	 * The GameFolder, folder hierarchy
	 */
	private GameFolder gameFolder;
	/**
	 * The LauncherPreferences, the name, size, ismoveable, resourceLocation
	 */
	private LauncherPreferences launcherSize;
	/**
	 * The GameVersion, version of the game
	 */
	private GameVersion gameVersion;
	/**
	 * The GameStyle, style to launcher Minecraft: vanilla, vanilla plus, optifine, forge
	 */
	private GameStyle gameStyle;
	/**
	 * The GameSize, Size of the game to launch
	 */
	private GameSize gameSize;
	/**
	 * The GameLinks, the links for the json, ignore, delete & status file
	 */
	private GameLinks gameLinks;
	/**
	 * The GameForge, register the new forge arguments
	 */
	private GameForge gameForge;
	/**
	 * The GameConnect, direct connect to a specified server
	 */
	private GameConnect gameConnect;
	/**
	 * The GameMemory; the RAM allocated
	 */
	private GameMemory gameMemory;
	/**
	 * The GameUpdater, the updater (downloading required files)
	 */
	private GameUpdater gameUpdater;
	/**
	 * The GameArguments, Addons launch arguments (game)
	 */
	private GameArguments gameArguments;
	/**
	 * The JVMArguments, Addons launch arguments (jvm)
	 */
	private JVMArguments jvmArgs;
	/**
	 * The GameMaintenance, don't open launcher and display a message when maintenance is active
	 */
	private GameMaintenance gameMaintenance;
	/**
	 * The Stage, the stage in question to use in other class.
	 */
	private Stage fakeBase;
	/**
	 * MinecraftVersion, the Minecraft version from the json
	 */
	private MinecraftVersion minecraftVersion;
	
	/**
	 * The Constructor
	 * @param folder The GameFolder for the folder name in APPDATA
	 * @param lSize The preferences for the Stage title, size etc..
	 * @param version The Version of the game
	 * @param style The GameStyle to launch Minecraft vanilla of modded
	 * @param size The GameSize to change the game window size to launch
	 */
	public GameEngine(GameFolder folder, LauncherPreferences lSize, GameVersion version, GameStyle style, GameSize size) {
		this.gameFolder = folder;
		this.launcherSize = lSize;
		this.gameVersion = version;
		this.gameStyle = style;
		this.gameSize = size;
	}
	/**
	 * The Constructor
	 * @param folder The GameFolder for the folder name in APPDATA
	 * @param lSize The preferences for the Stage title, size etc..
	 * @param version The Version of the game
	 * @param style The GameStyle to launch Minecraft vanilla of modded
	 */
	public GameEngine(GameFolder folder, LauncherPreferences lSize, GameVersion version, GameStyle style) {
		this.gameFolder = folder;
		this.launcherSize = lSize;
		this.gameVersion = version;
		this.gameStyle = style;
		this.gameSize = GameSize.DEFAULT;
	}
	
	/**
	 * Register some things...
	 * @param links Register download links
	 * @param forge Register the forge version/arguments
	 * @param connect Register server ip and port
	 * @param memory Register the memory (RAM) to launch
	 */
	public void reg(GameLinks links, GameForge forge, GameConnect connect, GameMemory memory) {
		this.gameLinks = links;
		this.gameForge = forge;
		this.gameConnect = connect;
		this.gameMemory = memory;
	}
	
	/**
	 * Register some things...
	 * @param links Register download links
	 * @param connect Register server ip and port
	 * @param memory Register the memory (RAM) to launch
	 */
	public void reg(GameLinks links, GameConnect connect, GameMemory memory) {
		this.gameLinks = links;
		this.gameForge = null;
		this.gameConnect = connect;
		this.gameMemory = memory;
	}
	
	/**
	 * Register some things...
	 * @param links Register download links
	 * @param connect Register server ip and port
	 */
	public void reg(GameLinks links, GameConnect connect) {
		this.gameLinks = links;
		this.gameForge = null;
		this.gameConnect = connect;
	}
	
	/**
	 * Register some things...
	 * @param jvArgs Register Java arguments (addons)
	 */
	public void reg(JVMArguments jvArgs) {
		this.jvmArgs = jvArgs;
	}
	
	/**
	 * Register some things...
	 * @param version Register The minecraft version from json
	 */
	public void reg(MinecraftVersion version) {
		this.minecraftVersion = version;
	}
	
	/**
	 * Register some things...
	 * @param links Register download links
	 */
	public void reg(GameLinks links) {
		this.gameLinks = links;
		this.gameForge = null;
	}
	
	/**
	 * Register some things...
	 * @param siz register game window size
	 */
	public void reg(GameSize siz) {
		this.gameSize = siz;
	}
	
	/**
	 * Register some things...
	 * @param updater Register the updater
	 */
	public void reg(GameUpdater updater) {
		this.gameUpdater = updater;
	}
	
	/**
	 * Register some things...
	 * @param connect Register server ip and port 
	 */
	public void reg(GameConnect connect) {
		this.gameForge = null;
		this.gameConnect = connect;
	}
	
	/**
	 * Register some things...
	 * @param forge Register the forge version/arguments
	 */
	public void reg(GameForge forge) {
		this.gameForge = forge;
	}
	
	/**
	 * Register some things...
	 * @param memory Register the Memory (RAM) to launch
	 */
	public void reg(GameMemory memory) {
		this.gameMemory = memory;
	}
	
	/**
	 * Register some things...
	 * @param arguments Register game arguments (addons)
	 */
	public void reg(GameArguments arguments) {
		this.gameArguments = arguments;
	}
	
	/**
	 * Register some things...
	 * @param base Register the current stage to use
	 */
	public void reg(Stage base) {
		this.fakeBase = base;
	}
	
	/**
	 * @param maintenance Register the maintenance
	 */
	public void reg(GameMaintenance maintenance) {
		this.gameMaintenance = maintenance;
	}
	
	/**
	 * @return The Stage
	 */
	public Stage getStage() {
		return this.fakeBase;
	}
	
	/**
	 * @return The launcher preferences
	 */
	public LauncherPreferences getLauncherPreferences() {
		return this.launcherSize;
	}
	
	/**
	 * @return The launcher Width
	 */
	public int getWidth() {
		return this.launcherSize.getWidth();
	}
	
	/**
	 * @return The launcher Height
	 */
	public int getHeight() {
		return this.launcherSize.getHeight();
	}
	
	/**
	 * @return The version of the game
	 */
	public GameVersion getGameVersion() {
		return this.gameVersion;
	}
	
	/**
	 * @return The Forge arguments
	 */
	public GameForge getGameForge() {
		return this.gameForge;
	}
	
	/**
	 * @return The game Style
	 */
	public GameStyle getGameStyle() {
		return this.gameStyle;
	}

	/**
	 * @return The game folder
	 */
	public GameFolder getGameFolder() {
		return this.gameFolder;
	}
	
	/**
	 * @return The game size
	 */
	public GameSize getGameSize() {
		if (this.gameSize == null) {
			this.gameSize = GameSize.DEFAULT;
		}
		return this.gameSize;
	}
	
	/**
	 * @return The game links
	 */
	public GameLinks getGameLinks() {
		return this.gameLinks;
	}
	
	/**
	 * @return The ip and port of the server
	 */
	public GameConnect getGameConnect() {
		return this.gameConnect;
	}
	
	/**
	 * @return The memory to use (RAM)
	 */
	public GameMemory getGameMemory() {
		return this.gameMemory;
	}

	/**
	 * @return The game updater
	 */
	public GameUpdater getGameUpdater() {
		return this.gameUpdater;
	}
	
	/**
	 * @return Custom game arguments
	 */
	public GameArguments getGameArguments() {
		return this.gameArguments;
	}
	
	/**
	 * @return The maintenance
	 */
	public GameMaintenance getGameMaintenance() {
		return this.gameMaintenance;
	}
	
	/**
	 * @return Minecraft version from Json
	 */
	public MinecraftVersion getMinecraftVersion() {
		return this.minecraftVersion;
	}

	/**
	 * @return Custom JVM(Java) arguments
	 */
	public JVMArguments getJVMArguments() {
		return jvmArgs;
	}
}
