package fr.trxyy.alternative.alternative_api;

import java.io.File;

import fr.trxyy.alternative.alternative_api.utils.file.GameUtils;

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
	 * The bin directory
	 */
	public File binDir;
	/**
	 * The play directory, where the game is placed
	 */
	public File playDir;
	/**
	 * The game jar (minecraft.jar)
	 */
	public File gameJar;
	/**
	 * The libraries directory, where the libraries are placed
	 */
	public File libsDir;
	/**
	 * The assets directory, where the assets are placed
	 */
	public File assetsDir;
	/**
	 * The natives directory, where the natives are placed
	 */
	public File nativesDir;
	/**
	 * The natives cache directory, where the natives are placed until unzipped inside natives directory
	 */
	public File nativesCacheDir;

	/**
	 * The Constructor
	 * @param location The folder name (inside APPDATA, if minecraft = .minecraft; if myserver = .myserver)
	 */
	public GameFolder(String location) {
		this.gameDir = GameUtils.getWorkingDirectory(location);
		this.binDir = new File(this.gameDir, "bin");
		this.playDir = new File(this.gameDir, "bin" + File.separator + "game");
		this.gameJar = new File(this.gameDir, "bin" + File.separator + "minecraft.jar");
		this.libsDir = new File(this.gameDir, "libraries");
		this.cacheDir = new File(this.gameDir, "cache");
		this.assetsDir = new File(this.gameDir, "assets");
		this.nativesDir = new File(this.gameDir, "bin" + File.separator + "natives");
		this.nativesCacheDir = new File(this.gameDir, "bin" + File.separator + "cache_natives");
		
		/** ----- Creating missing folders -------- */
		this.getLibsDir().mkdirs();
		this.getCacheDir().mkdirs();
		this.getAssetsDir().mkdirs();
		this.getBinDir().mkdirs();
		this.getGameDir().mkdirs();
		this.getNativesDir().mkdirs();
		this.getNativesCacheDir().mkdirs();
		this.getPlayDir().mkdirs();
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
	 * @return The bin directory (all game files)
	 */
	public File getBinDir() {
		return this.binDir;
	}
	
	/**
	 * @return The play directory (game placed)
	 */
	public File getPlayDir() {
		return this.playDir;
	}

	/**
	 * @return The game jar (minecraft.jar
	 */
	public File getGameJar() {
		return this.gameJar;
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
