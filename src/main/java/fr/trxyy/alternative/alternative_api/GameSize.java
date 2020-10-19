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

	/**
	 * @param i The value as a int
	 * @return The GameSize from the int
	 */
	public static GameSize getWindowSize(int i) {
		if (i == 0) {
			return GameSize.DEFAULT;
		} else if (i == 1) {
			return GameSize.SIZE_1024x768;
		} else if (i == 2) {
			return GameSize.SIZE_1280x1024;
		} else if (i == 3) {
			return GameSize.SIZE_1366x768;
		} else if (i == 4) {
			return GameSize.SIZE_1600x900;
		} else if (i == 5) {
			return GameSize.SIZE_1920x1080;
		} else if (i == 6) {
			return GameSize.SIZE_2560x1440;
		}
		return GameSize.DEFAULT;
	}
	
	/**
	 * @param value The value as a String
	 * @return The GameSize from the String
	 */
	public static int getWindowSize(String value) {
		if (value.equals("854x480")) {
			return 0;
		} else if (value.equals("1024x768")) {
			return 1;
		} else if (value.equals("1280x1024")) {
			return 2;
		} else if (value.equals("1366x768")) {
			return 3;
		} else if (value.equals("1600x900")) {
			return 4;
		} else if (value.equals("1920x1080")) {
			return 5;
		} else if (value.equals("2560x1440")) {
			return 6;
		}
		return 0;
	}
	
}
