package fr.trxyy.alternative.alternative_apiv2.minecraft.json;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.Arch;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.CompatibilityRule;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.OperatingSystem;
import fr.trxyy.alternative.alternative_apiv2.minecraft.utils.Substitutor;

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
	 * The download info
	 */
	private DownloadInfo artifact;
	
	/**
	 * The natives in a Map
	 */
	private Map<String, DownloadInfo> classifiers;

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
		CompatibilityRule.Action lastAction = CompatibilityRule.Action.DISALLOW;

		for (CompatibilityRule compatibilityRule : this.rules) {
			CompatibilityRule.Action action = compatibilityRule.getAppliedAction();
			if (action != null)
				lastAction = action;

		}
		return (lastAction == CompatibilityRule.Action.ALLOW);
	}

	/**
	 * The Constructor
	 * @param library The library
	 */
	public MinecraftLibrary(MinecraftLibrary library) {
	    HashMap<String, String> map = new HashMap<String, String>();
	    map.put("platform", OperatingSystem.getCurrent().getName());
	    map.put("arch", Arch.CURRENT.getBit());
	    SUBSTITUTOR = new Substitutor(map);
	    
		this.name = library.name;
		if (library.url != null) {
			this.url = library.url;
		}
		if (!library.url.equals("")) {
			this.url = library.url;
		}
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
		
		this.artifact = library.artifact;
		if (library.classifiers != null) {
//			this.classifiers = library.classifiers;
			this.classifiers = new LinkedHashMap();
			for (Map.Entry<String, DownloadInfo> entry : library.classifiers.entrySet()) {
				this.classifiers.put(entry.getKey(), new DownloadInfo((DownloadInfo) entry.getValue()));
			}
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
//	public String getArtifactBaseDir() {
//		if (this.name == null) {
//			throw new IllegalStateException("Cannot get artifact dir of empty/blank artifact");
//		}
//		String[] parts = this.name.split(":");
//		return String.format("%s/%s/%s", new Object[] { parts[0].replaceAll("\\.", "/"), parts[1], parts[2] });
//	}
	
	  
	private String getArtifactBaseDir() {
		if (this.name == null)
			throw new IllegalStateException("Cannot get artifact dir of empty/blank artifact");
		String[] parts = this.name.split(":");
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
		String result;
		if (this.name == null)
			throw new IllegalStateException("Cannot get artifact filename of empty/blank artifact");
		String[] parts = this.name.split(":");
		if (classifier == null) {
			result = (String) IntStream.range(1, parts.length).<CharSequence>mapToObj(i -> parts[i]).collect(Collectors.joining("-")) + ".jar";
		} else {
			result = String.format("%s-%s%s.jar", new Object[] { parts[1], parts[2], "-" + classifier });
		}
		return SUBSTITUTOR.replace(result);
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
		String[] split = this.name.split(":");
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
	 * Get the download info 
	 * @param classifier The native
	 * @return A Download Info
	 */
	public DownloadInfo getDownloadInfo(String classifier) {
		if (classifier == null) {
			return this.artifact;
		}
		return (DownloadInfo) this.classifiers.get(classifier);
	}

	/**
	 * @return The downloadInfo
	 */
	public DownloadInfo getArtifact() {
		return artifact;
	}

	/**
	 * Set the native
	 * @param artifact The download info
	 */
	public void setArtifact(DownloadInfo artifact) {
		this.artifact = artifact;
	}

	/**
	 * @return The natives in a Map
	 */
	public Map<String, DownloadInfo> getClassifiers() {
		return classifiers;
	}

	/**
	 * Set the natives
	 * @param classifiers The natives in a Map
	 */
	public void setClassifiers(Map<String, DownloadInfo> classifiers) {
		this.classifiers = classifiers;
	}

	/**
	 * toString()
	 */
	public String toString() {
		return "Library{name='" + this.name + '\'' + ", rules=" + this.rules + ", natives=" + this.natives
				+ ", extract=" + this.extract + '}';
	}
}