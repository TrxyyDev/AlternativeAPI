package fr.trxyy.alternative.alternative_api.minecraft.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.trxyy.alternative.alternative_api.utils.OperatingSystem;

/**
 * @author Trxyy
 */
public class OSRestriction {
	/**
	 * The OS name
	 */
	private OperatingSystem name;
	/**
	 * The version
	 */
	private String version;
	
	/**
	 * The arch
	 */
	private String arch;

	/**
	 * @return The OS Name
	 */
	public OperatingSystem getName() {
		return this.name;
	}

	/**
	 * The Constructor
	 */
	public OSRestriction() {
	}

	/**
	 * @return The version
	 */
	public String getVersion() {
		return this.version;
	}

	/**
	 * @return The OS Arch
	 */
	public String getArch() {
		return this.arch;
	}

	/**
	 * The Constructor
	 * @param osRestriction The Os restriction
	 */
	public OSRestriction(OSRestriction osRestriction) {
		this.name = osRestriction.name;
		this.version = osRestriction.version;
		this.arch = osRestriction.arch;
	}

	/**
	 * @return If the name is equals to the current OS
	 */
	public boolean isCurrentOperatingSystem() {
		if (this.name != null && this.name != OperatingSystem.getCurrentPlatform())
			return false;

		if (this.version != null) {
			try {
				Pattern pattern = Pattern.compile(this.version);
				Matcher matcher = pattern.matcher(System.getProperty("os.version"));
				if (!matcher.matches())
					return false;
			} catch (Throwable throwable) {
			}
		}

		if (this.arch != null) {
			try {
				Pattern pattern = Pattern.compile(this.arch);
				Matcher matcher = pattern.matcher(System.getProperty("os.arch"));
				if (!matcher.matches())
					return false;
			} catch (Throwable throwable) {
			}
		}

		return true;
	}

	/**
	 * toString()
	 */
	public String toString() {
		return "OSRestriction{name=" + this.name + ", version='" + this.version + '\'' + ", arch='" + this.arch + '\''
				+ '}';
	}
}