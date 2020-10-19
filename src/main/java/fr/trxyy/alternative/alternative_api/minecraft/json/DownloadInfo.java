package fr.trxyy.alternative.alternative_api.minecraft.json;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * @author Trxyy
 */
public class DownloadInfo {
	/**
	 * the path to file
	 */
	protected String path;
	/**
	 * The url of the file
	 */
	protected URL url;
	/**
	 * The sha1 of the file
	 */
	protected String sha1;
	/**
	 * The size of the file
	 */
	protected int size;

	/**
	 * The Constructor
	 */
	public DownloadInfo() {
	}

	/**
	 * The Constructor
	 * @param other The DownloadInfo
	 */
	public DownloadInfo(DownloadInfo other) {
		this.path = other.path;
		this.url = other.url;
		this.sha1 = other.sha1;
		this.size = other.size;
	}

	/**
	 * @return The url
	 */
	public URL getUrl() {
		return this.url;
	}

	/**
	 * @return The Sha1
	 */
	public String getSha1() {
		return this.sha1;
	}
	
	/**
	 * @return The path
	 */
	public String getPath() {
		return this.path;
	}
	
	/**
	 * @return The size
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Convert a String to a URI
	 * @param input The URI
	 * @return A URI
	 */
	public static URI constantURI(String input) {
		try {
			return new URI(input);
		} catch (URISyntaxException e) {
			throw new Error(e);
		}
	}

	/**
	 * Convert a String to a URL
	 * @param input The URL
	 * @return A URL
	 */
	public static URL constantURL(String input) {
		try {
			return new URL(input);
		} catch (MalformedURLException e) {
			throw new Error(e);
		}
	}
}