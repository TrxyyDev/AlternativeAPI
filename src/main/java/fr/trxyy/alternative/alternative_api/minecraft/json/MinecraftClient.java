package fr.trxyy.alternative.alternative_api.minecraft.json;

/**
 * @author Trxyy
 */
public class MinecraftClient {

	/**
	 * The client url
	 */
	private DownloadInfo client;

	/**
	 * @return The client
	 */
	public DownloadInfo getClient() {
		return client;
	}

	/**
	 * Set the Client Info
	 * @param client The download Info
	 */
	public void setClient(DownloadInfo client) {
		this.client = client;
	}

}
