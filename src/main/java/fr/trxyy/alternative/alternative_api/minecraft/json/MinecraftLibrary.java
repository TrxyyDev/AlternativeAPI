package fr.trxyy.alternative.alternative_api.minecraft.json;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import fr.trxyy.alternative.alternative_api.minecraft.utils.Substitutor;
import fr.trxyy.alternative.alternative_api.minecraft.utils.CompatibilityRule;
import fr.trxyy.alternative.alternative_api.minecraft.utils.CompatibilityRule.Action;
import fr.trxyy.alternative.alternative_api.utils.OperatingSystem;

/**
 * @author Trxyy
 */
public class MinecraftLibrary {
	/**
	 * The Substitutor
	 */
	private Substitutor SUBSTITUTOR = new Substitutor(new HashMap() {});
	/**
	 * The library name
	 */
	protected String name;
	/**
	 * The Compatibility Rules
	 */
	public List<CompatibilityRule> rules;
	/*
	 * The natives in a Map
	 */
	protected Map<OperatingSystem, String> natives; // fais un bug. LibraryOS
	/**
	 * The Minecraft extract rules
	 */
	protected MinecraftRules extract;
	/**
	 * The Library download info
	 */
	protected LibraryDownloadInfo downloads;
	/**
	 * The download url
	 */
	private String url;
	/**
	 * Is skipped ?
	 */
	private boolean skipped = false;

	/**
	 * The Constructor
	 */
	public MinecraftLibrary() {
	}

	/**
	 * The Constructor
	 * @param name The name
	 */
	public MinecraftLibrary(String name) {
		if ((name == null) || (name.length() == 0)) {
			throw new IllegalArgumentException("Library name cannot be null or empty");
		}
		this.name = name;
	}
	
	/**
	 * @return If library is allowed
	 */
	public boolean appliesToCurrentEnvironment() {
		if (this.rules == null)
			return true;
		CompatibilityRule.Action lastAction = CompatibilityRule.Action.disallow;

		for (CompatibilityRule compatibilityRule : this.rules) {
			CompatibilityRule.Action action = compatibilityRule.getAppliedAction();
			if (action != null)
				lastAction = action;

		}
		return (lastAction == CompatibilityRule.Action.allow);
	}

	/**
	 * The Constructor
	 * @param library The library
	 */
	public MinecraftLibrary(MinecraftLibrary library) {
		this.name = library.name;
		this.url = library.url;
		if (library.extract != null) {
			this.extract = new MinecraftRules(library.extract);
		}
		if (library.rules != null) {
			this.rules = new ArrayList();
			for (CompatibilityRule compatibilityRule : library.rules) {
				this.rules.add(new CompatibilityRule(compatibilityRule));
			}
		}
		if (library.natives != null) {
			this.natives = new LinkedHashMap();
			for (Map.Entry<OperatingSystem, String> entry : library.getNatives().entrySet()) {
				this.natives.put(entry.getKey(), entry.getValue());
			}
		}
		if (library.downloads != null) {
			this.downloads = new LibraryDownloadInfo(library.downloads);
		}
	}

	/**
	 * Add some natives
	 * @param operatingSystem The Operating System (Windows, Linux, Mac...)
	 * @param name The native name
	 * @return A MinecraftLibrary
	 */
	public MinecraftLibrary addNative(OperatingSystem operatingSystem, String name) {
		if ((operatingSystem == null) || (!operatingSystem.isSupported())) {
			throw new IllegalArgumentException("Cannot add native for unsupported OS");
		}
		if ((name == null) || (name.length() == 0)) {
			throw new IllegalArgumentException("Cannot add native for null or empty name");
		}
		if (this.natives == null) {
			this.natives = new EnumMap(OperatingSystem.class);
		}
		this.natives.put(operatingSystem, name);
		return this;
	}

	/**
	 * @return The Natives in a Map
	 */
	public Map<OperatingSystem, String> getNatives() {
		return this.natives;
	}

	/**
	 * @return If the library has required natives
	 */
	public boolean hasNatives() {
		return this.natives != null;
	}

	/**
	 * @return The extract rules
	 */
	public MinecraftRules getExtractRules() {
		return this.extract;
	}

	/**
	 * @return The compatibility rules
	 */
	public List<CompatibilityRule> getCompatibilityRules() {
		return this.rules;
	}

	/**
	 * @return The name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set extract rules
	 * @param rules The Minecraft Rules
	 * @return A Minecraft Library
	 */
	public MinecraftLibrary setExtractRules(MinecraftRules rules) {
		this.extract = rules;
		return this;
	}

	/**
	 * @return The Artifact base directory
	 */
	public String getArtifactBaseDir() {
		if (this.name == null) {
			throw new IllegalStateException("Cannot get artifact dir of empty/blank artifact");
		}
		String[] parts = this.name.split(":", 3);
		return String.format("%s/%s/%s", new Object[] { parts[0].replaceAll("\\.", "/"), parts[1], parts[2] });
	}

	/**
	 * @return The artifact path
	 */
	public String getArtifactPath() {
		return getArtifactPath(null);
	}

	/**
	 * @param classifier The native
	 * @return The artifact Path with native
	 */
	public String getArtifactPath(String classifier) {
		if (this.name == null) {
			throw new IllegalStateException("Cannot get artifact path of empty/blank artifact");
		}
		return String.format("%s/%s", new Object[] { getArtifactBaseDir(), getArtifactFilename(classifier) });
	}

	/**
	 * @param classifier The native
	 * @return The Artifact File Name
	 */
	public String getArtifactFilename(String classifier) {
		if (this.name == null) {
			throw new IllegalStateException("Cannot get artifact filename of empty/blank artifact");
		}
		String[] parts = this.name.split(":", 3);
		String result;
		if (classifier == null) {
			result = String.format("%s-%s.jar", new Object[] { parts[1], parts[2] });
		} else {
			result = String.format("%s-%s%s.jar", new Object[] { parts[1], parts[2], "-" + classifier });
		}
		return SUBSTITUTOR.replace(result);
	}

	@Deprecated
	public String getArtifactCustom(String name) {
		String[] split = name.split(":");
		String libName = split[1];
		String libVersion = split[2];
		return libName + "-" + libVersion + ".jar";
	}

	/**
	 * @param libArg The library
	 * @return The artifact natives
	 */
	public String getArtifactNatives(String libArg) {
		String[] split = getName().split(":");
		String libName = split[1];
		String libVersion = split[2];
		return libName + "-" + libVersion + "-" + libArg + ".jar";
	}

	/**
	 * @return The plain name
	 */
	public String getPlainName() {
		String[] split = this.name.split(":", 3);
		return split[0] + "." + split[1];
	}

	/**
	 * @return If is library skipped
	 */
	public boolean isSkipped() {
		return this.skipped;
	}

	/**
	 * Set the library skipped or not
	 * @param skipped
	 */
	public void setSkipped(boolean skipped) {
		this.skipped = skipped;
	}

	/**
	 * @return The Library download info
	 */
	public LibraryDownloadInfo getDownloads() {
		return downloads;
	}

	/**
	 * Set the download info
	 * @param downloads
	 */
	public void setDownloads(LibraryDownloadInfo downloads) {
		this.downloads = downloads;
	}

	/**
	 * toString()
	 */
	public String toString() {
		return "Library{name='" + this.name + '\'' + ", rules=" + this.rules + ", natives=" + this.natives
				+ ", extract=" + this.extract + '}';
	}
}