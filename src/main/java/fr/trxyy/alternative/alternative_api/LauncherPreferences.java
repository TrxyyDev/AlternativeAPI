package fr.trxyy.alternative.alternative_api;

/**
 * @author Trxyy
 */
public class LauncherPreferences {

	/**
	 * The launcher name
	 */
	public String name;
	/**
	 * The launcher width
	 */
	public int width;
	/**
	 * The launcher height
	 */
	public int height;
	/**
	 * If the window is moveable or not
	 */
	public boolean moveable;
	/**
	 * The resource location in package
	 */
	public String resourceLocation;

	/**
	 * The Constructor
	 * @param n The launcher name
	 * @param w The launcher size width
	 * @param h The launcher size height
	 * @param m If the window is moveable or not
	 */
	public LauncherPreferences(String n, int w, int h, boolean m) {
		this.name = n;
		this.width = w;
		this.height = h;
		this.moveable = m;
		this.resourceLocation = "/resources/";
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
	 * @return The launcher name (title window)
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return The resource location
	 */
	public String getResourceLocation() {
		return this.resourceLocation;
	}

	/**
	 * @return If the launcher window is moveable by a click or not
	 */
	public boolean isMoveable() {
		return this.moveable;
	}

}
