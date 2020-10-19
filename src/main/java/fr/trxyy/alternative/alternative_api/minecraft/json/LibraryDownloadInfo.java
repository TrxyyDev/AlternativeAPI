package fr.trxyy.alternative.alternative_api.minecraft.json;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Trxyy
 */
public class LibraryDownloadInfo {
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
	public LibraryDownloadInfo() {
	}

	/**
	 * The Constructor
	 * @param other The LibraryDownloadInfo
	 */
	public LibraryDownloadInfo(LibraryDownloadInfo other) {
		this.artifact = other.artifact;
		if (other.classifiers != null) {
			this.classifiers = new LinkedHashMap();
			for (Map.Entry<String, DownloadInfo> entry : other.classifiers.entrySet()) {
				this.classifiers.put(entry.getKey(), new DownloadInfo((DownloadInfo) entry.getValue()));
			}
		}
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
}