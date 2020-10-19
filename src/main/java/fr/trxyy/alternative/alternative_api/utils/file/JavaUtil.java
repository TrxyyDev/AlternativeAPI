package fr.trxyy.alternative.alternative_api.utils.file;

import java.lang.reflect.Field;

/**
 * @author Trxyy
 */
public class JavaUtil {

	/**
	 * @return Special Arguments
	 */
	public static String[] getSpecialArgs() {
		return new String[] { "-XX:-UseAdaptiveSizePolicy", "-XX:+UseConcMarkSweepGC" };
	}

	/**
	 * @param name The name
	 * @return The dock Mac Name
	 */
	public static String macDockName(String name) {
		return "-Xdock:name=" + name;
	}

	/**
	 * @return The Java Command by OS
	 */
	public static String getJavaCommand() {
		if (System.getProperty("os.name").toLowerCase().contains("win"))
			return "\"" + System.getProperty("java.home") + "\\bin\\java" + "\"";

		return System.getProperty("java.home") + "/bin/java";
	}

	/**
	 * Set the library Path
	 * @param path The library path
	 * @throws Exception
	 */
	public static void setLibraryPath(String path) throws Exception {
		System.setProperty("java.library.path", path);

		Field fieldSysPath = ClassLoader.class.getDeclaredField("sys_paths");
		fieldSysPath.setAccessible(true);
		fieldSysPath.set(null, null);
	}
}