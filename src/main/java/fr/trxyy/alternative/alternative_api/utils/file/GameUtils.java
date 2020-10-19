package fr.trxyy.alternative.alternative_api.utils.file;

import java.io.File;
import java.util.ArrayList;

import fr.trxyy.alternative.alternative_api.GameEngine;

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
	 * @param engine The GameEngine instance
	 * @return A String that contains the classpath
	 */
	public static String constructClasspath(GameEngine engine) {
		String result = "";
		ArrayList<File> libs = list(engine.getGameFolder().getLibsDir());
		String separator = System.getProperty("path.separator");
		for (File lib : libs) {
			result += lib.getAbsolutePath() + separator;
		}
		result += engine.getGameFolder().getGameJar().getAbsolutePath();
		return result;
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
