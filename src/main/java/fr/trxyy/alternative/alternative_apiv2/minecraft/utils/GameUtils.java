package fr.trxyy.alternative.alternative_apiv2.minecraft.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import fr.trxyy.alternative.alternative_apiv2.base.GameEngine;
import fr.trxyy.alternative.alternative_apiv2.base.GameFolder;
import fr.trxyy.alternative.alternative_apiv2.minecraft.json.MinecraftLibrary;
import fr.trxyy.alternative.alternative_apiv2.minecraft.json.MinecraftVersion;

/**
 * @author Trxyy
 */
public class GameUtils {

	/**
	 * @param workDir The working directory
	 * @return The working directory fro each OS
	 */
	public static File getWorkingDirectory(String workDir) {
		String userHome = System.getProperty("user.home", ".");
		File workingDirectory;
		switch (getPlatform()) {
		case 1:
			workingDirectory = new File(userHome + "/." + workDir);
		case 2:
			workingDirectory = new File(userHome + "/." + workDir);
			break;
		case 3:
			workingDirectory = new File(userHome + "\\AppData\\Roaming\\." + workDir);
			break;
		case 4:
			workingDirectory = new File(userHome + "/Library/Application Support/" + workDir);
			break;
		default:
			workingDirectory = new File(userHome + "/." + workDir);
		}
		return workingDirectory;
	}

	/**
	 * @return The current Platform
	 */
	private static int getPlatform() {
		String osName = System.getProperty("os.name").toLowerCase();
		if (osName.contains("linux"))
			return 1;
		if (osName.contains("unix"))
			return 1;
		if (osName.contains("solaris"))
			return 2;
		if (osName.contains("sunos"))
			return 2;
		if (osName.contains("win"))
			return 3;
		if (osName.contains("mac"))
			return 4;
		return 5;
	}
	
	/**
	 * @param mcVersion The GameEngine instance
	 * @return A String that contains the classpath
	 */
	public static String constructClasspath(MinecraftVersion mcVersion, GameEngine engine) {
		GameFolder workDir = engine.getGameFolder();
		String result = "";
		List<MinecraftLibrary> test = mcVersion.getLibraries();
		ArrayList<File> libs = getMinecraftLibraries(mcVersion, engine);
		String separator = System.getProperty("path.separator");
		for (File lib : libs) {
//			if (lib.getAbsolutePath().contains("fmlloader")) {
//
//			} 
			if (lib.getAbsolutePath().contains("-srg")) {

			} 
			else if (lib.getAbsolutePath().contains("-extra")) {

			} 
			else if (lib.getAbsolutePath().contains("fmlcore")) {

			} 
			else if (lib.getAbsolutePath().contains("javafmllanguage")) {

			} 
			else if (lib.getAbsolutePath().contains("mclanguage")) {

			}
			else if (lib.getAbsolutePath().contains("-universal")) {

			} else {
				result += lib.getAbsolutePath() + separator;
			}
			
//			if (!lib.getAbsolutePath().contains("fmlloader") || 
//					!lib.getAbsolutePath().contains("-srg") || 
//					!lib.getAbsolutePath().contains("-extra") || 
//					!lib.getAbsolutePath().contains("fmlcore") || 
//					!lib.getAbsolutePath().contains("javafmllanguage") || 
//					!lib.getAbsolutePath().contains("mclanguage") || 
//					!lib.getAbsolutePath().contains("-universal")) {
//					result += lib.getAbsolutePath() + separator;
//				}
		}
		File versionFolder = new File(workDir.getVersionsDir(), mcVersion.getId());
		result += new File(versionFolder, mcVersion.getId() + ".jar").getAbsolutePath();
		return result;
	}

	private static ArrayList<File> getMinecraftLibraries(MinecraftVersion minecraftVersion, GameEngine engine) {
		ArrayList<File> theLibraries = new ArrayList<File>();
		GameFolder workDir = engine.getGameFolder();
		for (MinecraftLibrary lib : minecraftVersion.getLibraries()) {
			if (lib.appliesToCurrentEnvironment()) {
				File libPath = new File(workDir.getLibsDir(), lib.getArtifactPath());
				theLibraries.add(libPath);
			}
		}
		return theLibraries;
	}

	/**
	 * @param folder The folder to list
	 * @return A ArrayList that contains all files listed
	 */
	public static ArrayList<File> list(File folder) {
		ArrayList<File> files = new ArrayList<File>();
		if (!folder.isDirectory())
			return files;

		File[] folderFiles = folder.listFiles();
		if (folderFiles != null)
			for (File f : folderFiles)
				if (f.isDirectory())
					files.addAll(list(f));
				else
					files.add(f);

		return files;
	}

}
