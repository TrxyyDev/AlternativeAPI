package fr.trxyy.alternative.alternative_api.minecraft.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;

import fr.trxyy.alternative.alternative_api.assets.AssetIndexInfo;

/**
 * @author Trxyy
 */
public class MinecraftVersion {

	/**
	 * The minecraft version id
	 */
	private String id;
	/**
	 * The inheritsFrom
	 */
	private String inheritsFrom;
	/**
	 * The minecraft arguments (old)
	 */
	private String minecraftArguments;
	/**
	 * The minecraft libraries
	 */
	private List<MinecraftLibrary> libraries;
	/**
	 * The main class
	 */
	private String mainClass;
	/**
	 * The assets
	 */
	private String assets;
	/**
	 * The asset Index
	 */
	private AssetIndexInfo assetIndex;
	/**
	 * The required downloads
	 */
	private MinecraftClient downloads;
	/**
	 * The minecraft arguments (new)
	 */
	public Map<ArgumentType, List<Argument>> arguments;
	/**
	 * The minecraft java version to use
	 */
	public JavaVersion javaVersion;

	/**
	 * The Constructor
	 */
	public MinecraftVersion() {
	}

	/**
	 * The Constructor
	 * @param version The MinecraftVersion
	 */
	public MinecraftVersion(MinecraftVersion version) {
		this.id = version.id;
		if (version.inheritsFrom != null) {
			this.inheritsFrom = version.inheritsFrom;
		}
		if (version.assetIndex != null) {
			this.assetIndex = version.assetIndex;
		}
		if (version.arguments != null) {
			this.arguments = Maps.newEnumMap(ArgumentType.class);
			for (Map.Entry<ArgumentType, List<Argument>> entry : version.arguments.entrySet()) {
				this.arguments.put(entry.getKey(), new ArrayList<Argument>(entry.getValue()));
			}
		}
	    this.minecraftArguments = version.minecraftArguments;
		this.libraries = version.libraries;
		this.mainClass = version.mainClass;
		this.assets = version.assets;
	}
	
	/**
	 * @return The Minecraft arguments (old)
	 */
	public String getMinecraftArguments() {
		return this.minecraftArguments;
	}

	/**
	 * @return The Minecraft libraries as a List
	 */
	public List<MinecraftLibrary> getLibraries() {
		return libraries;
	}

	/**
	 * @return The Minecraft required downloads
	 */
	public MinecraftClient getDownloads() {
		return downloads;
	}
	
	/**
	 * @return The Minecraft required java version
	 */
	public JavaVersion getJavaVersion() {
		return javaVersion;
	}

	/**
	 * @return The version Id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Set the Version Id
	 * @param idd The version id
	 */
	public void setId(String idd) {
		this.id = idd;
	}

	/**
	 * @return The inherits From
	 */
	public String getInheritsFrom() {
		return inheritsFrom;
	}

	/**
	 * Set the InheritsFrom
	 * @param inheritsFrom The inherits from version
	 */
	public void setInheritsFrom(String inheritsFrom) {
		this.inheritsFrom = inheritsFrom;
	}

	/**
	 * @return The main class
	 */
	public String getMainClass() {
		return mainClass;
	}

	/**
	 * Set the main class
	 * @param mainClass The main class
	 */
	public void setMainClass(String mainClass) {
		this.mainClass = mainClass;
	}

	/**
	 * @return The assets
	 */
	public String getAssets() {
		return assets;
	}

	/**
	 * Set the Assets
	 * @param assets The assets
	 */
	public void setAssets(String assets) {
		this.assets = assets;
	}

	/**
	 * @return The AssetIndexInfo
	 */
	public AssetIndexInfo getAssetIndex() {
		return assetIndex;
	}

	/**
	 * Set the asset Index
	 * @param s The asset Index version
	 */
	public void setAssetIndex(String s) {
		this.assets = s;
	}

	/**
	 * @return The arguments as a List (new)
	 */
	public Map<ArgumentType, List<Argument>> getArguments() {
		return this.arguments;
	}
}