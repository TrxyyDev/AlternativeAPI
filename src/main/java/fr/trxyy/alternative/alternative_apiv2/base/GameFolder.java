package fr.trxyy.alternative.alternative_apiv2.base;

import java.io.File;

import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.GameUtils;

/**
 * @author Trxyy
 */
public class GameFolder {
	
	/**
	 * The main game directory
	 */
	public File gameDir;
	/**
	 * The cache directory
	 */
	public File cacheDir;
	/**
	 * The versions directory, where the game is placed
	 */
	public File versionsDir;
	/**
	 * The resourcepacks folder
	 */
	public File resourcepackDir;
	/**
	 * The libraries directory, where the libraries are placed
	 */
	public File libsDir;
	/**
	 * The assets directory, where the assets are placed
	 */
	public File assetsDir;
	/**
	 * The logging configuration directory, where the log4j configuration files are placed
	 */
	public File log_configsDir;
	/**
	 * The natives directory, where the natives are placed
	 */
	public File nativesDir;
	/**
	 * The natives cache directory, where the natives are placed until unzipped inside natives directory
	 */
	public File nativesCacheDir;
	/**
	 * The runtime directory
	 */
	public File runtimeDir;

	/**
	 * The Constructor
	 * @param location The folder name (inside APPDATA, if minecraft = .minecraft; if myserver = .myserver)
	 */
	public GameFolder(String location) {
		this.gameDir = GameUtils.getWorkingDirectory(location);
		this.assetsDir = new File(this.gameDir, "assets");
		this.libsDir = new File(this.gameDir, "libraries");
		this.versionsDir = new File(this.gameDir, "versions");
		this.resourcepackDir = new File(this.gameDir, "resourcepacks");
		this.cacheDir = new File(this.gameDir, "cache");
		this.log_configsDir = new File(this.assetsDir, "log_configs");
		this.nativesDir = new File(this.gameDir, "natives");
		this.nativesCacheDir = new File(this.gameDir, "cache_natives");
		this.runtimeDir = new File(this.gameDir, "runtime");
		
		/** ----- Creating missing folders -------- */
		this.getLibsDir().mkdirs();
		this.getCacheDir().mkdirs();
		this.getAssetsDir().mkdirs();
		this.getLogConfigsDir().mkdirs();
		this.getGameDir().mkdirs();
		this.getNativesDir().mkdirs();
		this.getNativesCacheDir().mkdirs();
		this.getVersionsDir().mkdirs();
		this.getRuntimeDir().mkdirs();
	}

	public File getRuntimeDir() {
		return this.runtimeDir;
	}

	/**
	 * @return The log_configs directory
	 */
	public File getLogConfigsDir() {
		return this.log_configsDir;
	}

	/**
	 * @return The game directory
	 */
	public File getGameDir() {
		return this.gameDir;
	}
	
	/**
	 * @return The cache directory
	 */
	public File getCacheDir() {
		return this.cacheDir;
	}
	
	/**
	 * @return The play directory (game placed)
	 */
	public File getVersionsDir() {
		return this.versionsDir;
	}

	/**
	 * @return The game jar (minecraft.jar
	 */
	public File getresourcepackDir() {
		return this.resourcepackDir;
	}

	/**
	 * @return The libraries directory
	 */
	public File getLibsDir() {
		return this.libsDir;
	}

	/**
	 * @return The assets directory
	 */
	public File getAssetsDir() {
		return this.assetsDir;
	}

	/**
	 * @return The natives directory
	 */
	public File getNativesDir() {
		return this.nativesDir;
	}

	/**
	 * @return The natives cache directory
	 */
	public File getNativesCacheDir() {
		return this.nativesCacheDir;
	}
}
