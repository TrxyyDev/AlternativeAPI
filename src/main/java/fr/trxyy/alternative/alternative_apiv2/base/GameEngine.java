package fr.trxyy.alternative.alternative_apiv2.base;

import fr.trxyy.alternative.alternative_apiv2.minecraft.json.MinecraftVersion;
import fr.trxyy.alternative.alternative_apiv2.updater.GameUpdater;
import javafx.stage.Stage;

public class GameEngine {
	/**
	 * The GameFolder, folder hierarchy
	 */
	private GameFolder gameFolder;
	/**
	 * The GameLinks, the links for the json, ignore, delete & status file
	 */
	private GameLinks gameLinks;
	/**
	 * The LauncherPreferences, the name, size, ismoveable, resourceLocation
	 */
	private LauncherPreferences launcherSize;
	/**
	 * The Stage, the stage in question to use in other class.
	 */
	private Stage fakeBase;
	/**
	 * MinecraftVersion, the Minecraft version from the json
	 */
	private MinecraftVersion minecraftVersion;
	private GameConnect gameConnect;
	private GameSize gameSize;
	private GameUpdater minecraftUpdater;

	/**
	 * The Constructor
	 * 
	 * @param folder The GameFolder for the folder name in APPDATA
	 * @param links  The links where the updater need to update
	 */
	public GameEngine(GameFolder folder, GameLinks links, LauncherPreferences lSize) {
		this.gameFolder = folder;
		this.gameLinks = links;
		this.launcherSize = lSize;
		this.gameSize = GameSize.DEFAULT;
	}

	public GameFolder getGameFolder() {
		return this.gameFolder;
	}

	public LauncherPreferences getLauncherPreferences() {
		return this.launcherSize;
	}

	public GameLinks getGameLinks() {
		return this.gameLinks;
	}

	public int getWidth() {
		return this.getLauncherPreferences().getWidth();
	}

	public int getHeight() {
		return this.getLauncherPreferences().getHeight();
	}

	/**
	 * Register some things...
	 * 
	 * @param version Register The minecraft version from json
	 */
	public void reg(MinecraftVersion version) {
		this.minecraftVersion = version;
	}
	
	/**
	 * Register some things...
	 * 
	 * @param connect Register The GameConnect object
	 */
	public void reg(GameConnect connect) {
		this.gameConnect = connect;
	}
	/**
	 * Register some things...
	 * 
	 * @param connect Register The GameSize object
	 */
	public void reg(GameSize connect) {
		this.gameSize = connect;
	}
	/**
	 * Register some things...
	 * 
	 * @param upd Register The MinecraftUpdater object
	 */
	public void reg(GameUpdater upd) {
		this.minecraftUpdater = upd;
	}
	/**
	 * Register some things...
	 * 
	 * @param base Register the current stage to use
	 */
	public void reg(Stage base) {
		this.fakeBase = base;
	}

	/**
	 * @return The Stage
	 */
	public Stage getStage() {
		return this.fakeBase;
	}

	/**
	 * @return Minecraft version from Json
	 */
	public MinecraftVersion getMinecraftVersion() {
		return this.minecraftVersion;
	}

	/**
	 * @return The Server and Port of the direct connect
	 */
	public GameConnect getGameConnect() {
		return this.gameConnect;
	}

	public GameSize getGameSize() {
		return this.gameSize;
	}

	public GameUpdater getGameUpdater() {
		return this.minecraftUpdater;
	}
}
