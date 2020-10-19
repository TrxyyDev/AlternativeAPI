package fr.trxyy.alternative.alternative_api.assets;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Trxyy
 */
public class AssetIndex {
	/**
	 * The default asset name
	 */
	public static final String DEFAULT_ASSET_NAME = "legacy";
	/**
	 * The objects stocked inside a Map<String, AssetObject>
	 */
	private final Map<String, AssetObject> objects;
	/**
	 * Is Virtual ?
	 */
	private boolean virtual;

	/**
	 * The Constructor
	 */
	public AssetIndex() {
		this.objects = new LinkedHashMap<String, AssetObject>();
	}

	/**
	 * @return The objects list as a Map
	 */
	public Map<String, AssetObject> getObjects() {
		return this.objects;
	}

	/**
	 * @return Get the unique Objects as a Set
	 */
	public Set<AssetObject> getUniqueObjects() {
		return new HashSet<AssetObject>(this.objects.values());
	}

	/**
	 * @return If it's virtual
	 */
	public boolean isVirtual() {
		return this.virtual;
	}

}
