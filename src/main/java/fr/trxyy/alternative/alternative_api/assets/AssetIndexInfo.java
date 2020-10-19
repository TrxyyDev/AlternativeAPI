package fr.trxyy.alternative.alternative_api.assets;

import fr.trxyy.alternative.alternative_api.minecraft.json.DownloadInfo;

/**
 * @author Trxyy
 */
public class AssetIndexInfo extends DownloadInfo {
	/**
	 * The assetIndex
	 */
	protected String id;
	/**
	 * The total size of all Objects
	 */
	protected long totalSize;
	/**
	 * Is known
	 */
	protected boolean known = true;

	/**
	 * The Constructor
	 */
	public AssetIndexInfo() {
	}

	/**
	 * The Constructor
	 * @param id_ The assetIndex id
	 */
	public AssetIndexInfo(String id_) {
		this.id = id_;
		this.url = constantURL("https://s3.amazonaws.com/Minecraft.Download/indexes/" + id + ".json");
		this.known = false;
	}

	/**
	 * @return The total size 
	 */
	public long getTotalSize() {
		return this.totalSize;
	}

	/**
	 * @return The assetIndex id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * @return If the Size and Hash is known
	 */
	public boolean sizeAndHashKnown() {
		return this.known;
	}
}
