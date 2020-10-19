package fr.trxyy.alternative.alternative_api.assets;

/**
 * @author Trxyy
 */
public class AssetObject {
	/**
	 * The hash
	 */
	private String hash;
	/**
	 * The size
	 */
	private long size;

	/**
	 * @return The hash of the object
	 */
	public String getHash() {
		return this.hash;
	}

	/**
	 * @return The size
	 */
	public long getSize() {
		return this.size;
	}

	/**
	 * @return If is equals
	 */
	@Override
	public boolean equals(Object other) {
		if (this == other)
			return true;
		if ((other == null) || (getClass() != other.getClass()))
			return false;

		AssetObject that = (AssetObject) other;

		if (this.size != that.size)
			return false;
		return this.hash.equals(that.hash);
	}

	/**
	 * @return The hashcode
	 */
	@Override
	public int hashCode() {
		int result = this.hash.hashCode();
		result = 31 * result + (int) (this.size ^ this.size >>> 32);
		return result;
	}
}
