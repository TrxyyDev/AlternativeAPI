package fr.trxyy.alternative.alternative_api;

/**
 * @author Trxyy
 */
public enum GameSize {

	DEFAULT("854x480", 854, 480),
	SIZE_1024x768("1024x768", 1024, 768),
	SIZE_1280x1024("1280x1024", 1280, 1024),
	SIZE_1366x768("1366x768", 1366, 768),
	SIZE_1600x900("1600x900", 1600, 900),
	SIZE_1920x1080("1920x1080", 1920, 1080),
	SIZE_2560x1440("2560x1440", 2560, 1440);
	
	/**
	 * The size to display
	 */
	public String description;
	/**
	 * The game width
	 */
	public int width;
	/**
	 * The game height
	 */
	public int height;

	/**
	 * The Constructor
	 * @param desc The size to display
	 * @param w The width
	 * @param h The height
	 */
	GameSize(String desc, int w, int h) {
		this.description = desc;
		this.width = w;
		this.height = h;
	}
	
	/**
	 * @return The description to display
	 */
	public String getDesc() {
		return this.description;
	}
	
	/**
	 * @return The width
	 */
	public int getWidth() {
		return this.width;
	}
	
	/**
	 * @return The height
	 */
	public int getHeight() {
		return this.height;
	}

}
